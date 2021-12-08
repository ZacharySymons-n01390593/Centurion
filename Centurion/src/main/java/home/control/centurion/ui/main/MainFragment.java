//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
package home.control.centurion.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import home.control.centurion.MainActivity;
import home.control.centurion.R;
import home.control.centurion.SplashActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    static int i = 0;
    private DatabaseReference databaseReference;

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    private static int SPLASH_TIME= 2000;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);


        if(!wifiConnection(this)){
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.btn_star)
                    .setTitle("No Internet Connection")
                    .setMessage("A Internet Connection was not found. Please come back when you have connection")
                    .setPositiveButton(R.string.back_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .show();
        }


        return root;

    }

    private boolean wifiConnection(MainFragment mainFragment) {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(wifiConn != null && wifiConn.isConnected()){
            return true;
        } else {
            return false;
        }

    }




}