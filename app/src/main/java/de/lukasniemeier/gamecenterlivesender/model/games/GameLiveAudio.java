package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

public class GameLiveAudio {

    @Expose
    private Boolean hasLiveAwayRadio;
    @Expose
    private Boolean hasLiveFrenchRadio;
    @Expose
    private Boolean hasLiveHomeRadio;

    /**
     * @return The hasLiveAwayRadio
     */
    public Boolean getHasLiveAwayRadio() {
        return hasLiveAwayRadio;
    }

    /**
     * @param hasLiveAwayRadio The hasLiveAwayRadio
     */
    public void setHasLiveAwayRadio(Boolean hasLiveAwayRadio) {
        this.hasLiveAwayRadio = hasLiveAwayRadio;
    }

    /**
     * @return The hasLiveFrenchRadio
     */
    public Boolean getHasLiveFrenchRadio() {
        return hasLiveFrenchRadio;
    }

    /**
     * @param hasLiveFrenchRadio The hasLiveFrenchRadio
     */
    public void setHasLiveFrenchRadio(Boolean hasLiveFrenchRadio) {
        this.hasLiveFrenchRadio = hasLiveFrenchRadio;
    }

    /**
     * @return The hasLiveHomeRadio
     */
    public Boolean getHasLiveHomeRadio() {
        return hasLiveHomeRadio;
    }

    /**
     * @param hasLiveHomeRadio The hasLiveHomeRadio
     */
    public void setHasLiveHomeRadio(Boolean hasLiveHomeRadio) {
        this.hasLiveHomeRadio = hasLiveHomeRadio;
    }

}
