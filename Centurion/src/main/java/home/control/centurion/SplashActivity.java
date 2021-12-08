package home.control.centurion;
//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        new Handler().postDelayed(new Runnable() {


                                      @Override
                                      public void run() {
                                          Intent testIntent = new Intent(SplashActivity.this, MainActivity.class);
                                          startActivity(testIntent);
                                          finish();



                                      }
                                  }

                ,2000);


    }


}