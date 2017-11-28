package com.sagar.android_projects.rxandroid.retrofit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 11/27/2017.
 * "id": 4,
 * "first_name": "Eve",
 * "last_name": "Holt",
 * "avatar"
 */
public class data {
    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String avatar;

    public data() {
    }

    public data(String id, String firstName, String lastName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SuppressWarnings("unused")
    public String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SuppressWarnings("unused")
    public String getAvatar() {
        return avatar;
    }

    @SuppressWarnings("unused")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
