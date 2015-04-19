package de.lukasniemeier.gamecenterlivesender.view;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.droidparts.net.image.ImageFetcher;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.TimeZone;

import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.data.DataManager;
import de.lukasniemeier.gamecenterlivesender.model.GameHelper;
import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;
import de.lukasniemeier.gamecenterlivesender.utils.LocalUtils;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private final DataManager dataManager;
    private final ImageFetcher imageFetcher;
    private final OnGameClickedListener listener;

    public GameAdapter(DataManager dataManager, ImageFetcher imageFetcher, OnGameClickedListener listener) {
        this.dataManager = dataManager;
        this.imageFetcher = imageFetcher;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_game, parent, false);
        ViewHolder vh = new ViewHolder(cardView);
        vh.parentView.setOnClickListener(view -> {
            Game game = (Game) view.getTag();
            listener.onGameClicked(game);
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Game game = dataManager.getGames().get(position);
        viewHolder.bindGame(game);
    }

    @Override
    public int getItemCount() {
        return dataManager.getGames().size();
    }

    public interface OnGameClickedListener {
        void onGameClicked(Game game);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm")
                .withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()));

        public CardView parentView;
        public TextView awayTeamText;
        public TextView awayTeamSubText;
        public TextView homeTeamText;
        public TextView homeTeamSubText;
        public ImageView awayTeamLogo;
        public ImageView homeTeamLogo;
        public TextView timeText;
        private TextView progressText;

        public ViewHolder(CardView view) {
            super(view);
            this.parentView = view;
            this.awayTeamText = (TextView) view.findViewById(R.id.away_team_text);
            this.awayTeamSubText = (TextView) view.findViewById(R.id.away_team_sub_text);
            this.homeTeamText = (TextView) view.findViewById(R.id.home_team_text);
            this.homeTeamSubText = (TextView) view.findViewById(R.id.home_team_sub_text);
            this.awayTeamLogo = (ImageView) view.findViewById(R.id.away_team_logo);
            this.homeTeamLogo = (ImageView) view.findViewById(R.id.home_team_logo);
            this.timeText = (TextView) view.findViewById(R.id.time_text);
            this.progressText = (TextView) view.findViewById(R.id.progress_text);
        }

        public void bindGame(Game game) {
            Team awayTeam = dataManager.getTeam(
                    game.getGameInformation().getAwayTeam().getTeamAbb());
            Team homeTeam = dataManager.getTeam(
                    game.getGameInformation().getHomeTeam().getTeamAbb());

            String gameTimeEst = game.getGameInformation().getEasternGameTime();
            DateTime estTime = LocalUtils.parseDateTimeEastern(gameTimeEst);

            parentView.setTag(game);
            timeText.setText(fmt.print(estTime));
            timeText.setTextColor(GameHelper.gameHasProgram(game) ? Color.BLACK : Color.GRAY);

            awayTeamText.setText(awayTeam.getName());
            awayTeamSubText.setText(awayTeam.getCity());

            homeTeamText.setText(homeTeam.getName());
            homeTeamSubText.setText(homeTeam.getCity());

            imageFetcher.attachImage(awayTeam.getLogoURL(), awayTeamLogo);
            imageFetcher.attachImage(homeTeam.getLogoURL(), homeTeamLogo);

            progressText.setVisibility(View.GONE);
        }
    }
}
