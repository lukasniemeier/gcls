package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class GameInformation {

    @Expose
    private AwayTeam awayTeam;
    @Expose
    private String currentGameTime;
    @Expose
    private String easternGameTime;
    @Expose
    private Integer gameId;
    @Expose
    private String gameText;
    @Expose
    private Integer gs;
    @Expose
    private Boolean hasPPD;
    @Expose
    private Boolean hasTBD;
    @Expose
    private HomeTeam homeTeam;
    @Expose
    private List<String> linescoreLabels = new ArrayList<String>();

    /**
     * @return The awayTeam
     */
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    /**
     * @param awayTeam The awayTeam
     */
    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * @return The currentGameTime
     */
    public String getCurrentGameTime() {
        return currentGameTime;
    }

    /**
     * @param currentGameTime The currentGameTime
     */
    public void setCurrentGameTime(String currentGameTime) {
        this.currentGameTime = currentGameTime;
    }

    /**
     * @return The easternGameTime
     */
    public String getEasternGameTime() {
        return easternGameTime;
    }

    /**
     * @param easternGameTime The easternGameTime
     */
    public void setEasternGameTime(String easternGameTime) {
        this.easternGameTime = easternGameTime;
    }

    /**
     * @return The gameId
     */
    public Integer getGameId() {
        return gameId;
    }

    /**
     * @param gameId The gameId
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return The gameText
     */
    public String getGameText() {
        return gameText;
    }

    /**
     * @param gameText The gameText
     */
    public void setGameText(String gameText) {
        this.gameText = gameText;
    }

    /**
     * @return The gs
     */
    public Integer getGs() {
        return gs;
    }

    /**
     * @param gs The gs
     */
    public void setGs(Integer gs) {
        this.gs = gs;
    }

    /**
     * @return The hasPPD
     */
    public Boolean getHasPPD() {
        return hasPPD;
    }

    /**
     * @param hasPPD The hasPPD
     */
    public void setHasPPD(Boolean hasPPD) {
        this.hasPPD = hasPPD;
    }

    /**
     * @return The hasTBD
     */
    public Boolean getHasTBD() {
        return hasTBD;
    }

    /**
     * @param hasTBD The hasTBD
     */
    public void setHasTBD(Boolean hasTBD) {
        this.hasTBD = hasTBD;
    }

    /**
     * @return The homeTeam
     */
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    /**
     * @param homeTeam The homeTeam
     */
    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * @return The linescoreLabels
     */
    public List<String> getLinescoreLabels() {
        return linescoreLabels;
    }

    /**
     * @param linescoreLabels The linescoreLabels
     */
    public void setLinescoreLabels(List<String> linescoreLabels) {
        this.linescoreLabels = linescoreLabels;
    }

}
