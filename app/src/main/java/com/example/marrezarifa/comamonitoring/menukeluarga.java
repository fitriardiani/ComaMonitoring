package com.example.marrezarifa.comamonitoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menukeluarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menukeluarga);
    }

    public void biopasien(View view) {
        Intent intent = new Intent(menukeluarga.this, biodatapasien.class);
        startActivity(intent);
    }
    public void fisiopasien(View view) {
        Intent intent = new Intent(menukeluarga.this, datafisiologifam.class);
        startActivity(intent);
    }
}
