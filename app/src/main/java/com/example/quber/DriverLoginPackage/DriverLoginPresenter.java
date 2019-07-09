package com.example.quber.DriverLoginPackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.quber.DriverMapPackage.DriverMapActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.constraint.Constraints.TAG;

class DriverLoginPresenter {
    private Context context;
    private String missingField;

    DriverLoginPresenter(Context context) {
        this.context = context;
    }

    boolean confirm(String mail, String password) {

        if (TextUtils.isEmpty(mail)) {
            setMissingField("mail");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            setMissingField("password");
            return false;

        }

        return true;
    }

    void setMissingField(String missingField) {
        this.missingField = missingField;
    }

    String getMissingField() {
        return missingField;
    }

    void singIn(String mail, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("please wait ..");
        progressDialog.setMessage("getting user's data ...");

        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserToDriverMapActivity();
                }

                else {
                    progressDialog.dismiss();
                    Log.e(TAG, "onComplete: "+task.getException() );
                    Toast.makeText(context, "something went wrong , try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendUserToDriverMapActivity() {

        Intent i = new Intent (context , DriverMapActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
