package com.example.ferin.atm.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.account.impl.Student;
import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.factories.client.BusinessFactory;
import com.example.ferin.atm.services.account.ValidateWithdrawService;
import com.example.ferin.atm.services.account.impl.ValidateWithdrawServiceImpl;

import junit.framework.Assert;

/**
 * Created by Ferin on 2016-05-13.
 */
public class ValidateWithdrawServiceTest extends AndroidTestCase {

    private ValidateWithdrawService validateWithdrawService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ValidateWithdrawServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ValidateWithdrawServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (ValidateWithdrawServiceImpl.RetrieveAccountInfoLocalBinder) service;
            validateWithdrawService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testValidation() throws Exception {
        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");

        Student credit = new Student.Builder()
                .accountNumber("1234")
                .balance(300)
                .limit(100)
                .pin("123")
                .client(client)
                .build();
        boolean isValid = validateWithdrawService.isValidWithdraw(credit, new Double(50));

        Assert.assertTrue(isValid);
    }
}
