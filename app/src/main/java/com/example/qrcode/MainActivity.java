package com.example.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button gen, scan1, scan2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gen = (Button) findViewById(R.id.gen);
        scan1 = (Button) findViewById(R.id.scan1);
        scan2 = (Button) findViewById(R.id.scan2);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gen = new Intent(MainActivity.this, GeneratorActivity.class);
                startActivity(intent_gen);
            }
        });
        scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentScanSatu = new Intent(MainActivity.this, ReaderVersiSatu.class);
                startActivity(intentScanSatu);
            }
        });
        scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScanDua = new Intent(MainActivity.this, ReaderVersiDua.class);
                startActivity(intentScanDua);
            }
        });
    }
}
