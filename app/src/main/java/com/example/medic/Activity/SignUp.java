package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.medic.R;
import com.example.medic.Requests.RegisterUserRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;


public class SignUp extends AppCompatActivity {



    //Variables
    TextInputLayout regFirstName, regLastName, regConfirmPassword, regEmail, regPhoneNo, regPassword;
    TextView regToLoginBtn;
    Button regBtn;
    private ProgressDialog loadingBar;
    CountryCodePicker ccp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        regFirstName = (TextInputLayout) findViewById(R.id.reg_firstname);
        regLastName = (TextInputLayout)findViewById(R.id.reg_lastname);
        regConfirmPassword = (TextInputLayout)findViewById(R.id.reg_repassword);
        regEmail = (TextInputLayout)findViewById(R.id.reg_email);
        regPhoneNo = (TextInputLayout)findViewById(R.id.reg_phoneNo);
        regPassword = (TextInputLayout)findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        ccp = findViewById(R.id.ccp);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        loadingBar = new ProgressDialog(SignUp.this);








        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!validateFirstName() | !validateLastName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateRePassword())
                {
                    return;
                }

                VerifyQRcODE();


            }




        });




        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();
            }
        });





    }



    private Boolean validateFirstName() {
        String val = regFirstName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regFirstName.setError("Field cannot be empty");
            return false;
        }
        else {
            regFirstName.setError(null);
            regFirstName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLastName() {
        String val = regLastName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regLastName.setError("Field cannot be empty");
            return false;
        }
        else {
            regLastName.setError(null);
            regLastName.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        /*if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else*/if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
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
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

        private Boolean validateRePassword() {
            String val1 = regPassword.getEditText().getText().toString();
            String val = regConfirmPassword.getEditText().getText().toString();
           
            if (val.isEmpty()) {
                regConfirmPassword.setError("Field cannot be empty");
                return false;
            } else if (!val.equals(val1)) {
                regConfirmPassword.setError("Password Does't match");
                return false;
            } else {
                regConfirmPassword.setError(null);
                regConfirmPassword.setErrorEnabled(false);
                return true;
            }
    }



    public void VerifyQRcODE() {

        loadingBar.setTitle("Register");
        loadingBar.setMessage("Please wait for a while...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        String firstName = regFirstName.getEditText().getText().toString().trim();
        String lastName = regLastName.getEditText().getText().toString().trim();
        final String phoneNumber = regPhoneNo.getEditText().getText().toString().trim();
        final String fullPhoneNo = ccp.getFullNumber()+ phoneNumber ;
        String password = regPassword.getEditText().getText().toString().trim();
        String Email = regEmail.getEditText().getText().toString().trim();

        final RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPassword(password);
        request.setPhoneNumber(fullPhoneNo);
        request.setEmailAddress(Email);
        request.setRoleName("Customer");

        loadingBar.dismiss();

        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        intent.putExtra("request", request);
        startActivity(intent);
        finish();




    }

}



