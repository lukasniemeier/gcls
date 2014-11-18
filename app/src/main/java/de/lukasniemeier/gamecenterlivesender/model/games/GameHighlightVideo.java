package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

public class GameHighlightVideo {

    @Expose
    private Boolean hasArchiveAwayVideo;
    @Expose
    private Boolean hasArchiveFrenchVideo;
    @Expose
    private Boolean hasArchiveHomeVideo;
    @Expose
    private Boolean hasCondensedVideo;
    @Expose
    private Boolean hasFullGameVideo;

    /**
     * @return The hasArchiveAwayVideo
     */
    public Boolean getHasArchiveAwayVideo() {
        return hasArchiveAwayVideo;
    }

    /**
     * @param hasArchiveAwayVideo The hasArchiveAwayVideo
     */
    public void setHasArchiveAwayVideo(Boolean hasArchiveAwayVideo) {
        this.hasArchiveAwayVideo = hasArchiveAwayVideo;
    }

    /**
     * @return The hasArchiveFrenchVideo
     */
    public Boolean getHasArchiveFrenchVideo() {
        return hasArchiveFrenchVideo;
    }

    /**
     * @param hasArchiveFrenchVideo The hasArchiveFrenchVideo
     */
    public void setHasArchiveFrenchVideo(Boolean hasArchiveFrenchVideo) {
        this.hasArchiveFrenchVideo = hasArchiveFrenchVideo;
    }

    /**
     * @return The hasArchiveHomeVideo
     */
    public Boolean getHasArchiveHomeVideo() {
        return hasArchiveHomeVideo;
    }

    /**
     * @param hasArchiveHomeVideo The hasArchiveHomeVideo
     */
    public void setHasArchiveHomeVideo(Boolean hasArchiveHomeVideo) {
        this.hasArchiveHomeVideo = hasArchiveHomeVideo;
    }

    public Boolean getHasCondensedVideo() {
        return hasCondensedVideo;
    }

    public void setHasCondensedVideo(Boolean hasCondensedVideo) {
        this.hasCondensedVideo = hasCondensedVideo;
    }

    /**
     * @return The hasFullGameVideo
     */
    public Boolean getHasFullGameVideo() {
        return hasFullGameVideo;
    }

    /**
     * @param hasFullGameVideo The hasFullGameVideo
     */
    public void setHasFullGameVideo(Boolean hasFullGameVideo) {
        this.hasFullGameVideo = hasFullGameVideo;
    }

}
