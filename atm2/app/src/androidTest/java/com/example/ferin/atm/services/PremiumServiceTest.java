package com.example.ferin.atm.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.client.impl.Premium;
import com.example.ferin.atm.repository.client.PremiumRepository;
import com.example.ferin.atm.repository.client.impl.PremiumRepositoryImpl;
import com.example.ferin.atm.services.client.impl.PremiumServiceImpl;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ferin on 2016-05-13.
 */
public class PremiumServiceTest extends AndroidTestCase {

    public void testInsertDelete() throws Exception {

        PremiumServiceImpl premiumService = PremiumServiceImpl.getInstance();
        Context context = getContext();
        PremiumRepository businessRepository = new PremiumRepositoryImpl(context);

        Premium premium = new Premium.Builder()
                .membershipType("1")
                .name("1")
                .idNumber("1")
                .emailAddress("1")
                .build();

        premiumService.startActionInsert(context, premium);

        Thread.sleep(5000);
        // READ ALL
        Set<Premium> businessSet1 = businessRepository.findAll();
        Assert.assertTrue(businessSet1.size() > 0);

        List<Premium> savingsList = new ArrayList<>(businessSet1);

        premiumService.startActionDelete(context, savingsList.get(0));

        Thread.sleep(5000);
        // READ ALL
        Set<Premium> savingsSet1 = businessRepository.findAll();
        Assert.assertTrue(savingsSet1.size() == 0);
    }
}
