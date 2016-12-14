package com.example.csc413_volley_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;




/**
 * Created by Nazo on 12/2/2016.
 */

public class Splash extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);        //??
        Thread myThread = new Thread(){

            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        myThread.start();

    }

}
