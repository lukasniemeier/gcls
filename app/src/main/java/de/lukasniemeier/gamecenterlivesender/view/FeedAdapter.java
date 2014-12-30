package de.lukasniemeier.gamecenterlivesender.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.droidparts.net.image.ImageFetcher;

import java.util.List;

import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.model.Feed;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;

public class FeedAdapter extends ArrayAdapter<Feed> {

    private final ImageFetcher imageFetcher;
    private final Team awayTeam;
    private final Team homeTeam;

    public FeedAdapter(Context context, ImageFetcher imageFetcher,
                       Team awayTeam, Team homeTeam, List<Feed> feeds) {
        super(context, R.layout.feed_list_item, feeds);
        this.imageFetcher = imageFetcher;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = parent.inflate(getContext(), R.layout.feed_list_item, null);
        }
        ImageView teamIcon = (ImageView) view.findViewById(R.id.team_icon);
        TextView feedName = (TextView) view.findViewById(R.id.feed_name);


        Feed feed = getItem(position);
        Team team = feed.getTarget() == Feed.FeedTarget.AWAY ? awayTeam : homeTeam;

        imageFetcher.attachImage(team.getLogoURL(), teamIcon);
        feedName.setText(String.format("%s (%s)", team.getCity(), feed.getType()));
        return view;
    }
}
