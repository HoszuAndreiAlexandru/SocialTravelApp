package com.example.testing;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class Welcome extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_page);
    }
}
