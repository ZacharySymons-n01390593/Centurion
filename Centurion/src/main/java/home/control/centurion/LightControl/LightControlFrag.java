//Andres Vargas(N01359071), Ibrahim Abdiaziz(N01394807), Zachary Symons(N01390593), Jonathan Alexandris (N01352690).
package home.control.centurion.LightControl;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import home.control.centurion.R;


public class LightControlFrag extends Fragment {

URL ImageUrl;
        ProgressDialog p;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    String strURL;
    Switch switchOn_Off = null;

    public LightControlFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lightcontrol, container, false);

        imageView = (ImageView) root.findViewById(R.id.imageView2);


        switchOn_Off = (Switch) root.findViewById(R.id.switchLight);
        switchOn_Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_Off();

            }
        });


        return root;
    }

    public void On_Off(){

        if (!switchOn_Off.isChecked()){
            strURL = "https://icon-library.com/images/light-off-icon/light-off-icon-23.jpg";
            AsyncTaskExample asyncTask1=new AsyncTaskExample();
            asyncTask1.execute(strURL);

        }
        if (switchOn_Off.isChecked()){
            AsyncTaskExample asyncTask2=new AsyncTaskExample();
            strURL = "https://cdn-icons-png.flaticon.com/512/702/702797.png";
            asyncTask2.execute(strURL);
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

                BitmapFactory.Options options = new BitmapFactory.Options();
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
