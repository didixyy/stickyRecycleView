package com.unovo.views.stickytitlerecyclerview;

/**
 * Created by User on 2017/7/21.
 */

public class DetailBean {

    private int type;
    private String name;
    private String villager=null;
    private String build=null;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillager() {
        return villager;
    }

    public void setVillager(String villager) {
        this.villager = villager;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }
}
