package com.example.ferin.atm.client;

import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.client.impl.Standard;
import com.example.ferin.atm.repository.client.StandardRepository;
import com.example.ferin.atm.repository.client.impl.StandardRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-08.
 */
public class StandardRepositoryTest extends AndroidTestCase {

    private Long id;
    public void testCreateReadUpdateDelete() throws Exception {
        StandardRepository standardRepository = new StandardRepositoryImpl(this.getContext());

        // CREATE
        Standard client = new Standard.Builder()
                .name("ferin")
                .emailAddress("123@gmail")
                .idNumber("456")
                .membershipType("client")
                .build();

        Standard insertedEntity = standardRepository.save(client);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Standard> businessSet = standardRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Standard entity = standardRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Standard updateEntity = new Standard.Builder()
                .copy(entity)
                .name("taylor")
                .build();
        standardRepository.update(updateEntity);
        Standard newEntity = standardRepository.findById(id);
        Assert.assertEquals("taylor", newEntity.getName());

        // DELETE ENTITY
        standardRepository.delete(updateEntity);
        Standard deletedEntity = standardRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        standardRepository.deleteAll();
        Set<Standard> deletedUsers = standardRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }
}
