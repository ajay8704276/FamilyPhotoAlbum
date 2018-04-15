package com.app.photo.album.models;

import java.util.UUID;

/**
 * Created by ajay on 15/4/18.
 */

public class Album extends BaseModel {
    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
