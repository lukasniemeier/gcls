package de.lukasniemeier.gamecenterlivesender.data;

import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;

public interface ReadWriteDataManager extends DataManager {

    void reset();

    void addTeams(Iterable<Team> teams);

    void addGames(Iterable<Game> games);
}
