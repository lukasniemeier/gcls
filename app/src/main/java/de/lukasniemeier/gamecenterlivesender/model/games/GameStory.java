package de.lukasniemeier.gamecenterlivesender.model.games;

import com.google.gson.annotations.Expose;

public class GameStory {

    @Expose
    private String storyDesc;
    @Expose
    private String storyThumbnail;
    @Expose
    private String storyTitle;
    @Expose
    private String storyType;
    @Expose
    private Integer storyVideoId;

    /**
     * @return The storyDesc
     */
    public String getStoryDesc() {
        return storyDesc;
    }

    /**
     * @param storyDesc The storyDesc
     */
    public void setStoryDesc(String storyDesc) {
        this.storyDesc = storyDesc;
    }

    /**
     * @return The storyThumbnail
     */
    public String getStoryThumbnail() {
        return storyThumbnail;
    }

    /**
     * @param storyThumbnail The storyThumbnail
     */
    public void setStoryThumbnail(String storyThumbnail) {
        this.storyThumbnail = storyThumbnail;
    }

    /**
     * @return The storyTitle
     */
    public String getStoryTitle() {
        return storyTitle;
    }

    /**
     * @param storyTitle The storyTitle
     */
    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    /**
     * @return The storyType
     */
    public String getStoryType() {
        return storyType;
    }

    /**
     * @param storyType The storyType
     */
    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    /**
     * @return The storyVideoId
     */
    public Integer getStoryVideoId() {
        return storyVideoId;
    }

    /**
     * @param storyVideoId The storyVideoId
     */
    public void setStoryVideoId(Integer storyVideoId) {
        this.storyVideoId = storyVideoId;
    }

}
