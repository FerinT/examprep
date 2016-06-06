package com.example.ferin.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void invokeEvent(View v)
    {
        Intent intent = new Intent(this, AddClientActivity.class);
        startActivity(intent);
    }

    public void viewAll(View v)
    {
        Intent intent = new Intent(this, DisplayAllActivity.class);
        startActivity(intent);
    }
}
