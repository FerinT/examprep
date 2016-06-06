package com.example.ferin.atm.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.ferin.atm.domain.account.impl.Student;
import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.factories.client.BusinessFactory;
import com.example.ferin.atm.repository.account.StudentRepository;
import com.example.ferin.atm.repository.account.impl.StudentRepositoryImpl;
import com.example.ferin.atm.services.client.impl.StudentServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-13.
 */
public class StudentServiceTest extends AndroidTestCase {
    public void testInsertDelete() throws Exception {

        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");
        StudentServiceImpl studentService = StudentServiceImpl.getInstance();
        Context context = getContext();
        StudentRepository businessRepository = new StudentRepositoryImpl(context);

        Student student = new Student.Builder()
                .accountNumber("123")
                .balance(new Double(900))
                .limit(new Double(500))
                .pin("123")
                .client(client)
                .build();

        studentService.startActionAdd(context, student);

        Thread.sleep(5000);
        // READ ALL
        Set<Student> businessSet1 = businessRepository.findAll();
        Assert.assertTrue(businessSet1.size() > 0);

    }
}
