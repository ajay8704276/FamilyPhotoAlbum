package com.app.photo.album.repositories;

/**
 * Created by ajay on 15/4/18.
 */

class ItemMissingException extends RepositoryException {
    public ItemMissingException(String message) {
        super(message);
    }
}
