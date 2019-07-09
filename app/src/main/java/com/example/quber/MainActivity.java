package com.example.quber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quber.DriverMapPackage.DriverMapActivity;
import com.example.quber.WelcoomePackage.WelcomeActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);


        Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                    if (FirebaseAuth.getInstance().getCurrentUser()==null) {
                        Intent i = new Intent(getBaseContext(), WelcomeActivity.class);
                        // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else sendUserToDriverMap();
                }
            }
        };

        thread.start();

    }

    private void sendUserToDriverMap() {

        Intent i = new Intent(getBaseContext(), DriverMapActivity.class);
        // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
