package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medic.Api_Interfaces.UserInterface;
import com.example.medic.R;
import com.example.medic.Requests.RegisterUserRequest;
import com.example.medic.Responses.LoginUserResponse;
import com.example.medic.Responses.RegisterUserResponse;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    TextInputLayout  LoginPhone, LoginPassword;
    TextView ForgetPassword;
    Button regBtn, LoginBtn;
    private RetrofitClient retrofitClient;
    RegisterUserRequest request;
    private UserInterface userInterface;

    private ProgressDialog loadingBar;
    CountryCodePicker ccp;


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

        loadingBar = new ProgressDialog(SignIn.this);

        Paper.init(this);


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

                if(!validatePassword() | !validatePhoneNo())
                {
                    return;
                }

                VerifyQRcODE();
            }
        });
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
        } else if (!val.matches(passwordVal)) {
            LoginPassword.setError("Password is too weak");
            return false;
        } else {
            LoginPassword.setError(null);
            LoginPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void VerifyQRcODE() {

        loadingBar.setTitle("Register");
        loadingBar.setMessage("Please wait for a while...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();



        final String phoneNumber = LoginPhone.getEditText().getText().toString().trim();
        final String fullPhoneNo = ccp.getFullNumber()+ phoneNumber ;
        String password = LoginPassword.getEditText().getText().toString().trim();


        final RegisterUserRequest request = new RegisterUserRequest();
        request.setPassword(password);
        request.setPhoneNumber(fullPhoneNo);

        retrofitClient = RetrofitClient.getInstance();
        userInterface = retrofitClient.getRetrofit().create(UserInterface.class);
        Call<LoginUserResponse> call = userInterface.loginUser(request);
        System.out.println("Hello");

        call.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                // loadingBar.dismiss();
                LoginUserResponse loginUserResponse = response.body();
                //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();
                System.out.println(loginUserResponse.getResponseCode());



                if(loginUserResponse.getResponseCode().equals("00"))
                {


                    loadingBar.dismiss();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();                }

            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                // loadingBar.dismiss();
                System.out.println("Failed");
            }
        });
//                            Toast.makeText(VerifyPhoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

        //Perform Your required action here to either let the user sign In or do something required


    /*} else {
        Toast.makeText(SignIn.this, LoginUserResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
    }*/



    }
}
