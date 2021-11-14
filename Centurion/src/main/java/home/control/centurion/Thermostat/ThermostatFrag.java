//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Thermostat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;

import home.control.centurion.R;


public class ThermostatFrag extends Fragment {

    static int temp = 21;


    public ThermostatFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thermostat, container, false);
        ImageButton increment = root.findViewById(R.id.increment);
        ImageButton decrement = root.findViewById(R.id.decrement);
        TextView number = (TextView) root.findViewById(R.id.number);
        number.setText(""+temp);



        increment.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = Integer.parseInt(number.getText().toString().trim());
                if(temp < 50)
                temp++;
                number.setText(""+temp);



            }

        });

        decrement.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        temp = Integer.parseInt(number.getText().toString());
                        if(temp > 0)
                            temp--;
                        number.setText(""+temp);

                    }

                });


        return root;
    }


}