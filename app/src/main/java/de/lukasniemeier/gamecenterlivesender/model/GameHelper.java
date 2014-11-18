package de.lukasniemeier.gamecenterlivesender.model;

import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.games.GameHighlightVideo;
import de.lukasniemeier.gamecenterlivesender.model.games.GameLiveVideo;

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
}
