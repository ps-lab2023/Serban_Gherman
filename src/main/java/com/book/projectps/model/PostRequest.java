package com.book.projectps.model;

public class PostRequest {

    private String artist;
    private String title;
    private String description;
    private String artType;
    private String type; // Assuming this is a string representation of typeOfPost enum

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
// Getters and setters
}