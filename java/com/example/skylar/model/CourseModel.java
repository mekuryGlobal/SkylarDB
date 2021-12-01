package com.example.skylar.model;

public class CourseModel {
    private String title, description, urlToImage, content;

    public CourseModel(String title, String description, String urlToImage, String content) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
