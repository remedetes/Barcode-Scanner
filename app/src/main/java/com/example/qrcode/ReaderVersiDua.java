package com.example.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReaderVersiDua extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private ZXingScannerView zXingScannerView;
    private int CAMERA_PERMISSION = 1;
    private boolean mAutoFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_versi_dua);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
        Log.v("TAG", result.getText()); // Prints scan results
//        Log.v("TAG", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");
//        builder.setMessage(result.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
        zXingScannerView.resumeCameraPreview(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                Toast.makeText(this, "Akses Kamera ok", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Akses Kamera ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
