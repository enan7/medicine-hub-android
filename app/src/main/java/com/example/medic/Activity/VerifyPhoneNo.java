package com.example.medic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.Api_Interfaces.UserInterface;
import com.example.medic.R;
import com.example.medic.Requests.RegisterUserRequest;
import com.example.medic.Responses.RegisterUserResponse;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneNo extends AppCompatActivity implements Serializable {

    Button verify_btn;
    EditText phoneNoEnteredByTheUser;
    ProgressBar progressBar;
    String verificationCodeBySystem;
    private RetrofitClient retrofitClient;
    RegisterUserRequest request;
    private UserInterface userInterface;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

         request = (RegisterUserRequest) getIntent().getSerializableExtra("request");

        sendVerificationCodeToUser(request.getPhoneNumber());

        verify_btn = findViewById(R.id.verify_btn);
        phoneNoEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);


        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = phoneNoEnteredByTheUser.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    phoneNoEnteredByTheUser.setError("Wrong OTP...");
                    phoneNoEnteredByTheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+" + phoneNo,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    //Get the code in global variable
                    verificationCodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(VerifyPhoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);

    }


    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            retrofitClient = RetrofitClient.getInstance();
                            userInterface = retrofitClient.getRetrofit().create(UserInterface.class);
                            Call<RegisterUserResponse> call = userInterface.registerUser(request);

                            call.enqueue(new Callback<RegisterUserResponse>() {
                                @Override
                                public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {
                                    // loadingBar.dismiss();
                                    RegisterUserResponse registerUserResponse = response.body();
                                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();
                                    System.out.println(registerUserResponse.getResponseCode());


                                    if(registerUserResponse.getResponseCode().equals("00")) {

                                        Toast.makeText(VerifyPhoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                                        startActivity(intent);

                                    }
                                    else
                                        {
                                            Intent intent = new Intent(getApplicationContext(), SignUp.class);
                                            startActivity(intent);
                                            Toast.makeText(VerifyPhoneNo.this, registerUserResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                }

                                @Override
                                public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                                    // loadingBar.dismiss();
                                    System.out.println("Failed");
                                }
                            });


                            //Perform Your required action here to either let the user sign In or do something required


                        } else {
                            Toast.makeText(VerifyPhoneNo.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}
