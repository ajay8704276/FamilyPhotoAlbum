package com.app.photo.album.repositories;

/**
 * Created by ajay on 15/4/18.
 */

public class RepositoryException extends Exception {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
