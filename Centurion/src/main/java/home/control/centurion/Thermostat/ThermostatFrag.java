//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Thermostat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

import home.control.centurion.MainActivity;
import home.control.centurion.R;


public class ThermostatFrag extends Fragment {

    //DRY principle used as the increments and decrements are independent

    static int temp= 21;
    private FloatingActionButton homebtn;
    public ThermostatFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thermostat, container, false);
        ImageButton increment = root.findViewById(R.id.increment);
        ImageButton decrement = root.findViewById(R.id.decrement);
        Switch FanToggle = root.findViewById(R.id.fanSwitch);
        Switch FanDTempToggle = root.findViewById(R.id.switch1);
        TextView number = (TextView) root.findViewById(R.id.number);
        number.setText(""+temp);


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
                if (temp < 50)
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
                            if (temp > 0)
                                temp--;
                            number.setText("" + temp);
                        }
                    }
                });

        homebtn = root.findViewById(R.id.fab_temp);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });




        return root;
    }
}