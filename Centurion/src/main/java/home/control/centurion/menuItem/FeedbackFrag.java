package home.control.centurion.menuItem;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import home.control.centurion.MainActivity;
import home.control.centurion.R;


public class FeedbackFrag extends Fragment {

    // DRY principle in effect

    TextView ratingNum, showRating;
    EditText name, phoneNum, email, comment;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;
    private FloatingActionButton homebtn;
    private ProgressBar mProgressBar;
    private TextView mLoadingText;

    private int mProgressStatus = 0;

    private final Handler mHandler = new Handler();
    public FeedbackFrag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);


        ratingNum = root.findViewById(R.id.rateNumber);
        ratingBar = root.findViewById(R.id.ratingBar);

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        phoneNum = root.findViewById(R.id.phoneNumber);
        comment = root.findViewById(R.id.comment);
        submit = root.findViewById(R.id.submit);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && rateValue>0) {
                    ratingNum.setText(getString(R.string.rating_bad) + " " + rateValue + " " + getString(R.string.out_of_five));
                }
                else if(rateValue<=2 && rateValue>1) {
                    ratingNum.setText(getString(R.string.ok) + " " + rateValue + getString(R.string.out_of_five));
                }
                else if(rateValue<=3 && rateValue>2) {
                    ratingNum.setText(getString(R.string.good) + " " + rateValue + getString(R.string.out_of_five));
                }
                else if(rateValue<=4 && rateValue>3) {
                    ratingNum.setText(getString(R.string.great) + " " + rateValue + getString(R.string.out_of_five));
                }
                else if(rateValue<=5 && rateValue>4) {
                    ratingNum.setText(getString(R.string.amazing) + " " + rateValue + getString(R.string.out_of_five));
                }

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "failed to read value",
                        Toast.LENGTH_LONG).show();            }
        });

        String deviceName = Build.MODEL;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("Users").child(name.getText().toString()).child("email").setValue(email.getText().toString());
                myRef.child("Users").child(name.getText().toString()).child("feedback").setValue(comment.getText().toString());
                myRef.child("Users").child(name.getText().toString()).child("name").setValue(name.getText().toString());
                myRef.child("Users").child(name.getText().toString()).child("phone number").setValue(phoneNum.getText().toString());
                myRef.child("Users").child(name.getText().toString()).child("rating").setValue(rateValue);
                myRef.child("Users").child(name.getText().toString()).child("phone model").setValue(deviceName);


                name.setText("");
                ratingBar.setRating(0);
                ratingNum.setText("");
                phoneNum.setText("");
                comment.setText("");
                email.setText("");
                Toast.makeText(getContext(), (R.string.greet), Toast.LENGTH_SHORT).show();
                mProgressBar = root.findViewById(R.id.progressbar);
                mLoadingText = root.findViewById(R.id.LoadingCompleteTextView);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mProgressStatus < 100){
                            mProgressStatus++;
                            android.os.SystemClock.sleep(50);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(mProgressStatus);
                                }
                            });
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mLoadingText.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }).start();
            }
        });

        homebtn = root.findViewById(R.id.fab_feedback);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}