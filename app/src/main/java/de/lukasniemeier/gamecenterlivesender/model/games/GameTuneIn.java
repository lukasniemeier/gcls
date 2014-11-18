package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

public class GameTuneIn {

    @Expose
    private String country;
    @Expose
    private String localText;
    @Expose
    private String nationalLogoUrl;
    @Expose
    private String nationalTargetUrl;
    @Expose
    private String nationalText;

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The localText
     */
    public String getLocalText() {
        return localText;
    }

    /**
     * @param localText The localText
     */
    public void setLocalText(String localText) {
        this.localText = localText;
    }

    /**
     * @return The nationalLogoUrl
     */
    public String getNationalLogoUrl() {
        return nationalLogoUrl;
    }

    /**
     * @param nationalLogoUrl The nationalLogoUrl
     */
    public void setNationalLogoUrl(String nationalLogoUrl) {
        this.nationalLogoUrl = nationalLogoUrl;
    }

    /**
     * @return The nationalTargetUrl
     */
    public String getNationalTargetUrl() {
        return nationalTargetUrl;
    }

    /**
     * @param nationalTargetUrl The nationalTargetUrl
     */
    public void setNationalTargetUrl(String nationalTargetUrl) {
        this.nationalTargetUrl = nationalTargetUrl;
    }

    /**
     * @return The nationalText
     */
    public String getNationalText() {
        return nationalText;
    }

    /**
     * @param nationalText The nationalText
     */
    public void setNationalText(String nationalText) {
        this.nationalText = nationalText;
    }

}
