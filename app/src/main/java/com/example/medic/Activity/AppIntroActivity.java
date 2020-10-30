package com.example.medic.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.medic.R;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;

public class AppIntroActivity extends AppIntro {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //First Slide
        addSlide(AppIntroFragment.newInstance("Welcome to Navistop","Amazing App Intro with cool feature",
                R.drawable.medic_logo, ContextCompat.getColor(getApplicationContext(),R.color.whitebutton)));
        //Second Slide
        addSlide(AppIntroFragment.newInstance("All features","Amazing App Intro with cool feature",
                R.drawable.medic_logo, ContextCompat.getColor(getApplicationContext(),R.color.whitebutton)));
        //Third Slide
        addSlide(AppIntroFragment.newInstance("third slide","Amazing App Intro with cool feature",
                R.drawable.medic_logo, ContextCompat.getColor(getApplicationContext(),R.color.whitebutton)));

        setTransformer(AppIntroPageTransformerType.Zoom.INSTANCE);


        //SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefrences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences!=null)
        {
            boolean checkshared = sharedPreferences.getBoolean("checkStated",false);
            if (checkshared == true)
            {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
                finish();
            }
        }

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Decide what to do when the user clicks on "Skip"
        startActivity(new Intent(getApplicationContext(), SignIn.class));
        editor.putBoolean("checkStated", false).commit();
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Decide what to do when the user clicks on "Done"
        startActivity(new Intent(getApplicationContext(), SignIn.class));
        editor.putBoolean("checkStated", true).commit();
        finish();
    }

}