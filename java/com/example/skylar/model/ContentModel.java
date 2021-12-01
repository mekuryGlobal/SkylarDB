package com.example.skylar.model;

import java.util.ArrayList;

public class ContentModel {
    private int totalResults;
    private String Status;
    private ArrayList<CourseModel> articles;

    public ContentModel(int totalResults, String status, ArrayList<CourseModel> articles) {
        this.totalResults = totalResults;
        Status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<CourseModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<CourseModel> articles) {
        this.articles = articles;
    }
}
