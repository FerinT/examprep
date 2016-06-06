package com.example.ferin.atm;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.domain.account.impl.Student;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.domain.client.impl.Premium;
import com.example.ferin.atm.domain.client.impl.Standard;
import com.example.ferin.atm.factories.account.CreditFactory;
import com.example.ferin.atm.factories.account.SavingsFactory;
import com.example.ferin.atm.factories.account.StudentFactory;
import com.example.ferin.atm.factories.client.BusinessFactory;
import com.example.ferin.atm.factories.client.PremiumFactory;
import com.example.ferin.atm.factories.client.StandardFactory;
import com.example.ferin.atm.repository.account.CreditRepository;
import com.example.ferin.atm.repository.account.SavingsRepository;
import com.example.ferin.atm.repository.account.StudentRepository;
import com.example.ferin.atm.repository.account.impl.CreditRepositoryImpl;
import com.example.ferin.atm.repository.account.impl.SavingsRepositoryImpl;
import com.example.ferin.atm.repository.account.impl.StudentRepositoryImpl;
import com.example.ferin.atm.repository.client.BusinessRepository;
import com.example.ferin.atm.repository.client.PremiumRepository;
import com.example.ferin.atm.repository.client.StandardRepository;
import com.example.ferin.atm.repository.client.impl.BusinessRepositoryImpl;
import com.example.ferin.atm.repository.client.impl.PremiumRepositoryImpl;
import com.example.ferin.atm.repository.client.impl.StandardRepositoryImpl;

import junit.framework.Assert;


public class PopulateDatabase extends AndroidTestCase{

    public void insertData() throws Exception {
        Context context = getContext();

        Business clientBusiness = BusinessFactory.createBusinessClient("9403300", "Ferin", "FerinTaylor@gmail.com");
        Credit accountCredit = CreditFactory.createCredit("123456", new Double(15000.0), new Double(5000.00), "123", clientBusiness);


        Premium clientPremium = PremiumFactory.createBusinessClient("9403300", "Ferin", "FerinTaylor@gmail.com");
        Savings accountSavings = SavingsFactory.createCredit("123456", new Double(15000.0), new Double(5000.00), "123", clientPremium);


        Standard clientStandard = StandardFactory.createBusinessClient("9403300", "Ferin", "FerinTaylor@gmail.com");
        Student accountStudent = StudentFactory.createCredit("123456", new Double(15000.0), new Double(5000.00), "123", clientStandard);

        CreditRepository cr = new CreditRepositoryImpl(context);
        SavingsRepository sr = new SavingsRepositoryImpl(context);
        StudentRepository studr = new StudentRepositoryImpl(context);

        BusinessRepository br = new BusinessRepositoryImpl(context);
        PremiumRepository pr = new PremiumRepositoryImpl(context);
        StandardRepository standardRepository = new StandardRepositoryImpl(context);

        br.save(clientBusiness);
        pr.save(clientPremium);
        standardRepository.save(clientStandard);

        cr.save(accountCredit);
        sr.save(accountSavings);
        studr.save(accountStudent);

        Thread.sleep(5000);

        Assert.assertTrue(br.findAll().size() > 0);

    }
}
