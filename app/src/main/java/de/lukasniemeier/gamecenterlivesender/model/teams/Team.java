package de.lukasniemeier.gamecenterlivesender.model.teams;

import com.google.gson.annotations.Expose;

public class Team {

    @Expose
    private String code;
    @Expose
    private String name;
    @Expose
    private String city;
    @Expose
    private String division;
    @Expose
    private String conference;
    @Expose
    private Integer teamID;
    @Expose
    private String logoURL;
    @Expose
    private String sponsors;
    @Expose
    private String ticketURL;
    @Expose
    private String compositeColor;
    @Expose
    private String venue;
    @Expose
    private String twitter;
    @Expose
    private String primaryColor;
    @Expose
    private String secondaryColor;
    @Expose
    private String liveAudio;
    @Expose
    private String audioIos;
    @Expose
    private String audioDroid;
    @Expose
    private String iosId;
    @Expose
    private String androidId;

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division The division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return The conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * @param conference The conference
     */
    public void setConference(String conference) {
        this.conference = conference;
    }

    /**
     * @return The teamID
     */
    public Integer getTeamID() {
        return teamID;
    }

    /**
     * @param teamID The teamID
     */
    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    /**
     * @return The logoURL
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * @param logoURL The logoURL
     */
    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    /**
     * @return The sponsors
     */
    public String getSponsors() {
        return sponsors;
    }

    /**
     * @param sponsors The sponsors
     */
    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

    /**
     * @return The ticketURL
     */
    public String getTicketURL() {
        return ticketURL;
    }

    /**
     * @param ticketURL The ticketURL
     */
    public void setTicketURL(String ticketURL) {
        this.ticketURL = ticketURL;
    }

    /**
     * @return The compositeColor
     */
    public String getCompositeColor() {
        return compositeColor;
    }

    /**
     * @param compositeColor The compositeColor
     */
    public void setCompositeColor(String compositeColor) {
        this.compositeColor = compositeColor;
    }

    /**
     * @return The venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue The venue
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return The primaryColor
     */
    public String getPrimaryColor() {
        return primaryColor;
    }

    /**
     * @param primaryColor The primaryColor
     */
    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    /**
     * @return The secondaryColor
     */
    public String getSecondaryColor() {
        return secondaryColor;
    }

    /**
     * @param secondaryColor The secondaryColor
     */
    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    /**
     * @return The liveAudio
     */
    public String getLiveAudio() {
        return liveAudio;
    }

    /**
     * @param liveAudio The liveAudio
     */
    public void setLiveAudio(String liveAudio) {
        this.liveAudio = liveAudio;
    }

    /**
     * @return The audioIos
     */
    public String getAudioIos() {
        return audioIos;
    }

    /**
     * @param audioIos The audioIos
     */
    public void setAudioIos(String audioIos) {
        this.audioIos = audioIos;
    }

    /**
     * @return The audioDroid
     */
    public String getAudioDroid() {
        return audioDroid;
    }

    /**
     * @param audioDroid The audioDroid
     */
    public void setAudioDroid(String audioDroid) {
        this.audioDroid = audioDroid;
    }

    /**
     * @return The iosId
     */
    public String getIosId() {
        return iosId;
    }

    /**
     * @param iosId The iosId
     */
    public void setIosId(String iosId) {
        this.iosId = iosId;
    }

    /**
     * @return The androidId
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * @param androidId The androidId
     */
    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

}
