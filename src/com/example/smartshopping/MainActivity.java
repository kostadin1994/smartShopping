package com.example.smartshopping;
import java.util.Hashtable;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.utils.HelperHttp;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	Button b;
	    EditText user,pass;
	    TextView tv;
	    HttpPost httppost;
	    StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
	    private ProgressDialog pDialog;
	     
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main); 
	        b = (Button)findViewById(R.id.Button01);  
	        user = (EditText)findViewById(R.id.username);
	      pass= (EditText)findViewById(R.id.password);
	      // sets the input type to "password"
	       pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
	        tv = (TextView)findViewById(R.id.tv);
	         
	        b.setOnClickListener(new OnClickListener() {
				@Override
	            public void onClick(View v) {
  	            	new AttemptLogin().execute();
        
	            }
	        });
	    }
	    class AttemptLogin extends  AsyncTask<Hashtable<String,String>,Void,String>{
			boolean failure = false;

	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(MainActivity.this);
	            pDialog.setMessage("Attempting login...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

			@Override
			protected String doInBackground(Hashtable<String, String>... params) { {
				String username = user.getText().toString();
	            String password = pass.getText().toString();
	            try {
	                
	              

	                Log.d("request!", "starting");
	           
	                String json=HelperHttp.Login("https://www.youlocalapp.com/oauth2/2.0/signin", username, password);
	              // creates a session if the Login has returned a string different than "fail" 
	                if(!json.equals("fail")){
	            		JSONObject obj = new JSONObject(json);
	            		String Uname = obj.getString("fullname_lower");
	            		Toast.makeText(MainActivity.this, Uname, Toast.LENGTH_LONG).show();
	            		

	                	
	                	startActivity(new Intent(MainActivity.this, UserPage.class));
	                
	                } else {
	     		    return "error";
	     		   }
	            }catch (Exception e ){
	     			   
	     		   }
			}
			return null;
	     		   
	     		  }
	     
	     		  
	     		  
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once product deleted
	            pDialog.dismiss();
	            if (file_url != null){
	            	Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
	            }

	        }

			

		}
	     
}