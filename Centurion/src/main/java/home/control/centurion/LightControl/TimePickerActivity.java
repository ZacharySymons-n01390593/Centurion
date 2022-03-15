package home.control.centurion.LightControl;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import home.control.centurion.R;

public class TimePickerActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);

        DialogFragment timePicker1 = new TimePickerFragment();
        timePicker1.show(getSupportFragmentManager(), "Time Picker");

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      String  time = hourOfDay + ":" + minute;
        Bundle bundle = new Bundle();
        bundle.putString("time", time);
        LightControlFrag fragObj = new LightControlFrag();
        fragObj.setArguments(bundle);

    }

}
