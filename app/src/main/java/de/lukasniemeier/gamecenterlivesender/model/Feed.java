package de.lukasniemeier.gamecenterlivesender.model;

/**
 * Created by Hugo on 16.11.2014.
 */
public class Feed {

    public enum FeedTarget {
        AWAY("4"),
        HOME("2"),
        UNKNOWN("0");

        private String code;

        FeedTarget(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

    }

    public enum FeedType {
        LIVE("live"),
        CONDENSED("condensed"),
        UNKNOWN("null");

        private final String code;

        FeedType(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }
    }

    private final FeedTarget feedTarget;

    private final FeedType feedType;

    public Feed(FeedTarget feedTarget, FeedType feedType) {
        this.feedTarget = feedTarget;
        this.feedType = feedType;
    }

    public FeedTarget getTarget() {
        return feedTarget;
    }

    public FeedType getType() {
        return feedType;
    }
}
