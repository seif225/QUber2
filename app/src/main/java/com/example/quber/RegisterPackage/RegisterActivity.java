package com.example.quber.RegisterPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.quber.R;

public class RegisterActivity extends AppCompatActivity {

    EditText firstNameEt,lastNameEt,passwordEt , passwordConfirmEt , mailEt;
    CheckBox driverCheckBox;
    Button registerBtn;
    RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializefields();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName , lastName , password , passwordConfirm , mail;
                boolean isDriver;
                firstName=firstNameEt.getText().toString();
                lastName=lastNameEt.getText().toString();
                password=passwordEt.getText().toString();
                passwordConfirm = passwordConfirmEt.getText().toString();
                mail = mailEt.getText().toString();
                isDriver=driverCheckBox.isChecked();
                boolean isVerified = presenter.verify(firstName,lastName,password,passwordConfirm,mail,isDriver);

                if(isVerified)
                    presenter.registerAccount(firstName,lastName,password,passwordConfirm,mail,isDriver);
                else {

                    String fieldName = presenter.getMissingField();
                    handleMissingField(fieldName);

                }



            }
        });



    }

    private void handleMissingField(String fieldName) {

        if(fieldName.equals("firstName")) firstNameEt.setError("you can't leave this field empty");
        if(fieldName.equals("lastName"))lastNameEt.setError("you can't leave this field empty");
        if(fieldName.equals("password"))passwordEt.setError("your password must contain 6 characters at least");
        if(fieldName.equals("passwordConfirm"))passwordConfirmEt.setError("this doesn't match your password");
        if(fieldName.equals("mail"))mailEt.setError("you can't leave this field empty");




    }

    private void initializefields() {

    firstNameEt = findViewById(R.id.first_name_edit_text);
    lastNameEt = findViewById(R.id.last_name_edit_text);
    passwordEt = findViewById(R.id.password_edit_text);
    passwordConfirmEt = findViewById(R.id.retype_password_edit_text);
    mailEt = findViewById(R.id.mail_edit_text);
    driverCheckBox = findViewById(R.id.driver_check_box);
    registerBtn = findViewById(R.id.register_btn);
    presenter = new RegisterPresenter(this);
    }
}
