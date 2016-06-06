package com.example.ferin.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.repository.account.CreditRepository;
import com.example.ferin.atm.repository.account.impl.CreditRepositoryImpl;
import com.example.ferin.atm.repository.client.BusinessRepository;
import com.example.ferin.atm.repository.client.impl.BusinessRepositoryImpl;

public class ConfirmActivity extends AppCompatActivity {

    Business business;
    Credit credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

            Bundle extras = getIntent().getExtras();
            business = (Business)extras.getSerializable("CLIENT");
            credit = (Credit)extras.getSerializable("ACCOUNT");
            ((TextView)findViewById(R.id.txtName)).setText(business.getName());

    }

    public void saveToDB(View v)
    {
        BusinessRepository businessRepository = new BusinessRepositoryImpl(getBaseContext());
        CreditRepository creditRepository = new CreditRepositoryImpl(getBaseContext());

        businessRepository.save(business);
        creditRepository.save(credit);

        Intent intent = new Intent(this, DisplayAllActivity.class);
        startActivity(intent);
    }
}
