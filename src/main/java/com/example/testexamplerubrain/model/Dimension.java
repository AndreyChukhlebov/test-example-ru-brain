package com.example.testexamplerubrain.model;

//TODO lombok,doc
public class Dimension {
    private int id;
    private int userId;
    private int gasInfo;
    private int coldWaterInfo;
    private int hotWhaterInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGasInfo() {
        return gasInfo;
    }

    public void setGasInfo(int gasInfo) {
        this.gasInfo = gasInfo;
    }

    public int getColdWaterInfo() {
        return coldWaterInfo;
    }

    public void setColdWaterInfo(int coldWaterInfo) {
        this.coldWaterInfo = coldWaterInfo;
    }

    public int getHotWhaterInfo() {
        return hotWhaterInfo;
    }

    public void setHotWhaterInfo(int hotWhaterInfo) {
        this.hotWhaterInfo = hotWhaterInfo;
    }
}
