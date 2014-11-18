package de.lukasniemeier.gamecenterlivesender.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;

public class InMemoryDataManager implements DataManager, ReadWriteDataManager {

    private List<Game> games;

    private Map<String, Team> teamMap;

    public InMemoryDataManager(List<Game> games, Map<String, Team> teamMap) {
        this.games = games;
        this.teamMap = teamMap;
    }

    public InMemoryDataManager() {
        this(new ArrayList<Game>(), new HashMap<String, Team>());
    }

    @Override
    public List<Game> getGames() {
        return games;
    }

    @Override
    public Collection<Team> getTeams() {
        return teamMap.values();
    }

    @Override
    public Team getTeam(String teamCode) {
        return teamMap.get(teamCode);
    }

    @Override
    public void reset() {
        this.games.clear();
        this.teamMap.clear();
    }

    @Override
    public void addTeams(Iterable<Team> teams) {
        for (Team team : teams) {
            teamMap.put(team.getCode(), team);
        }
    }

    @Override
    public void addGames(Iterable<Game> games) {
        for (Game game : games) {
            this.games.add(game);
        }
    }
}
