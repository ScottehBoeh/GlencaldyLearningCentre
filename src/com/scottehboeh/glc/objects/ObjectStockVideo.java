package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumMediaType;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class ObjectStockVideo extends ObjectStock {

    private long runningTime;
    private String videoFormat;
    private String videoGenre;
    private String videoTitle;
    private String videoPublisher;
    private String videoStorageCaseType;

    public ObjectStockVideo() {
        setMediaType(EnumMediaType.VIDEO);
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public String getVideoGenre() {
        return videoGenre;
    }

    public void setVideoGenre(String videoGenre) {
        this.videoGenre = videoGenre;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPublisher() {
        return videoPublisher;
    }

    public void setVideoPublisher(String videoPublisher) {
        this.videoPublisher = videoPublisher;
    }

    public String getVideoStorageCaseType() {
        return videoStorageCaseType;
    }

    public void setVideoStorageCaseType(String videoStorageCaseType) {
        this.videoStorageCaseType = videoStorageCaseType;
    }

    @Override
    public String getUnderstandableName() {
        return this.videoTitle;
    }
}
