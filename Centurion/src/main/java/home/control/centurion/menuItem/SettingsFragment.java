//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
package home.control.centurion.menuItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import home.control.centurion.R;
import home.control.centurion.ui.main.MainViewModel;

public class SettingsFragment extends Fragment {

    private MainViewModel mViewModel;
    static int i = 0;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
    private static int SPLASH_TIME= 2000;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        Button btnToggleDark = root.findViewById(R.id.btnDarkModeToggle);





        btnToggleDark.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (i == 0) {
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_YES);

                            i=1;

                        } else if (i == 1) {
                            AppCompatDelegate
                                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            i=0;

                        }
                    }
                });


        return root;

    }

}