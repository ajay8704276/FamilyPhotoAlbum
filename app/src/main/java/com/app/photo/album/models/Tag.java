package com.app.photo.album.models;

import java.util.UUID;

/**
 * Created by ajay on 15/4/18.
 */

public class Tag extends BaseModel{

    private String tagId;
    private String tagName;

    public Tag() {
        tagId = UUID.randomUUID().toString();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
