package com.app.photo.album.repositories;

import android.content.Context;

import com.app.photo.album.models.BaseModel;
import com.app.photo.album.models.Photo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ajay on 15/4/18.
 */

/**
 * An in-memory repository.  The data goes away when the app is restarted
 *
 * @param <T> the type that the repository holds.
 */
public class FakeRepository<T extends BaseModel> implements IRepository<T> {
    private List<T> items = new ArrayList<>();

    public FakeRepository(Context context) {
        /* Do nothing */
    }

    @Override
    public T saveItem(T record) throws RepositoryException {
        /* Update the record so that the updated value is set */
        record.setUpdated(System.currentTimeMillis());
        int index = items.indexOf(record);
        if (index == -1) {
            items.add(record);
        } else {
            items.set(index, record);
        }
        return record;
    }

    @Override
    public void removeItem(T record) throws RepositoryException {
        if (!items.remove(record)) {
            throw new ItemMissingException(String.format("Item %s does not exist", record.getId()));
        }
    }

    @Override
    public T getItem(String id) throws RepositoryException {
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getId() == id) {
                return item;
            }
        }
        throw new ItemMissingException(String.format("Item %s does not exist", id));
    }

    @Override
    public List<T> getItems() throws RepositoryException {
        return items;
    }

    @Override
    public List<T> getItems(int start, int count) throws RepositoryException {
        if (start > items.size()) {
            return new ArrayList<>();
        }
        int endIndex = (start + count > items.size()) ? items.size() : start + count;
        return items.subList(start, endIndex);
    }

    @Override
    public int getLength() throws RepositoryException {
        return items.size();
    }
}
