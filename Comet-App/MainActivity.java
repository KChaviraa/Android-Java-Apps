package com.example.cometapp;

/*
    This app displays a solid circle moving along an elliptical path on the screen.
    The elliptical orbit has the height equal to the height of the AVD screen and the width equal to the width of the AVD screen.
    The circle stays within the bounds of the screen without slipping out and it jumps smoothly across the screen

    App: Comet App
    @author : Kate Chavira
    @version 04/1/2024
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find the width and height of the ui screen
        // DisplayMetrics instance is used to describe general information about display size and so on
        DisplayMetrics displayMetrics = new DisplayMetrics();
        // an instance to communicate with window manager
        // use Context.getSystemService(Context.WINDOW_SERVICE) to get one of these
        // and getApplicationContext() method
        WindowManager windowManager = (WindowManager)getApplicationContext().
                getSystemService(Context.WINDOW_SERVICE);
        // tell this WindowManager instance will create a new window for the ui.
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        setContentView(new CometAnimation(this, width,height));
    }
}