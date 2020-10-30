package com.example.medic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.R;



public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2*1000);

                    startActivity(new Intent(getApplicationContext(), AppIntroActivity.class));

                    //check if the user already has saved login data
                  //  Parent d = Util.getSavedObjectFromPreference(getApplicationContext(),
                         //   "mPreference", "Parent", Parent.class);

                    //if the user already has saved login data
                  //  if(d!=null)
                 //   {
                        //redirect the main screen
                    //    Util.redirectToActivity(SplashScreenActivity.this, MainActivity.class);
                //    }
                   // else
                  //  {
                        //if not, redirect the user to the login screen
                   //     Util.redirectToActivity(SplashScreenActivity.this, AppIntroActivity.class);
                  //  }

                } catch (Exception e) {
                    Log.d("Splash", "run: " + e.getMessage());
                }
            }
        };

        // start thread
        background.start();
    }
}
