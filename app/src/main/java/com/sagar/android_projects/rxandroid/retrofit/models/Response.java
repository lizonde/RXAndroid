package com.sagar.android_projects.rxandroid.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sagar on 11/27/2017.
 */

public class Response {
    @SerializedName("page")
    private String page;
    @SerializedName("per_page")
    private String perPage;
    @SerializedName("total")
    private String total;
    @SerializedName("total_pages")
    private String totalPages;
    @SerializedName("data")
    private ArrayList<data> data;

    @SuppressWarnings("unused")
    public Response() {
    }

    @SuppressWarnings("unused")
    public Response(String page, String perPage, String total, String totalPages,
                    ArrayList<com.sagar.android_projects.rxandroid.retrofit.models.data> data) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    @SuppressWarnings("unused")
    public void setPage(String page) {
        this.page = page;
    }

    @SuppressWarnings("unused")
    public String getPerPage() {
        return perPage;
    }

    @SuppressWarnings("unused")
    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    @SuppressWarnings("unused")
    public String getTotal() {
        return total;
    }

    @SuppressWarnings("unused")
    public void setTotal(String total) {
        this.total = total;
    }

    @SuppressWarnings("unused")
    public String getTotalPages() {
        return totalPages;
    }

    @SuppressWarnings("unused")
    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<com.sagar.android_projects.rxandroid.retrofit.models.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.sagar.android_projects.rxandroid.retrofit.models.data> data) {
        this.data = data;
    }
}
