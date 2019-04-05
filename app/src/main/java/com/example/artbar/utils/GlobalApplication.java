package com.example.artbar.utils;

import android.app.Application;

public class GlobalApplication extends Application{

    protected static String csvStr ="Customer ID,Created Time,Purchase,QTY,Unit Cost,Tokens\n";

    public String getCsvStr() {
        return csvStr;
    }

    public void setCsvStr(String csvStr) {
        this.csvStr = csvStr;
    }
}
