//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
package home.control.centurion.LightControl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import home.control.centurion.MainActivity;
import home.control.centurion.R;


public class LightControlFrag extends Fragment
 {

    URL ImageUrl;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    String strURL;
    Switch switchOn_Off = null;

    private FloatingActionButton homebtn;
     DatabaseReference reff;


    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radioButton1;
    RadioButton radioButton2;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lightcontrol, container, false);

        imageView = (ImageView) root.findViewById(R.id.imageView2);


        reff = FirebaseDatabase.getInstance().getReference().child("DistanceSensor").child("Detected");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lastDetected = dataSnapshot.child("Date_Time").getValue() + " " +  dataSnapshot.child("Distance").getValue();
                TextView tv = root.findViewById(R.id.dateDetectedTv);
                tv.setText(lastDetected);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Spinner initialization

         Spinner Dur = (Spinner) root.findViewById(R.id.spinnerDur);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.timerArray, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         Dur.setAdapter(adapter);

         ArrayAdapter<CharSequence> adapterHours = ArrayAdapter.createFromResource(getContext(), R.array.timeHours, android.R.layout.simple_spinner_item);
         adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         ArrayAdapter<CharSequence> adapterMins = ArrayAdapter.createFromResource(getContext(), R.array.timeMinutes, android.R.layout.simple_spinner_item);
         adapterMins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         Spinner startHour = (Spinner) root.findViewById(R.id.spinnerStartHour);
         startHour.setAdapter(adapterHours);

         Spinner startMin = (Spinner) root.findViewById(R.id.spinnerStartMin);
         startMin.setAdapter(adapterMins);

         Spinner endHour = (Spinner) root.findViewById(R.id.spinnerEndHour);
         endHour.setAdapter(adapterHours);

         Spinner endMin = (Spinner) root.findViewById(R.id.spinnerEndMin);
         endMin.setAdapter(adapterMins);

         homebtn = root.findViewById(R.id.fab_light);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class); // home action button intent
                startActivity(intent);
            }
        });

        switchOn_Off = (Switch) root.findViewById(R.id.switchLight);
        switchOn_Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_Off(v);
            }
        });

        TextView startTimeTv = (TextView) root.findViewById(R.id.startTime);
        TextView endTimeTv = (TextView) root.findViewById(R.id.endTime);
        ImageButton startTimePick = (ImageButton) root.findViewById(R.id.startTimeBtn);
        ImageButton endTimePick = (ImageButton) root.findViewById(R.id.endTimeBtn);

        Button confirmBtn = (Button) root.findViewById(R.id.confirmBtn);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from ui
                radioGroup1 = root.findViewById(R.id.start_am_pm);
                radioGroup2 = root.findViewById(R.id.end_am_pm);

                int radioId1 = radioGroup1.getCheckedRadioButtonId();
                int radioId2 = radioGroup2.getCheckedRadioButtonId();

                radioButton1 =  root.findViewById(radioId1);
                radioButton2 =  root.findViewById(radioId2);

                String start = startHour.getSelectedItem().toString() + ":" +startMin.getSelectedItem().toString() + " " + radioButton1.getText();
                String end = endHour.getSelectedItem().toString() + ":" + endMin.getSelectedItem().toString() + " " + radioButton2.getText();
                startTimeTv.setText(start);
                endTimeTv.setText(end);
                String dur = Dur.getSelectedItem().toString();


                //save strings to object

                //send object to database
                reff.child("Settings").child("Active Hours").child("End").setValue(end);
                reff.child("Settings").child("Active Hours").child("Start").setValue(start);
                reff.child("Settings").child("Duration").setValue(dur);

            }
        });

        return root;
    }



     public void On_Off(View v){

        if (!switchOn_Off.isChecked()){
            strURL = "https://icon-library.com/images/light-off-icon/light-off-icon-23.jpg"; //pass string of URL to async task to download image
            AsyncFrag asyncTask1=new AsyncFrag();
            asyncTask1.execute(strURL);

            Snackbar snackbar = Snackbar.make(v, R.string.switchOffLight, Snackbar.LENGTH_LONG); //implement snackbar to let user know state of the light was updated
            snackbar.setTextColor(Color.RED);
            snackbar.show();

        }
        if (switchOn_Off.isChecked()){
            AsyncFrag asyncTask2=new AsyncFrag(); //create async task object
            strURL = "https://cdn-icons-png.flaticon.com/512/702/702797.png"; //pass string of URL to async task to download image
            asyncTask2.execute(strURL);


            Snackbar snackbar = Snackbar.make(v, R.string.switchOnLight, Snackbar.LENGTH_LONG);//implement snackbar to let user know state of the light was updated
            snackbar.setTextColor(Color.RED);
            snackbar.show();
        }
    }


    private class AsyncFrag extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                ImageUrl = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection) ImageUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();//create bitmap
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            } else {

            }
        }
    }
}
