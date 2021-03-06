package com.example.medic.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hbb20.CountryCodePicker;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    TextInputLayout LoginPhone, LoginPassword;
    TextView regBtn, ForgetPassword;
    Button LoginBtn;
    private RetrofitClient retrofitClient;
    private UserInterface userInterface;

    private ProgressDialog loadingBar;
    private String fcm_token;

    CountryCodePicker ccp;

    String UserPhoneKey, UserPasswordKey;
    final LoginUserRequest request = new LoginUserRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LoginPhone = findViewById(R.id.login_phone);
        ForgetPassword = findViewById(R.id.Forgrt_pass);
        LoginPassword = findViewById(R.id.login_password);
        regBtn = findViewById(R.id.signup_btn);
        LoginBtn = findViewById(R.id.login_btn);
        ccp = findViewById(R.id.ccp_login);

        Paper.init(this);


        loadingBar = new ProgressDialog(SignIn.this);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword() | !validatePhoneNo()) {
                    return;
                }

                LoginUser();
            }
        });


        /*UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);*/
       /* if (UserPhoneKey != "" && UserPasswordKey != "") {


            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
                request.setUserName(UserPhoneKey);
                request.setPassword(UserPasswordKey);

                inititalizeFcmToken(request);




            }
        }*/
    }

    private Boolean validatePhoneNo() {
        String val = LoginPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            LoginPhone.setError("Field cannot be empty");
            return false;
        } else {
            LoginPhone.setError(null);
            LoginPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = LoginPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            LoginPassword.setError("Field cannot be empty");
            return false;
        } /*else if (!val.matches(passwordVal)) {
            LoginPassword.setError("Password is too weak");
            return false;
        }*/ else {
            LoginPassword.setError(null);
            LoginPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void LoginUser() {


        loadingBar.setTitle("Login");
        loadingBar.setIcon(R.drawable.logo);
        loadingBar.setMessage("Please wait for a while...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        final String phoneNumber = LoginPhone.getEditText().getText().toString().trim();
        final String fullPhoneNo = ccp.getFullNumber() + phoneNumber;
        String password = LoginPassword.getEditText().getText().toString().trim();

        Paper.book().write(Prevalent.UserPhoneKey, fullPhoneNo);
        Paper.book().write(Prevalent.UserPasswordKey, password);

        request.setPassword(password);
        request.setUserName(fullPhoneNo);

        inititalizeFcmToken(request);
//                            Toast.makeText(VerifyPhoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

        //Perform Your required action here to either let the user sign In or do something required


    /*} else {
        Toast.makeText(SignIn.this, LoginUserResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
    }*/


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

       /* loadingBar.setTitle("Logged in");
        loadingBar.setMessage("Please wait.....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();*/

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


                    if (loginUserResponse.getResponseCode().equals("00")) {
                        Toast.makeText(SignIn.this, "Login successfully!", Toast.LENGTH_SHORT).show();

                        retrofitClient.setJwtToken(loginUserResponse.getJwtToken());


                        loadingBar.dismiss();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        retrofitClient.setCartCount(loginUserResponse.getCartItems());
                        startActivity(intent);
                        finish();
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(SignIn.this, loginUserResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                   loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }


    }



}
