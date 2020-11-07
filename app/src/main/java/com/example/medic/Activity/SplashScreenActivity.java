package com.example.medic.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.Api_Interfaces.UserInterface;
import com.example.medic.Prevalent.Prevalent;
import com.example.medic.R;
import com.example.medic.Requests.LoginUserRequest;
import com.example.medic.Responses.LoginUserResponse;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenActivity extends AppCompatActivity {

    private ImageView splashLogo;
    String UserPhoneKey, UserPasswordKey;
    private ProgressDialog loadingBar;
    private String fcm_token;
    private RetrofitClient retrofitClient;
    private UserInterface userInterface;

    final LoginUserRequest request = new LoginUserRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Paper.init(this);
        loadingBar = new ProgressDialog(SplashScreenActivity.this);


        UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);



        splashLogo = findViewById(R.id.splash_logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        splashLogo.startAnimation(animation);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2*1500);



                    //check if the user already has saved login data

                    if (UserPhoneKey != "" && UserPasswordKey != "") {


                        if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
                            request.setUserName(UserPhoneKey);
                            request.setPassword(UserPasswordKey);

                            inititalizeFcmToken(request);

                        }
                        else
                        {
                            startActivity(new Intent(getApplicationContext(), AppIntroActivity.class));
                            finish();
                        }

                    }


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

    private void inititalizeFcmToken(final LoginUserRequest request){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID fcm_token
                        fcm_token = task.getResult().getToken();
                        request.setFcmToken(fcm_token);
                        AllowAccess(request);


                    }
                });

    }

    private void AllowAccess(final LoginUserRequest request) {

        loadingBar.setTitle("Already Logged in");
        loadingBar.setMessage("Please wait.....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

//        request.setPassword(request.getPassword());
//        request.setUserName(request.getUserName());
        try {
            //inititalizeFcmToken(request);
            retrofitClient = RetrofitClient.getInstance();
            userInterface = retrofitClient.getRetrofit().create(UserInterface.class);
            Call<LoginUserResponse> call = userInterface.loginUser(request);

            call.enqueue(new Callback<LoginUserResponse>() {
                @Override
                public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                    // loadingBar.dismiss();
                    LoginUserResponse loginUserResponse = response.body();
                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();
                    System.out.println(loginUserResponse.getResponseCode());

                    loadingBar.dismiss();


                    if (loginUserResponse.getResponseCode().equals("00")) {
                        retrofitClient.setJwtToken(loginUserResponse.getJwtToken());
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        retrofitClient.setCartCount(loginUserResponse.getCartItems());
                        startActivity(intent);
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), SignIn.class));
                        finish();
                        Toast.makeText(SplashScreenActivity.this, loginUserResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                    loadingBar.dismiss();
                    System.out.println("Failed");
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
