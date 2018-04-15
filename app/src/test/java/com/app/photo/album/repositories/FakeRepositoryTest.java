package com.app.photo.album.repositories;


import com.app.photo.album.models.BaseModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ajay on 15/4/18.
 */
class TestModel extends BaseModel {
    private String test;

    public String getTest() { return test; }
    public void setTest(String test) { this.test = test; }
}

public class FakeRepositoryTest {
    @Test
    public void newRepositoryShouldBeEmpty() throws RepositoryException {
        FakeRepository<TestModel> repository = new FakeRepository<>(null);
        assertEquals("Repository is not empty", 0, repository.getLength());
    }

    @Test (expected = NullPointerException.class)
    public void saveNullItemShouldThrow() throws RepositoryException {
        FakeRepository<TestModel> repository = new FakeRepository<>(null);
        repository.saveItem(null);
    }

    @Test
    public void saveItemShouldBeStored() throws RepositoryException {
        FakeRepository<TestModel> repository = new FakeRepository<>(null);
        TestModel r = new TestModel();
        TestModel t = repository.saveItem(r);
        long c = System.currentTimeMillis();
        assertEquals("Record was not inserted", 1, repository.getLength());
        assertTrue("Updated was not updated", c - t.getUpdated() < 50);
    }

    @Test
    public void saveSameItemShouldUpdate() throws RepositoryException {
        FakeRepository<TestModel> repository = new FakeRepository<>(null);
        TestModel r = new TestModel();
        TestModel t = repository.saveItem(r);
        t.setTest("Update");
        TestModel u = repository.saveItem(t);
        long c = System.currentTimeMillis();
        assertEquals("Record was not inserted", 1, repository.getLength());
        assertTrue("Updated was not updated", c - t.getUpdated() < 50);
    }

    @Test
    public void saveDifferentItemsShouldAdd() throws RepositoryException {
        FakeRepository<TestModel> repository = new FakeRepository<>(null);
        TestModel r = new TestModel();
        TestModel t = repository.saveItem(r);
        TestModel u = new TestModel();
        TestModel u2 = repository.saveItem(u);
        assertEquals("Record was not inserted", 2, repository.getLength());
    }
}