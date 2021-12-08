//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Thermostat;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;

import home.control.centurion.R;


public class ThermostatFrag extends Fragment {

    //DRY principle used as the increments and decrements are independent

    static int temp= 21;
    DatabaseReference reff;
    public ThermostatFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thermostat, container, false);
            //declare all layout variables suchas image buttons switches and textviews
        ImageButton increment = root.findViewById(R.id.increment);
        ImageButton decrement = root.findViewById(R.id.decrement);
        Switch FanToggle = root.findViewById(R.id.fanSwitch);
        Switch FanDTempToggle = root.findViewById(R.id.switch1);
        TextView number = (TextView) root.findViewById(R.id.number);

        TextView tReading = root.findViewById(R.id.TemperatureReading);
        TextView hReading = root.findViewById(R.id.HumidityReading);

        number.setText(""+temp);

        reff = FirebaseDatabase.getInstance().getReference().child("TemperatureHumidity");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String tempReading = dataSnapshot.child("Temperature Reading").getValue().toString();
                String humidReading = dataSnapshot.child("Humidity Reading").getValue().toString();

                tReading.setText(tempReading);
                hReading.setText(humidReading); // update texviews humidity and temperature

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FanToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Fan Toggled",Snackbar.LENGTH_LONG);
                snackbar.setTextColor(Color.RED);
                snackbar.show(); //display snackbar to tell user fan state has changed
            }
        });

        FanDTempToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Desired Temperature Toggled",Snackbar.LENGTH_LONG);
                snackbar.setTextColor(Color.RED);
                snackbar.show();//display snackbar to let user know that the state has changed
            }
        });

        increment.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FanDTempToggle.isChecked()) {
                temp = Integer.parseInt(number.getText().toString().trim());
                if (temp < 50) //requested temp upper limit
                    temp++;
                number.setText("" + temp);
                }
            }
        });

        decrement.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(FanDTempToggle.isChecked()) {
                            temp = Integer.parseInt(number.getText().toString());
                            if (temp > 0) //requested temp lower limit
                                temp--;
                            number.setText("" + temp);
                        }
                    }
                });

        return root;
    }
}