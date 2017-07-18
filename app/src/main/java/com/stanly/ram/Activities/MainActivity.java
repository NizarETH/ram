package com.stanly.ram.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stanly.ram.R;
import com.stanly.ram.Widgets.slidingMenu.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    public String bodyFragment;
    private SlidingMenu menu;
    private View shadow;
    public Bundle extras;
    private boolean isTablet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
