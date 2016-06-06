package com.example.ferin.atm.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.factories.client.BusinessFactory;
import com.example.ferin.atm.repository.account.SavingsRepository;
import com.example.ferin.atm.repository.account.impl.SavingsRepositoryImpl;
import com.example.ferin.atm.services.account.impl.SavingsServiceImpl;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SavingsServiceTest extends AndroidTestCase {

    public void testInsertDelete() throws Exception {

        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");
        SavingsServiceImpl savingsService = SavingsServiceImpl.getInstance();
        Context context = getContext();

        SavingsRepository savingsRepository = new SavingsRepositoryImpl(context);

        Savings savings = new Savings.Builder()
                .accountNumber("123")
                .balance(new Double(900))
                .limit(new Double(500))
                .pin("123")
                .client(client)
                .build();

        savingsService.startActionInsert(context, savings);

        Thread.sleep(5000);
        // READ ALL
        Set<Savings> savingsSet = savingsRepository.findAll();
        Assert.assertTrue(savingsSet.size() > 0);

        List<Savings> savingsList = new ArrayList<>(savingsSet);

        savingsService.startActionDelete(context, savingsList.get(0));

        Thread.sleep(5000);
        // READ ALL
        Set<Savings> savingsSet1 = savingsRepository.findAll();
        Assert.assertTrue(savingsSet1.size() == 0);
    }

}
