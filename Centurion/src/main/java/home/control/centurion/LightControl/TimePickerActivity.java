package home.control.centurion.LightControl;

import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.TimeZone;

import home.control.centurion.MainActivity;
import home.control.centurion.R;

public class TimePickerActivity extends AppCompatActivity {
    int t1Hour;
    int t1Minute;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lightcontrol);

        Button btn = (Button) findViewById(R.id.stBtn);



    }
}
