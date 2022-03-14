package home.control.centurion.LightControl;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import home.control.centurion.R;

public class TimePickerActivity  extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lightcontrol);

        //TimePicker requires a new activity
        ImageButton startTimeBtn = (ImageButton) findViewById(R.id.startTimeBtn);
        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePick = new timePicker();
                timePick.show(getSupportFragmentManager(), "Time Picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.startTimeTv);
        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }
}
