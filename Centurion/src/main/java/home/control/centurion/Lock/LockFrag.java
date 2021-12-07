//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690)
package home.control.centurion.Lock;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import home.control.centurion.MainActivity;
import home.control.centurion.R;


public class LockFrag extends Fragment {

private boolean lock = true;
private DatabaseReference databaseReference;
private FloatingActionButton homebtn;

    public LockFrag() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_lock, container, false);
        ImageButton lockBTN = root.findViewById(R.id.lockBTN);
        //create text boxes of all data required
        TextView output = root.findViewById(R.id.latestAccessLabel);
        TextView output2 = root.findViewById(R.id.latestAccessLabel2);
        TextView output3 = root.findViewById(R.id.latestAccessLabel3);
        lockBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lock) { // if lock is active
                    TextView lockTV = (TextView) root.findViewById(R.id.lockTV);
                    lockTV.setText(R.string.unlocked);
                    lockTV.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
                    lock = false;
                } else { //if lock is currently unlocked
                    TextView lockTV = (TextView) root.findViewById(R.id.lockTV);
                    lockTV.setText(R.string.locked);
                    lockTV.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
                    lock = true;
                }
            }
        });

        homebtn = root.findViewById(R.id.fab_lock);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("RFID");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //receive data from database
                String reading = dataSnapshot.child("1-set").child("Access").getValue().toString();
                String reading2 = dataSnapshot.child("1-set").child("CardID").getValue().toString();
                String reading3 = dataSnapshot.child("1-set").child("User").getValue().toString();
                    //update text fields for user.
                output.setText(reading);
                output2.setText(reading2);
                output3.setText(R.string.greet + reading3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }

}
