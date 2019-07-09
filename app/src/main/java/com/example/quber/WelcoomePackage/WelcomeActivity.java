package com.example.quber.WelcoomePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quber.CustomerLoginPackage.CustomerLoginActivity;

import com.example.quber.DriverLoginPackage.DriverLoginActivity;
import com.example.quber.R;
import com.example.quber.RegisterPackage.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button customerLoginBtn , driverLoginButton;
    TextView dontHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initializeFields();

        customerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendUserToCustomerLoginActivity();
            }
        });

        driverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendUserToDriverLoginActvity();

            }
        });

        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendUserToRegisterActivity();


            }
        });

    }

    private void sendUserToRegisterActivity() {

        startActivity(new Intent( this , RegisterActivity.class));


    }

    private void sendUserToDriverLoginActvity() {

        startActivity(new Intent( this , DriverLoginActivity.class));


    }

    private void sendUserToCustomerLoginActivity() {
        startActivity(new Intent( this , CustomerLoginActivity.class));

    }

    private void initializeFields() {

    customerLoginBtn=findViewById(R.id.customer_login_button);
    driverLoginButton=findViewById(R.id.driver_login_button);
    dontHaveAccount = findViewById(R.id.dont_have_Account);
    }
}
