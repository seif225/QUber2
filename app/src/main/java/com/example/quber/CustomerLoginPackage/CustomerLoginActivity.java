package com.example.quber.CustomerLoginPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.quber.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomerLoginActivity extends AppCompatActivity {

    @Bind(R.id.customer_login_button_customer)
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        ButterKnife.bind(this);

    }
}
