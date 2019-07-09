package com.example.quber.DriverLoginPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quber.R;

public class DriverLoginActivity extends AppCompatActivity {

    private EditText mailEt, passwordEt;
    private Button loginBtn;
    DriverLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        initializeFields();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail, password;
                mail = mailEt.getText().toString();
                password = passwordEt.getText().toString();
                boolean isConfirmed = presenter.confirm(mail, password);
                if (isConfirmed) presenter.singIn(mail, password);
                else {
                    String missingField = presenter.getMissingField();
                    handleMissingField(missingField);

                }

            }
        });


    }

    private void handleMissingField(String missingField) {

        if (missingField.equals("mail")) mailEt.setError("you can't leave this empty");
        if (missingField.equals("password")) passwordEt.setError("you can't leave this empty");


    }

    private void initializeFields() {
        mailEt = findViewById(R.id.username_edit_text);
        passwordEt = findViewById(R.id.password_edit_text_driver);
        loginBtn = findViewById(R.id.driver_login_button_driver_activity);
        presenter = new DriverLoginPresenter(this);

    }
}
