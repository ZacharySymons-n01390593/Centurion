//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Lock;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import home.control.centurion.R;


public class LockFrag extends Fragment {



    public LockFrag() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lock, container, false);
        ImageButton lockBTN = root.findViewById(R.id.lockBTN);
//        lockBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (lockBTN.isEnabled()){
//                    lockBTN.setColorFilter(color);
//                }
//                lockTheDoor();
//            }
//        });


        return root;
    }
    public void lockTheDoor(){

    }

}
