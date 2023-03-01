package com.example.notepad;

import io.realm.RealmObject;

public class Note extends RealmObject {
    String title;
    String description;

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

    public long getCreatedTime(long createdtime) {
        return CreatedTime;
    }

    public void setCreatedTime(long createdTime) {
        CreatedTime = createdTime;
    }

    long CreatedTime;

}
