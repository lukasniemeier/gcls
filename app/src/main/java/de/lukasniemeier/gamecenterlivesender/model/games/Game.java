package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Game {

    @Expose
    private List<Object> gameBlackout = new ArrayList<Object>();
    @Expose
    private GameHighlightVideo gameHighlightVideo;
    @Expose
    private GameInformation gameInformation;
    @Expose
    private GameLiveAudio gameLiveAudio;
    @Expose
    private GameLiveVideo gameLiveVideo;
    @Expose
    private List<Object> gameSponsor = new ArrayList<Object>();
    @Expose
    private GameStory gameStory;
    @Expose
    private String gameTicketUrl;
    @Expose
    private List<GameTuneIn> gameTuneIn = new ArrayList<GameTuneIn>();
    @Expose
    private Integer id;
    @Expose
    private Integer sortOrder;

    /**
     * @return The gameBlackout
     */
    public List<Object> getGameBlackout() {
        return gameBlackout;
    }

    /**
     * @param gameBlackout The gameBlackout
     */
    public void setGameBlackout(List<Object> gameBlackout) {
        this.gameBlackout = gameBlackout;
    }

    /**
     * @return The gameHighlightVideo
     */
    public GameHighlightVideo getGameHighlightVideo() {
        return gameHighlightVideo;
    }

    /**
     * @param gameHighlightVideo The gameHighlightVideo
     */
    public void setGameHighlightVideo(GameHighlightVideo gameHighlightVideo) {
        this.gameHighlightVideo = gameHighlightVideo;
    }

    /**
     * @return The gameInformation
     */
    public GameInformation getGameInformation() {
        return gameInformation;
    }

    /**
     * @param gameInformation The gameInformation
     */
    public void setGameInformation(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    /**
     * @return The gameLiveAudio
     */
    public GameLiveAudio getGameLiveAudio() {
        return gameLiveAudio;
    }

    /**
     * @param gameLiveAudio The gameLiveAudio
     */
    public void setGameLiveAudio(GameLiveAudio gameLiveAudio) {
        this.gameLiveAudio = gameLiveAudio;
    }

    /**
     * @return The gameLiveVideo
     */
    public GameLiveVideo getGameLiveVideo() {
        return gameLiveVideo;
    }

    /**
     * @param gameLiveVideo The gameLiveVideo
     */
    public void setGameLiveVideo(GameLiveVideo gameLiveVideo) {
        this.gameLiveVideo = gameLiveVideo;
    }

    /**
     * @return The gameSponsor
     */
    public List<Object> getGameSponsor() {
        return gameSponsor;
    }

    /**
     * @param gameSponsor The gameSponsor
     */
    public void setGameSponsor(List<Object> gameSponsor) {
        this.gameSponsor = gameSponsor;
    }

    /**
     * @return The gameStory
     */
    public GameStory getGameStory() {
        return gameStory;
    }

    /**
     * @param gameStory The gameStory
     */
    public void setGameStory(GameStory gameStory) {
        this.gameStory = gameStory;
    }

    /**
     * @return The gameTicketUrl
     */
    public String getGameTicketUrl() {
        return gameTicketUrl;
    }

    /**
     * @param gameTicketUrl The gameTicketUrl
     */
    public void setGameTicketUrl(String gameTicketUrl) {
        this.gameTicketUrl = gameTicketUrl;
    }

    /**
     * @return The gameTuneIn
     */
    public List<GameTuneIn> getGameTuneIn() {
        return gameTuneIn;
    }

    /**
     * @param gameTuneIn The gameTuneIn
     */
    public void setGameTuneIn(List<GameTuneIn> gameTuneIn) {
        this.gameTuneIn = gameTuneIn;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The sortOrder
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder The sortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
