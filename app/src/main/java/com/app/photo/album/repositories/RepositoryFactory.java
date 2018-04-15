package com.app.photo.album.repositories;

import android.content.Context;

import com.app.photo.album.models.Album;
import com.app.photo.album.models.Photo;

/**
 * Created by ajay on 15/4/18.
 */

public class RepositoryFactory {

    private static IRepository<Photo> _photosRepository = null;
    private static IRepository<Album> _albumsRepository = null;

    public static synchronized void initialize(Context context) {
        if (_photosRepository == null) {
            _photosRepository = new FakeRepository<Photo>(context);
        }
        if (_albumsRepository == null) {
            _albumsRepository = new FakeRepository<Album>(context);
        }
    }

    /**
     * Used during testing to clear away the old repository.
     */
    static synchronized void cleanup() {
        _photosRepository = null;
        _albumsRepository = null;
    }

    public static IRepository<Photo> getPhotosRepository() throws RepositoryException {
        if (_photosRepository == null) {
            throw new RepositoryException("Repository is not initialized");
        }
        return _photosRepository;
    }

    public static IRepository<Album> getAlbumsRepository() throws RepositoryException {
        if (_albumsRepository == null) {
            throw new RepositoryException("Repository is not initialized");
        }
        return _albumsRepository;
    }
}
