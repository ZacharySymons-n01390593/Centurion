//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;

import home.control.centurion.CarbonMonoxide.CarbonMonoxideFrag;
import home.control.centurion.LightControl.LightControlFrag;
import home.control.centurion.LightControl.TimePickFrag;
import home.control.centurion.Lock.LockFrag;
import home.control.centurion.Thermostat.ThermostatFrag;
import home.control.centurion.menuItem.FeedbackFrag;

import home.control.centurion.menuItem.SettingsFragment;
import home.control.centurion.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity{

    TextView startTimeTv;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //calls the splash screen fragment
        loadFragment(new MainFragment());


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        Toast.makeText(getApplicationContext(), menuItem.getTitle(),
                                Toast.LENGTH_LONG).show();
                        // Launch the corresponding fragment
                        //switch case checks  which fragment ot navigate to
                        Fragment fragment = null;
                        Class fragmentClass = null;
                        switch (menuItem.getItemId()) {
//                            case R.id.nav_main:
//                                fragmentClass = MainFragment.class;
//                                break;
                            case R.id.nav_lock:
                                fragmentClass = LockFrag.class;
                                break;
                            case R.id.nav_thermostat:
                                fragmentClass = ThermostatFrag.class;
                                break;
                            case R.id.nav_lightcontrol:
                                fragmentClass = LightControlFrag.class;
                                break;
                            case R.id.nav_carbonmonoxide:
                                fragmentClass = CarbonMonoxideFrag.class;
                                break;
                        }

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        loadFragment(fragment);


                        return true;
                    }
                });


        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
/*
        startTimeTv = (TextView) findViewById(R.id.startTimeTv);
        ImageButton startTimeBtn = (ImageButton) findViewById(R.id.startTimeBtn);
        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePick = new TimePickFrag();
                timePick.show(getSupportFragmentManager(), "time pick start");
            }
        });
*/
    }



    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.commit();
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.btn_star)
                .setTitle(R.string.back_exit)
                .setMessage(R.string.back_message)
                .setPositiveButton(R.string.back_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.back_no, null)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case (R.id.ActivityLog):
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://humber.ca"));
                startActivity(intent1);
                return true;
            case (R.id.settingsMenu):
                loadFragment(new SettingsFragment());
                return true;
            case (R.id.help):
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=cueulBxn1Fw"));
                startActivity(intent3);
                return true;
            case (R.id.feedback):
                loadFragment(new FeedbackFrag());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}