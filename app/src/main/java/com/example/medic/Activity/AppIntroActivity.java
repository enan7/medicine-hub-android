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
        addSlide(AppIntroFragment.newInstance("Welcome to Medic","Amazing Medical Store with cool feature",
                R.drawable.firstintro, ContextCompat.getColor(getApplicationContext(), R.color.title_color),
                ContextCompat.getColor(getApplicationContext(), R.color.dark_blue),
                ContextCompat.getColor(getApplicationContext(), R.color.black_color)));
        //Second Slide
        addSlide(AppIntroFragment.newInstance("Easy to Select","Amazing App Intro with cool feature",
                R.drawable.secondintro, ContextCompat.getColor(getApplicationContext(), R.color.title_color),
                ContextCompat.getColor(getApplicationContext(), R.color.dark_blue),
                ContextCompat.getColor(getApplicationContext(), R.color.black_color)));
        //Third Slide
        addSlide(AppIntroFragment.newInstance("Fastest to Buy","Amazing App Intro with cool feature",
                R.drawable.thirdintro, ContextCompat.getColor(getApplicationContext(), R.color.title_color),
                ContextCompat.getColor(getApplicationContext(), R.color.dark_blue),
                ContextCompat.getColor(getApplicationContext(), R.color.black_color)));

        setTransformer(AppIntroPageTransformerType.Zoom.INSTANCE);

        //Indicator color
        setIndicatorColor(getResources().getColor(R.color.dark_blue), getResources().getColor(R.color.dark_blue));
        //Skip Button color
        setColorSkipButton(getResources().getColor(R.color.dark_blue));
        //Done Button Color
        setColorDoneText(getResources().getColor(R.color.dark_blue));
        //Arrow Color
        setNextArrowColor(getResources().getColor(R.color.dark_blue));
        //Bottom Bar Color
        setBarColor(getResources().getColor(R.color.title_color));


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