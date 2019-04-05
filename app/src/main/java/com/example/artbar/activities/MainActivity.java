package com.example.artbar.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.artbar.R;
import com.example.artbar.utils.GlobalApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageButton btnSetting;
    private Button btnBar, btnExport;
    private GlobalApplication globalApplication = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setControlEvents();
    }

    private void initUI() {
        btnSetting = (ImageButton)findViewById(R.id.btnSetting);
        btnBar = (Button) findViewById(R.id.btnBar);
        btnExport = (Button) findViewById(R.id.btnExport);

        globalApplication = (GlobalApplication)this.getApplication();
    }

    private void setControlEvents() {
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Setting Button was clicked.");

            }
        });

        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Bar Button was clicked.");
                gotoDataScreen();
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Data Export Button was clicked.");
                if (!globalApplication.getCsvStr().equals("Customer ID,Created Time,Purchase,QTY,Unit Cost,Tokens\n")) {
                    int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }

                    saveCSVFile(globalApplication.getCsvStr());

                } else {
                    Toast.makeText(getApplicationContext(), "There is no the data to export.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void gotoDataScreen() {
        Intent mainIntent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(mainIntent);
    }

    private void saveCSVFile(String saveDateStr) {
        File file   = null;
        File root   = Environment.getExternalStorageDirectory();
        if (root.canWrite()){
            File dir    =   new File (root.getAbsolutePath() + "/Artbar_Folder");
            dir.mkdirs();
            Date currentTime = Calendar.getInstance().getTime();
            file   =   new File(dir, "Artbar_" + currentTime.toString() + ".csv");
            FileOutputStream out   =   null;
            try {
                out = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.write(saveDateStr.getBytes());
                Log.d(TAG, "------------------ CSV File is saved -------------------");
                globalApplication.setCsvStr("Customer ID,Created Time,Purchase,QTY,Unit Cost,Tokens\n");
                Toast.makeText(MainActivity.this, "The data was exported successfully.", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MainActivity.this, "The Data can not be exported. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Write to the storage (ex: call appendByteBuffer(byte[] data) here)

        } else {
            Toast.makeText(getApplicationContext(), "Please grant permission.", Toast.LENGTH_LONG).show();
        }

    }

}
