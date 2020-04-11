package com.nmp.firstapp;

public class DataModel {
    String model;
    String type;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataModel(String model, String type) {
        this.model = model;
        this.type = type;
    }

}
