package de.lukasniemeier.gamecenterlivesender.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.sample.castcompanionlibrary.cast.VideoCastManager;

import org.droidparts.net.image.ImageFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        Team awayTeam = dataManager.getTeam(game.getGameInformation().getAwayTeam().getTeamAbb());
        Team homeTeam = dataManager.getTeam(game.getGameInformation().getHomeTeam().getTeamAbb());
        boolean hasCondensed = game.getGameHighlightVideo() != null &&
                Boolean.TRUE.equals(game.getGameHighlightVideo().getHasCondensedVideo());

        List<Feed> feeds = new ArrayList<>(4);
        feeds.add(new Feed(Feed.FeedTarget.AWAY, Feed.FeedType.LIVE));
        feeds.add(new Feed(Feed.FeedTarget.HOME, Feed.FeedType.LIVE));
        if (hasCondensed) {
            feeds.add(new Feed(Feed.FeedTarget.AWAY, Feed.FeedType.CONDENSED));
            feeds.add(new Feed(Feed.FeedTarget.HOME, Feed.FeedType.CONDENSED));
        }

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .adapter(new FeedAdapter(context, imageFetcher, awayTeam, homeTeam, feeds))
                .build();
        ListView listView = dialog.getListView();
        assert listView != null;
        listView.setOnItemClickListener((parent, view, position, id) -> {
            dialog.cancel();
            listener.onFeedSelected(game, feeds.get(position));
        });
        dialog.show();
    }

    private VideoCastManager getCastManager() {
        return CoreApplication.getVideoCastManager(context);
    }
}
