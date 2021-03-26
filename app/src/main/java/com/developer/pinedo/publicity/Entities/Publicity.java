package com.developer.pinedo.publicity.Entities;

public class Publicity {

    int id;
    String image;
    String type_content;
    int duration;

    public Publicity(int id, String image, String type_content, int duration) {
        this.id = id;
        this.image = image;
        this.type_content = type_content;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType_content() {
        return type_content;
    }

    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Publicity{" +
                "id=" + id +
                ", url='" + image + '\'' +
                ", type_content='" + type_content + '\'' +
                ", duration=" + duration +
                '}';
    }
}
