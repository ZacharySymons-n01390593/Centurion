package home.control.centurion.LightControl;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import home.control.centurion.MainActivity;
import home.control.centurion.R;

public class TimePickerActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
  private  String time = "-1";
    int pick = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);


        DialogFragment timePicker1 = new TimePickerFragment();
        timePicker1.show(getSupportFragmentManager(), "Time Picker");

        pick = getIntent().getIntExtra("case",-1);


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setContentView(R.layout.fragment_lightcontrol);
        time = hourOfDay + ":" + minute;

        if (pick == 0) {
            TextView tv = (TextView) findViewById(R.id.startTimeTv);
            tv.setText(time);
            Log.d(time, "time");
        }
    }

}
