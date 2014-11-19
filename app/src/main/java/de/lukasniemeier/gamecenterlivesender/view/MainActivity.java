package de.lukasniemeier.gamecenterlivesender.view;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.sample.castcompanionlibrary.cast.BaseCastManager;
import com.google.sample.castcompanionlibrary.cast.VideoCastManager;
import com.google.sample.castcompanionlibrary.widgets.MiniController;

import org.apache.http.cookie.Cookie;
import org.droidparts.Injector;
import org.droidparts.annotation.inject.InjectView;
import org.droidparts.net.http.RESTClient2;
import org.droidparts.net.image.ImageFetcher;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import de.lukasniemeier.gamecenterlivesender.BuildConfig;
import de.lukasniemeier.gamecenterlivesender.CoreApplication;
import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.data.InMemoryDataManager;
import de.lukasniemeier.gamecenterlivesender.data.ReadWriteDataManager;
import de.lukasniemeier.gamecenterlivesender.http.GetHttpTask;
import de.lukasniemeier.gamecenterlivesender.http.HttpTask;
import de.lukasniemeier.gamecenterlivesender.http.TaskFactory;
import de.lukasniemeier.gamecenterlivesender.model.Feed;
import de.lukasniemeier.gamecenterlivesender.model.GameHelper;
import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;
import de.lukasniemeier.gamecenterlivesender.utils.Functional;
import de.lukasniemeier.gamecenterlivesender.utils.LocalUtils;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectView(id = R.id.swipe_container)
    private SwipeRefreshLayout swipeContainer;

    @InjectView(id = R.id.game_list)
    private RecyclerView gameList;

    @InjectView(id = R.id.miniController1)
    private MiniController miniController;

    @InjectView(id = R.id.status_bar)
    private TextView statusBar;

    private VideoCastManager castManager;

    private SimpleVideoCastConsumer castConsumer;

    private RESTClient2 restClient;

    private ReadWriteDataManager dataManager;

    private GameAdapter gameListAdapter;

    private GameDateDividerDecorator gameListDecorator;

    public static MediaInfo buildMediaInfo(String title,
                                           String studio,
                                           String url,
                                           boolean isLiveStream) {
        MediaMetadata movieMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
        movieMetadata.putString(MediaMetadata.KEY_TITLE, title);
        movieMetadata.putString(MediaMetadata.KEY_SUBTITLE, isLiveStream ? "Live" : "On Demand");
        movieMetadata.putString(MediaMetadata.KEY_STUDIO, studio);

        movieMetadata.addImage(new WebImage(Uri.parse("http://cdn.nhl.com/nhl/images/logos/large.png")));
        movieMetadata.addImage(new WebImage(Uri.parse("https://gcls.herokuapp.com/static/logo_por.jpg")));

        return new MediaInfo.Builder(url)
                .setStreamType(isLiveStream ? MediaInfo.STREAM_TYPE_LIVE : MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType("application/vnd.apple.mpegurl")
                .setMetadata(movieMetadata)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseCastManager.checkGooglePlayServices(this);

        setContentView(R.layout.activity_main);
        Injector.inject(this);

        this.dataManager = new InMemoryDataManager();
        this.restClient = new RESTClient2(this, CoreApplication.USER_AGENT);

        setupToolbar();
        setupCastManager();
        setupList();
        refreshData();
    }

    private void setupList() {
        gameListAdapter = new GameAdapter(
                dataManager,
                new ImageFetcher(this),
                new GameFeedSelectionListener(this, dataManager, this::castGame));
        gameList.setLayoutManager(new LinearLayoutManager(this));
        DefaultItemAnimator animator = new DefaultItemAnimator();
        gameList.setItemAnimator(animator);
        gameList.setAdapter(gameListAdapter);
        gameListDecorator = new GameDateDividerDecorator(this);
        gameList.addItemDecoration(gameListDecorator);

        swipeContainer.setOnRefreshListener(() -> refreshData());
    }

    private void setupCastManager() {
        castManager = CoreApplication.getVideoCastManager(this);
        castConsumer = new SimpleVideoCastConsumer(this);
        castManager.addMiniController(miniController);
        castManager.reconnectSessionIfPossible(this, true);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onResume() {
        castManager = CoreApplication.getVideoCastManager(this);
        if (null != castManager) {
            castManager.addVideoCastConsumer(castConsumer);
            castManager.incrementUiCounter();
        }
        super.onResume();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (castManager.onDispatchVolumeKeyEvent(event, CoreApplication.VOLUME_INCREMENT)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {
        castManager.decrementUiCounter();
        castManager.removeVideoCastConsumer(castConsumer);
        super.onPause();
    }

    @Override
    protected void onStop() {
        statusBar.setVisibility(View.GONE);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (null != castManager) {
            miniController.removeOnMiniControllerChangedListener(castManager);
            castManager.removeMiniController(miniController);
            castManager.clearContext(this);
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        castManager.addMediaRouterButton(menu, R.id.media_route_menu_item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            SettingsActivity.startActivity(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshData() {
        swipeContainer.setRefreshing(true);

        dataManager.reset();
        gameListDecorator.clearDividers();

        String teamsUrl = getString(R.string.teams_url);

        new GetHttpTask(restClient, teamsUrl, this,
                response -> {
                    parseTeams(response.body);
                    fetchGames();
                },
                exception -> Toast.makeText(
                        this,
                        getString(R.string.error_downloading_teams),
                        Toast.LENGTH_LONG).show())
                .execute();
    }

    private void parseTeams(String json) {
        JsonArray teamArray = LocalUtils.parseJson(json);
        Gson gson = new Gson();
        dataManager.addTeams(Iterables.transform(teamArray,
                element -> gson.fromJson(element, Team.class)));
    }

    private void fetchGames() {
        DateTime now = DateTime.now();
        DateTime nowInNYC = now.withZone(DateTimeZone.forID("EST"));
        LocalDate date = nowInNYC.toLocalDate();
        fetchGames(date, false);
        fetchGames(date.minusDays(1), true);
        fetchGames(date.minusDays(2), true);
    }

    private void fetchGames(LocalDate date, final boolean addDivider) {
        final String dateString = date.toString();
        String gamesUrl = getString(R.string.games_url, dateString);

        new GetHttpTask(restClient, gamesUrl, this,
                response -> {
                    List<Game> newGames = new ArrayList<Game>();
                    JsonObject json = LocalUtils.parseJson(response.body);
                    JsonArray gameArray = json.getAsJsonArray("games");
                    Gson gson = new Gson();
                    Iterables.addAll(newGames, Iterables.transform(gameArray,
                            (JsonElement element) -> gson.fromJson(element, Game.class)));

                    if (!newGames.isEmpty()) {
                        int firstNewIndex = dataManager.getGames().size();
                        dataManager.addGames(newGames);
                        if (addDivider) {
                            gameListDecorator.addDivider(firstNewIndex, dateString);
                        }
                        gameListAdapter.notifyItemRangeInserted(firstNewIndex, newGames.size());
                    }
                    swipeContainer.setRefreshing(false);
                },
                exception -> {
                    Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                })
                .execute();
    }

    private void castGame(final Game game, final Feed feed) {
        statusBar.setMinimumHeight(miniController.getHeight());

        final String publishPointUrl = String.format(
                getString(R.string.pp_url),
                game.getGameInformation().getGameId(),
                feed.getType().code(),
                feed.getTarget().code());

        Functional.Consumer<String> startCastingFunc = path -> startCasting(game, path);

        TaskFactory factory = new TaskFactory(this, restClient, statusBar);
        HttpTask login = factory.createLoginTask(BuildConfig.GCL_USER, BuildConfig.GCL_PASSWORD,
                factory.createPublishTask(publishPointUrl, startCastingFunc));
        HttpTask publish1st = factory.createPublishTaskWithFallback(login, publishPointUrl,
                startCastingFunc);

        publish1st.execute();
    }

    private void startCasting(Game game, String path) {

        CookieManager manager = (CookieManager) CookieHandler.getDefault();
        List<HttpCookie> cookies = manager.getCookieStore().getCookies();

        MediaInfo info = buildMediaInfo(
                String.format("%s@%s",
                        game.getGameInformation().getAwayTeam().getTeamName(),
                        game.getGameInformation().getHomeTeam().getTeamName()),
                getString(R.string.stream_sub_info),
                path,
                GameHelper.gameIsLive(game));
        castManager.startCastControllerActivity(this, info, 0, true);
    }

}
