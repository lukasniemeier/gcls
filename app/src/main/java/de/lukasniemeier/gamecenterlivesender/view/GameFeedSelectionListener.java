package de.lukasniemeier.gamecenterlivesender.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.list.ItemProcessor;
import com.google.sample.castcompanionlibrary.cast.VideoCastManager;

import org.droidparts.net.image.ImageFetcher;

import java.util.HashMap;
import java.util.Map;

import de.lukasniemeier.gamecenterlivesender.CoreApplication;
import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.data.DataManager;
import de.lukasniemeier.gamecenterlivesender.model.Feed;
import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;
import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public class GameFeedSelectionListener implements GameAdapter.OnGameClickedListener {

    public interface FeedSelectionListener {
        void onFeedSelected(Game game, Feed feed);
    }

    private static final String TAG = GameFeedSelectionListener.class.getSimpleName();
    private Activity context;
    private DataManager dataManager;
    private FeedSelectionListener listener;
    private ImageFetcher imageFetcher;

    public GameFeedSelectionListener(Activity context, DataManager dataManager, FeedSelectionListener listener) {
        this.context = context;
        this.dataManager = dataManager;
        this.listener = listener;
        this.imageFetcher = new ImageFetcher(context);
    }

    @Override
    public void onGameClicked(final Game game) {
        if (!getCastManager().isConnected()) {
            Toast.makeText(context, "Connect to Chromecast first", Toast.LENGTH_LONG).show();
            return;
        }

        String awayTeam = game.getGameInformation().getAwayTeam().getTeamAbb();
        String homeTeam = game.getGameInformation().getHomeTeam().getTeamAbb();
        boolean hasCondesed = game.getGameHighlightVideo() != null &&
                Boolean.TRUE.equals(game.getGameHighlightVideo().getHasCondensedVideo());

        Map<String, Feed> itemMap = new HashMap<>();
        itemMap.put(String.format("%s;%s", awayTeam, Feed.FeedType.LIVE.code()),
                new Feed(Feed.FeedTarget.AWAY, Feed.FeedType.LIVE));
        itemMap.put(String.format("%s;%s", homeTeam, Feed.FeedType.LIVE.code()),
                new Feed(Feed.FeedTarget.HOME, Feed.FeedType.LIVE));
        if (hasCondesed) {
            itemMap.put(String.format("%s;%s", awayTeam, Feed.FeedType.CONDENSED.code()),
                    new Feed(Feed.FeedTarget.AWAY, Feed.FeedType.CONDENSED));
            itemMap.put(String.format("%s;%s", homeTeam, Feed.FeedType.CONDENSED.code()),
                    new Feed(Feed.FeedTarget.HOME, Feed.FeedType.CONDENSED));
        }

        new MaterialDialog.Builder(context)
                .title("Choose feed")
                .items(itemMap.keySet().toArray(new String[0]))
                .itemProcessor(new ItemProcessor(context) {
                    @Override
                    protected int getLayout(int index) {
                        return R.layout.feed_list_item;
                    }

                    @Override
                    protected void onViewInflated(int index, String feedKey, View view) {
                        ImageView teamIcon = (ImageView) view.findViewById(R.id.team_icon);
                        TextView feedName = (TextView) view.findViewById(R.id.feed_name);

                        String teamCode = feedKey.split(";")[0];
                        String feedType = feedKey.split(";")[1];

                        Team team = dataManager.getTeam(teamCode);
                        imageFetcher.attachImage(team.getLogoURL(), teamIcon);
                        feedName.setText(String.format("%s (%s)",
                                team.getCity(), feedType));
                    }
                })
                .itemsCallback((materialDialog, view, index, text) -> {
                    Feed feed = itemMap.get(text);
                    if (feed != null) {
                        listener.onFeedSelected(game, feed);
                    } else {
                        Log.w(TAG, "Invalid feed selection.");
                    }
                })
                .build().show();
    }

    private VideoCastManager getCastManager() {
        return CoreApplication.getVideoCastManager(context);
    }
}
