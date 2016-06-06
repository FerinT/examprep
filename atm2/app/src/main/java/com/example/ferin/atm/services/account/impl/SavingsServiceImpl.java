package com.example.ferin.atm.services.account.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.repository.account.SavingsRepository;
import com.example.ferin.atm.repository.account.impl.SavingsRepositoryImpl;


public class SavingsServiceImpl extends IntentService {

    private static final String ACTION_ADD = "com.example.ferin.atm.services.account.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.example.ferin.atm.services.account.impl.action.UPDATE";
    private static final String ACTION_DELETE = "com.example.ferin.atm.services.account.impl.action.DELETE";

    private static final String EXTRA_ADD = "com.example.ferin.atm.services.account.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.example.ferin.atm.services.account.impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "com.example.ferin.atm.services.account.impl.extra.DELETE";

    public SavingsServiceImpl() {
        super("SavingsServiceImpl");
    }

    private static SavingsServiceImpl service = null;

    public static SavingsServiceImpl getInstance() {
        if (service == null)
            service = new SavingsServiceImpl();
        return service;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Savings savings = (Savings)intent.getSerializableExtra(EXTRA_ADD);
                insertSavingsAccount(savings);
            } else if (ACTION_UPDATE.equals(action)) {
                final Savings savings = (Savings)intent.getSerializableExtra(EXTRA_UPDATE);
                updateSavingsAccount(savings);
            }
            else if (ACTION_DELETE.equals(action)) {
                final Savings savings = (Savings)intent.getSerializableExtra(EXTRA_DELETE);
                deleteSavingsAccount(savings);
            }
        }
    }

    public static void startActionInsert(Context context, Savings savings) {
        Intent intent = new Intent(context, SavingsServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, savings);
        context.startService(intent);
    }

    public static void startActionUpdate(Context context, Savings savings) {
        Intent intent = new Intent(context, SavingsServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, savings);
        context.startService(intent);
    }


    public static void startActionDelete(Context context, Savings savings) {
        Intent intent = new Intent(context, SavingsServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, savings);
        context.startService(intent);
    }

    private void insertSavingsAccount(Savings savings) {
        SavingsRepository savingsRepository = new SavingsRepositoryImpl(getBaseContext());
        savingsRepository.save(savings);
    }


    private void updateSavingsAccount(Savings savings) {
        SavingsRepository savingsRepository = new SavingsRepositoryImpl(getBaseContext());
        savingsRepository.update(savings);
    }

    private void deleteSavingsAccount(Savings savings) {
        SavingsRepository savingsRepository = new SavingsRepositoryImpl(getBaseContext());
        savingsRepository.delete(savings);
    }
}
