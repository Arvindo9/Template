package com.aiprog.template.utils.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.aiprog.template.utils.AppConstants.DATE_FORMAT_INTEGER;
import static com.aiprog.template.utils.AppConstants.DATE_TIME_NAME_FORMAT;
import static com.aiprog.template.utils.AppConstants.GENERAL_DATE_FORMAT;
import static com.aiprog.template.utils.AppConstants.LOG_DELETE_DATE_INCREMENT;

/**
 * Author       : Arvindo Mondal
 * Created on   : 15-01-2019
 * Email        : arvindomondal@gmail.com
 */
public class General {

    private General(){}

    public static String getDateTime(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(GENERAL_DATE_FORMAT, Locale.ENGLISH);
        return  df.format(date);
    }

    public static String getDateTimeName(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_NAME_FORMAT, Locale.ENGLISH);
        return df.format(date);
    }

    public static int getDateInteger(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_INTEGER, Locale.ENGLISH);
        String dateStr = df.format(date);
        return Integer.parseInt(dateStr);
    }

    public static int integerDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_INTEGER, Locale.ENGLISH);
        String dateStr = df.format(date);
        return Integer.parseInt(dateStr);
    }

    public static int integerDate(String date){
        SimpleDateFormat df = new SimpleDateFormat(GENERAL_DATE_FORMAT, Locale.ENGLISH);
        int newDate = 0;
        try {
            Date dateObj = df.parse(date);
            df = new SimpleDateFormat(DATE_FORMAT_INTEGER, Locale.ENGLISH);
            String dateStr = df.format(dateObj);
            newDate = Integer.parseInt(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static int integerDate(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_INTEGER, Locale.ENGLISH);
        return Integer.parseInt(df.format(date));
    }

    public static int nextLogDeleteDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_INTEGER, Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, LOG_DELETE_DATE_INCREMENT); // Adding 10 days
        String dateStr = sdf.format(c.getTime());
        return Integer.parseInt(dateStr);
    }

    //-----------------------

    /**
     * If keypad is showing it can be hide immediately
     */
    public static void hideKeyboard(Context context) {
        View view = ((Activity)context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     *
     * @return true if network available else false for not
     */
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
