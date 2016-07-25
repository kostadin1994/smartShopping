package com.example.kostadin.youlocal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class profileActivity extends AppCompatActivity {
    // reference to the transformed image
    RoundImage round;
    TextView userName,Information,AboutMe;

    ImageView background;
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        // get the object which was created in the previous activity
        User user=(User) intent.getSerializableExtra("user");
        // get the url of the avatar
        String Avatar=user.getAvatar();
        imageView = (ImageView) findViewById(R.id.avatar);
        // calls the asyncTask to download the image
        DownloadPic task = new DownloadPic();
        task.execute(new String[] { Avatar });
        userName= (TextView)findViewById(R.id.fullName) ;
        background=(ImageView) findViewById(R.id.background);

    }
    // an async task which downloads the image from the url
    private class DownloadPic extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {

                map = downloadImage(url);
            }
            return map;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // get the intent
            Intent intent=getIntent();
            User user=(User) intent.getSerializableExtra("user");
            String uName=user.getName();
            // load elements and set their animations
            background.setImageResource(R.drawable.background);
            background.startAnimation(AnimationUtils.loadAnimation(profileActivity.this, android.R.anim.slide_in_left));
            round= new RoundImage(result);
            imageView.setImageDrawable(round);
            userName.setText(uName);
            userName.startAnimation(AnimationUtils.loadAnimation(profileActivity.this, android.R.anim.slide_in_left));
            AboutMe=(TextView)findViewById(R.id.aboutMe);
            AboutMe.setText("About me: ");
            AboutMe.startAnimation(AnimationUtils.loadAnimation(profileActivity.this, android.R.anim.slide_in_left));
            String userInfo=user.getInfo();
            Information=(TextView)findViewById(R.id.info);
            Information.setText(userInfo);
            Information.startAnimation(AnimationUtils.loadAnimation(profileActivity.this, android.R.anim.slide_in_left));
            // add scrolling functionality to the textView
            Information.setMovementMethod(new ScrollingMovementMethod());
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                // used to transform the input stream into a bitmap
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream used to download the image and save it in
        // the form of an input stream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
}
