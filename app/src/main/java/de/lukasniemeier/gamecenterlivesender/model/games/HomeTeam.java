package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

public class HomeTeam {

    @Expose
    private String teamAbb;
    @Expose
    private String teamCity;
    @Expose
    private String teamCustomLogoUrl;
    @Expose
    private Integer teamId;
    @Expose
    private String teamName;
    @Expose
    private Integer teamScore;

    /**
     * @return The teamAbb
     */
    public String getTeamAbb() {
        return teamAbb;
    }

    /**
     * @param teamAbb The teamAbb
     */
    public void setTeamAbb(String teamAbb) {
        this.teamAbb = teamAbb;
    }

    /**
     * @return The teamCity
     */
    public String getTeamCity() {
        return teamCity;
    }

    /**
     * @param teamCity The teamCity
     */
    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    /**
     * @return The teamCustomLogoUrl
     */
    public String getTeamCustomLogoUrl() {
        return teamCustomLogoUrl;
    }

    /**
     * @param teamCustomLogoUrl The teamCustomLogoUrl
     */
    public void setTeamCustomLogoUrl(String teamCustomLogoUrl) {
        this.teamCustomLogoUrl = teamCustomLogoUrl;
    }

    /**
     * @return The teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @param teamId The teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @return The teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName The teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return The teamScore
     */
    public Integer getTeamScore() {
        return teamScore;
    }

    /**
     * @param teamScore The teamScore
     */
    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

}
