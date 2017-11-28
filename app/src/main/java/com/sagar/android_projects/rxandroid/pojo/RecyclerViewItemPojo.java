package com.sagar.android_projects.rxandroid.pojo;

/**
 * Created by sagar on 11/27/2017.
 */

public class RecyclerViewItemPojo {
    private String itemName;

    @SuppressWarnings("unused")
    public RecyclerViewItemPojo() {
    }

    public RecyclerViewItemPojo(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @SuppressWarnings("unused")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
