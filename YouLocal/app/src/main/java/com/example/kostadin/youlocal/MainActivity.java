package com.example.kostadin.youlocal;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
public class MainActivity extends AppCompatActivity {
    String username;
    String password;
    boolean firstClick=true;
    //butterknife used to inject the views instead of calling them programatically
    @InjectView(R.id.usernameInput)
    EditText usernameText;
    @InjectView(R.id.passwordInput)
    EditText passwordText;
    @InjectView(R.id.btn_login)
    Button loginButton;
    @InjectView(R.id.forgottenPass)
    TextView forgottenPass;
    @InjectView(R.id.UsernameIn)
    TextInputLayout userNameLayout;
    @InjectView(R.id.PasswordIn)
    TextInputLayout passwordLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inject the views
        ButterKnife.inject(this);
        // add animation to the elements
        userNameLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        passwordLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        passwordText.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        loginButton.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        forgottenPass.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        ImageView image1 = (ImageView) findViewById(R.id.Image);
        image1.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the API version of the device
                if (validate()) {
                            /* hides the button using reveal animation
                                this method is only available for devices with android 5.0 or
                                later
                             */
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;

                    if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                            animate(loginButton);
                          username = usernameText.getText().toString();
                          password = passwordText.getText().toString();
                          login(username,password);
                      }else {
                          loginButton.setBackgroundColor(Color.rgb(231,231,231));
                    username = usernameText.getText().toString();
                    password = passwordText.getText().toString();
                    login(username,password);
                }
            } }
        });

        forgottenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newX= passwordLayout.getX();
                float newY=passwordLayout.getY();
                float oldX=forgottenPass.getX();
                float oldY=forgottenPass.getY();
                if(firstClick){
                    passwordLayout.setVisibility(View.GONE);
                    Animation animation = new TranslateAnimation(oldX,newX,oldY,newY);
                    animation.setDuration(1000);
                    animation.start();
                    forgottenPass.setText("Back to login");
                    loginButton.setText("Reset");
                    firstClick=false;
                } else {
                    passwordLayout.setVisibility(View.VISIBLE);
                    Animation animation = new TranslateAnimation(newX,oldX,newY,oldY);
                    animation.setDuration(1000);
                    animation.start();
                    loginButton.setText("Login");
                    forgottenPass.setText("Forgotten password?");
                    firstClick=true;


                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

// validate the user input
    public boolean validate() {
        boolean valid = true;

        String email = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty()) {
            usernameText.setError("Please,enter your username");
            valid = false;
        } else {
            usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            passwordText.setError("Your password should be between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        return valid;
    }
    // this method is only available for phones with api > 21
    @TargetApi(21)
    private void animate(final View view) {
        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                initialRadius, 0);
        anim.setDuration(1000);
        // start the animation
        anim.start();
    }


    private void login(final String username, String password) {
        class LoginAsync extends AsyncTask<String, Void, String>{
            private Dialog loadingDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }
            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                //add the user inputs
                nameValuePairs.add(new BasicNameValuePair("email", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                   // make an httpPost request to the server
                    HttpPost httpPost = new HttpPost(
                            "https://www.youlocalapp.com/oauth2/2.0/signin");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    // record the response from the server
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    // conver the response to a string
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                try{
                    // convert the response to a JSONobject
                    JSONObject obj = new JSONObject(result);
                    // proceed to the next activity if the server hasnt returned "code"(error)
                    if(!obj.has("code")){
                        Intent intent = new Intent(MainActivity.this, profileActivity.class);
                       // get the values from the json String
                        String fullName= obj.getString("fullname");
                        String avatar= obj.getString("avatar");
                        String info = obj.getString("aboutMe");
                        // create a new object from the class user and set the parameters
                        User user = new User();
                        user.setName(fullName);
                        user.setAvatar(avatar);
                        user.setInfo(info);
                        intent.putExtra("user",user);
                        finish();

                        startActivity(intent);
                    }else {

                        // print out the error
                        String error = obj.getString("error");
                        if (error.equalsIgnoreCase("Please review these errors")){
                            Toast.makeText(getApplicationContext(), "Username and password are not correct,please try again", Toast.LENGTH_LONG).show();

                        }  if (error.equalsIgnoreCase("Please enter a correct Email and Password" )){
                            Toast.makeText(getApplicationContext(), "Please,enter a valid password", Toast.LENGTH_LONG).show();

                        } if (error.equalsIgnoreCase("Email is not valid or is used by another user" )){
                            Toast.makeText(getApplicationContext(), "Pleae,enter a valid email", Toast.LENGTH_LONG).show();
                        }
                        // stop the loading dialog
                        loadingDialog.dismiss();
                        // clear the user input
                        usernameText.setText(null);
                        passwordText.setText(null);
                    }
                } catch (JSONException exception){

                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }
}





