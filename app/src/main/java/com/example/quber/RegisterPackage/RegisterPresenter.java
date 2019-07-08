package com.example.quber.RegisterPackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.quber.WelcoomePackage.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.constraint.Constraints.TAG;

class RegisterPresenter {
    private Context context;
    private String missingField;

    void setMissingField(String missingField) {
        this.missingField = missingField;
    }

    RegisterPresenter(Context context) {
        this.context = context;
    }

    boolean verify(String firstName, String lastName, String password, String passwordConfirm, String mail, boolean isDriver) {

        if (TextUtils.isEmpty(firstName)) {
            setMissingField("firstName");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            setMissingField("lastName");
            return false;
        }

        if (TextUtils.isEmpty(mail)) {
            setMissingField("mail");
            return false;
        }


        if (TextUtils.isEmpty(password) || password.length() < 6) {
            setMissingField("password");
            return false;
        }

        if (TextUtils.isEmpty(passwordConfirm) || !password.equals(passwordConfirm)) {
            setMissingField("passwordConfirm");
            return false;

        }


        return true;
    }


    void registerAccount(String firstName, String lastName, String password, String passwordConfirm, String mail, boolean isDriver) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "you have registered successfully", Toast.LENGTH_SHORT).show();
                    sendUserToWelcomeActivity();
                } else{
                    progressDialog.dismiss();
                    Log.e(TAG, "onComplete: "+ task.getException() );
                    Toast.makeText(context, "Something went wrong , try again later", Toast.LENGTH_SHORT).show();
            }}
        });


    }

    private void sendUserToWelcomeActivity() {
        Intent i = new Intent(context, WelcomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    String getMissingField() {
        return missingField;
    }
}
