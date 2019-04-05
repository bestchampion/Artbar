package com.example.artbar.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artbar.R;
import com.example.artbar.utils.GlobalApplication;

import java.util.Calendar;
import java.util.Date;

public class DataActivity extends AppCompatActivity {

    private static final String TAG = "DataActivity";
    private ImageButton btnBarcode;
    public static EditText editTxtCustomerID;
    private TextView txtViewTokens, txtViewQTY, txtViewPrice, txtViewHalfPint, txtViewPint, txtViewBeer, txtViewProsecco, txtViewSpirit, txtViewGT, txtViewSoft, txtViewExtra, btnHalfPint, btnPint, btnBeer, btnProsecco, btnSpirit, btnGT, btnSoft, btnExtra;
    private Button btnBack, btnClear, btnNext;
    private RelativeLayout LayoutHalfPint, LayoutPint, LayoutBeer, LayoutProsecco, LayoutSpirit, LayoutGT, LayoutSoft, LayoutExtra;
    private GlobalApplication globalApplication = null;
    private boolean inputCheck;

    private String customerStr="";
    private int HalfPintAmount, PintAmount, BeerAmount, ProseccoAmount, SpiritAmount, GTAmount, SoftAmount, ExtraAmount, tokens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_data);

        initUI();

        setControlEvents();
    }

    private void initUI() {
        btnBarcode = (ImageButton)findViewById(R.id.btnBarcode);
        btnHalfPint = (TextView) findViewById(R.id.btnHalfPint);
        btnPint = (TextView) findViewById(R.id.btnPint);
        btnBeer = (TextView) findViewById(R.id.btnBeer);
        btnProsecco = (TextView) findViewById(R.id.btnProsecco);
        btnSpirit = (TextView) findViewById(R.id.btnSpirit);
        btnGT = (TextView) findViewById(R.id.btnGT);
        btnSoft = (TextView) findViewById(R.id.btnSoft);
        btnExtra = (TextView) findViewById(R.id.btnExtra);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnNext = (Button) findViewById(R.id.btnNext);

        editTxtCustomerID = (EditText) findViewById(R.id.editTxtCustomerID);

        txtViewTokens = (TextView) findViewById(R.id.txtViewTokens);
        txtViewQTY = (TextView) findViewById(R.id.txtViewQTY);
        txtViewPrice = (TextView) findViewById(R.id.txtViewPrice);
        txtViewHalfPint = (TextView) findViewById(R.id.txtViewHalfPint);
        txtViewPint = (TextView) findViewById(R.id.txtViewPint);
        txtViewBeer = (TextView) findViewById(R.id.txtViewBeer);
        txtViewProsecco = (TextView) findViewById(R.id.txtViewProsecco);
        txtViewSpirit = (TextView) findViewById(R.id.txtViewSpirit);
        txtViewGT = (TextView) findViewById(R.id.txtViewGT);
        txtViewSoft = (TextView) findViewById(R.id.txtViewSoft);
        txtViewExtra = (TextView) findViewById(R.id.txtViewExtra);

        LayoutHalfPint = (RelativeLayout) findViewById(R.id.LayoutHalfPint);
        LayoutPint = (RelativeLayout) findViewById(R.id.LayoutPint);
        LayoutBeer = (RelativeLayout) findViewById(R.id.LayoutBeer);
        LayoutProsecco = (RelativeLayout) findViewById(R.id.LayoutProsecco);
        LayoutSpirit = (RelativeLayout) findViewById(R.id.LayoutSpirit);
        LayoutGT = (RelativeLayout) findViewById(R.id.LayoutGT);
        LayoutSoft = (RelativeLayout) findViewById(R.id.LayoutSoft);
        LayoutExtra = (RelativeLayout) findViewById(R.id.LayoutExtra);

        globalApplication = (GlobalApplication)this.getApplication();

        editTxtCustomerID.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (editTxtCustomerID.getText().length() == 4) {
                    customerStr = editTxtCustomerID.getText().toString();
                }

                if (editTxtCustomerID.getText().length() > 4) {
                    editTxtCustomerID.setText(customerStr);
                }
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        getAmount();
    }

    private void getAmount(){
        if (btnHalfPint.getText().toString().substring(0) == "0") {
            HalfPintAmount = Integer.valueOf(btnHalfPint.getText().toString().substring(1));
        } else {
            HalfPintAmount = Integer.valueOf(btnHalfPint.getText().toString());
        }

        if (btnPint.getText().toString().substring(0) == "0") {
            PintAmount = Integer.valueOf(btnPint.getText().toString().substring(1));
        } else {
            PintAmount = Integer.valueOf(btnPint.getText().toString());
        }

        if (btnBeer.getText().toString().substring(0) == "0") {
            BeerAmount = Integer.valueOf(btnBeer.getText().toString().substring(1));
        } else {
            BeerAmount = Integer.valueOf(btnBeer.getText().toString());
        }

        if (btnProsecco.getText().toString().substring(0) == "0") {
            ProseccoAmount = Integer.valueOf(btnProsecco.getText().toString().substring(1));
        } else {
            ProseccoAmount = Integer.valueOf(btnProsecco.getText().toString());
        }

        if (btnSpirit.getText().toString().substring(0) == "0") {
            SpiritAmount = Integer.valueOf(btnSpirit.getText().toString().substring(1));
        } else {
            SpiritAmount = Integer.valueOf(btnSpirit.getText().toString());
        }

        if (btnGT.getText().toString().substring(0) == "0") {
            GTAmount = Integer.valueOf(btnGT.getText().toString().substring(1));
        } else {
            GTAmount = Integer.valueOf(btnGT.getText().toString());
        }

        if (btnSoft.getText().toString().substring(0) == "0") {
            SoftAmount = Integer.valueOf(btnSoft.getText().toString().substring(1));
        } else {
            SoftAmount = Integer.valueOf(btnSoft.getText().toString());
        }

        if (btnExtra.getText().toString().substring(0) == "0") {
            ExtraAmount = Integer.valueOf(btnExtra.getText().toString().substring(1));
        } else {
            ExtraAmount = Integer.valueOf(btnExtra.getText().toString());
        }

        tokens = (int) (Float.valueOf(txtViewPrice.getText().toString().substring(1))/1.5);
        txtViewTokens.setText("TOKENS " + String.valueOf(tokens));
    }


    private void setControlEvents() {
        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Barcode Button was clicked.");
                int permission = ActivityCompat.checkSelfPermission(DataActivity.this, Manifest.permission.CAMERA);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(DataActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                }

                gotoScanScreen();
            }
        });

        LayoutHalfPint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "1/2 Pint Button was clicked.");
                String PriceStr = txtViewHalfPint.getText().toString();

                HalfPintAmount = HalfPintAmount + 1;
                if (String.valueOf(HalfPintAmount).length() == 1) {
                    btnHalfPint.setText("0" + String.valueOf(HalfPintAmount));
                } else {
                    btnHalfPint.setText(String.valueOf(HalfPintAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));

            }
        });

        LayoutPint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pint Button was clicked.");
                String PriceStr = txtViewPint.getText().toString();

                PintAmount = PintAmount + 1;
                if (String.valueOf(PintAmount).length() == 1) {
                    btnPint.setText("0" + String.valueOf(PintAmount));
                } else {
                    btnPint.setText(String.valueOf(PintAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        LayoutBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Beer Button was clicked.");
                String PriceStr = txtViewBeer.getText().toString();

                BeerAmount = BeerAmount + 1;
                if (String.valueOf(BeerAmount).length() == 1) {
                    btnBeer.setText("0" + String.valueOf(BeerAmount));
                } else {
                    btnBeer.setText(String.valueOf(BeerAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        LayoutProsecco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Prosecco was clicked.");
                String PriceStr = txtViewProsecco.getText().toString();

                ProseccoAmount = ProseccoAmount + 1;
                if (String.valueOf(ProseccoAmount).length() == 1) {
                    btnProsecco.setText("0" + String.valueOf(ProseccoAmount));
                } else {
                    btnProsecco.setText(String.valueOf(ProseccoAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        LayoutSpirit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Spirit Button was clicked.");
                String PriceStr = txtViewSpirit.getText().toString();

                SpiritAmount = SpiritAmount + 1;
                if (String.valueOf(SpiritAmount).length() == 1) {
                    btnSpirit.setText("0" + String.valueOf(SpiritAmount));
                } else {
                    btnSpirit.setText(String.valueOf(SpiritAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });
        LayoutGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "GT Button was clicked.");
                String PriceStr = txtViewGT.getText().toString();

                GTAmount = GTAmount + 1;
                if (String.valueOf(GTAmount).length() == 1) {
                    btnGT.setText("0" + String.valueOf(GTAmount));
                } else {
                    btnGT.setText(String.valueOf(GTAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        LayoutSoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Soft Button was clicked.");
                String PriceStr = txtViewSoft.getText().toString();

                SoftAmount = SoftAmount + 1;
                if (String.valueOf(SoftAmount).length() == 1) {
                    btnSoft.setText("0" + String.valueOf(SoftAmount));
                } else {
                    btnSoft.setText(String.valueOf(SoftAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        LayoutExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Extra Button was clicked.");
                String PriceStr = txtViewExtra.getText().toString();

                ExtraAmount = ExtraAmount + 1;
                if (String.valueOf(ExtraAmount).length() == 1) {
                    btnExtra.setText("0" + String.valueOf(ExtraAmount));
                } else {
                    btnExtra.setText(String.valueOf(ExtraAmount));
                }

                updateData(Float.valueOf(PriceStr.substring(1)));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Back Button was clicked.");
                onBackPressed();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clear Button was clicked.");
                clearData();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next Button was clicked.");

                inputCheck();
                if (inputCheck) {
                    String csvStr = globalApplication.getCsvStr();
                    Date currentTime = Calendar.getInstance().getTime();
                    if (HalfPintAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "1/2 PINT" + "," + String.valueOf(HalfPintAmount) + "," + txtViewHalfPint.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (PintAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "PINT" + "," + String.valueOf(PintAmount) + "," + txtViewPint.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (BeerAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "BEER CAN" + "," + String.valueOf(BeerAmount) + "," + txtViewBeer.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (ProseccoAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "PROSECCO" + "," + String.valueOf(ProseccoAmount) + "," + txtViewProsecco.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (SpiritAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "SPIRIT" + "," + String.valueOf(SpiritAmount) + "," + txtViewSpirit.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (GTAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "G&T" + "," + String.valueOf(GTAmount) + "," + txtViewGT.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (SoftAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "SOFT" + "," + String.valueOf(SoftAmount) + "," + txtViewSoft.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    if (ExtraAmount != 0) {
                        csvStr = csvStr + editTxtCustomerID.getText().toString() + "," + currentTime.toString() + "," + "EXTRA" + "," + String.valueOf(ExtraAmount) + "," + txtViewExtra.getText().toString().substring(1) + "," + String.valueOf(tokens)+ "\n";
                    }
                    globalApplication.setCsvStr(csvStr);
                    clearData();
                    Toast.makeText(DataActivity.this, "The data was saved successfully.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void clearData() {
        editTxtCustomerID.setText("");
        txtViewQTY.setText("QTY 0");
        txtViewPrice.setText("£0.00");
        btnHalfPint.setText("00");
        btnPint.setText("00");
        btnBeer.setText("00");
        btnProsecco.setText("00");
        btnSpirit.setText("00");
        btnGT.setText("00");
        btnSoft.setText("00");
        btnExtra.setText("00");
        HalfPintAmount = 0;
        PintAmount = 0;
        BeerAmount = 0;
        ProseccoAmount = 0;
        SpiritAmount = 0;
        GTAmount = 0;
        SoftAmount = 0;
        ExtraAmount = 0;
        tokens = 0;
        txtViewTokens.setText("TOKENS " + String.valueOf(tokens));
    }

    private void updateData(float price) {
        int QTYAmount = Integer.valueOf(txtViewQTY.getText().toString().substring(4, txtViewQTY.getText().toString().length())) + 1;
        txtViewQTY.setText("QTY " + String.valueOf(QTYAmount));
        float totalPrice = Float.valueOf(txtViewPrice.getText().toString().substring(1)) + price;
        txtViewPrice.setText("£" + String.valueOf(totalPrice) + "0");
        tokens = (int) (totalPrice/1.5);
        txtViewTokens.setText("TOKENS " + String.valueOf(tokens));
    }

    private void inputCheck() {
        inputCheck = true;
        if (editTxtCustomerID.getText().toString().equals("")) {
            inputCheck = false;
            Toast.makeText(DataActivity.this, "Please enter the customer ID", Toast.LENGTH_LONG).show();
        }

        if (txtViewQTY.getText().toString().substring(4, txtViewQTY.getText().toString().length()).equals("0")) {
            inputCheck = false;
            Toast.makeText(DataActivity.this, "There is no the data to save.", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoScanScreen() {
        Intent mainIntent = new Intent(DataActivity.this, ScanActivity.class);
        startActivity(mainIntent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Write to the storage (ex: call appendByteBuffer(byte[] data) here)

        } else {
            Toast.makeText(getApplicationContext(), "Please grant permission.", Toast.LENGTH_LONG).show();
        }

    }

    // Hide keyboard when user click anywhere of screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}
