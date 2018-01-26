package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumMediaType;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class ObjectStockCompactDisc extends ObjectStock {

    private long runningTime;
    private String discType;
    private int discNumberOfTracks;
    private String discArtist;
    private String discTitle;
    private String discPublisher;
    private String discStorageCaseType;

    public ObjectStockCompactDisc() {
        setMediaType(EnumMediaType.COMPACTDISC);
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public int getDiscNumberOfTracks() {
        return discNumberOfTracks;
    }

    public void setDiscNumberOfTracks(int discNumberOfTracks) {
        this.discNumberOfTracks = discNumberOfTracks;
    }

    public String getDiscArtist() {
        return discArtist;
    }

    public void setDiscArtist(String discArtist) {
        this.discArtist = discArtist;
    }

    public String getDiscTitle() {
        return discTitle;
    }

    public void setDiscTitle(String discTitle) {
        this.discTitle = discTitle;
    }

    public String getDiscPublisher() {
        return discPublisher;
    }

    public void setDiscPublisher(String discPublisher) {
        this.discPublisher = discPublisher;
    }

    public String getDiscStorageCaseType() {
        return discStorageCaseType;
    }

    public void setDiscStorageCaseType(String discStorageCaseType) {
        this.discStorageCaseType = discStorageCaseType;
    }

    @Override
    public String getUnderstandableName() {
        return this.discTitle;
    }
}
