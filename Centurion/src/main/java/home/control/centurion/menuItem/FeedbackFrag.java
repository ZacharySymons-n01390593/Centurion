package home.control.centurion.menuItem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import home.control.centurion.R;


public class FeedbackFrag extends Fragment {

    TextView ratingNum, showRating;
    EditText name, phoneNum, email, comment;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;
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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = ratingNum.getText().toString();
                showRating.setText(getString(R.string.ratingText) + temp + "\n" + name.getText());
                name.setText("");
                ratingBar.setRating(0);
                ratingNum.setText("");
            }
        });



        return root;
    }
}