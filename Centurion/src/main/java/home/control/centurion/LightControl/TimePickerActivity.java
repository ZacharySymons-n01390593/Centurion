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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.TimeZone;

import home.control.centurion.MainActivity;
import home.control.centurion.R;

public class TimePickerActivity extends AppCompatActivity {

    DatabaseReference reff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lightcontrol);

        TextView startTimeTv = (TextView) findViewById(R.id.startTimeTv);
        TextView endTimeTv = (TextView) findViewById(R.id.endTimeTv);
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);

            reff = FirebaseDatabase.getInstance().getReference().child("DistanceSensor");
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from ui
                String start = startTimeTv.getText().toString();
                String end = endTimeTv.getText().toString();
                //save strings to object

                //push object ot database
                reff.child("StartTime").setValue(start);
                reff.child("EndTime").setValue(end);

            }
        });
    }
}