package de.lukasniemeier.gamecenterlivesender.model;

import de.lukasniemeier.gamecenterlivesender.model.games.AwayTeam;
import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.games.GameHighlightVideo;
import de.lukasniemeier.gamecenterlivesender.model.games.GameInformation;
import de.lukasniemeier.gamecenterlivesender.model.games.GameLiveVideo;
import de.lukasniemeier.gamecenterlivesender.model.games.HomeTeam;

public class GameHelper {

    public static boolean gameHasProgram(Game game) {
        if (hasArchive(game)) return true;
        return gameIsLive(game);
    }

    private static boolean hasArchive(Game game) {
        GameHighlightVideo highlights = game.getGameHighlightVideo();
        if (highlights != null) {
            return Boolean.TRUE.equals(highlights.getHasArchiveAwayVideo()) ||
                    Boolean.TRUE.equals(highlights.getHasArchiveHomeVideo());
        }
        return false;
    }

    public static boolean gameIsLive(Game game) {
        GameLiveVideo video = game.getGameLiveVideo();
        if (video != null) {
            return Boolean.TRUE.equals(video.getHasLiveAwayVideo()) ||
                    Boolean.TRUE.equals(video.getHasLiveHomeVideo());
        }
        return false;
    }

    public static Game fakeGame(boolean isLive) {
        Game customGame = new Game();

        GameInformation information = new GameInformation();
        AwayTeam awayTeam = new AwayTeam();
        awayTeam.setTeamName("NN");
        HomeTeam homeTeam = new HomeTeam();
        homeTeam.setTeamName("NN");
        information.setAwayTeam(awayTeam);
        information.setHomeTeam(homeTeam);
        customGame.setGameInformation(information);

        GameLiveVideo liveVideo = new GameLiveVideo();
        liveVideo.setHasLiveAwayVideo(isLive);
        customGame.setGameLiveVideo(liveVideo);
        return customGame;
    }
}
