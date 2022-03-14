//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
package home.control.centurion.LightControl;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import home.control.centurion.MainActivity;
import home.control.centurion.R;


public class LightControlFrag extends Fragment
 {

    URL ImageUrl;
    ProgressDialog p;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    String strURL;
    Switch switchOn_Off = null;
    TextView startTimeTv;
    private FloatingActionButton homebtn;

    public LightControlFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lightcontrol, container, false);

        imageView = (ImageView) root.findViewById(R.id.imageView2);
        //Spinner initialization
        Spinner mySpinner = (Spinner) root.findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.timerArray, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mySpinner.setAdapter(adapter);

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

        return root;
    }

    public void On_Off(View v){

        if (!switchOn_Off.isChecked()){
            strURL = "https://icon-library.com/images/light-off-icon/light-off-icon-23.jpg"; //pass string of URL to async task to download image
            AsyncTaskExample asyncTask1=new AsyncTaskExample();
            asyncTask1.execute(strURL);

            Snackbar snackbar = Snackbar.make(v, R.string.switchOffLight, Snackbar.LENGTH_LONG); //implement snackbar to let user know state of the light was updated
            snackbar.setTextColor(Color.RED);
            snackbar.show();

        }
        if (switchOn_Off.isChecked()){
            AsyncTaskExample asyncTask2=new AsyncTaskExample(); //create async task object
            strURL = "https://cdn-icons-png.flaticon.com/512/702/702797.png"; //pass string of URL to async task to download image
            asyncTask2.execute(strURL);


            Snackbar snackbar = Snackbar.make(v, R.string.switchOnLight, Snackbar.LENGTH_LONG);//implement snackbar to let user know state of the light was updated
            snackbar.setTextColor(Color.RED);
            snackbar.show();
        }
    }


    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {

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
