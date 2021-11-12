//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Thermostat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import home.control.centurion.R;


public class ThermostatFrag extends Fragment {




    public ThermostatFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thermostat, container, false);
        return root;
    }
}