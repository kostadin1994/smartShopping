package com.example.smartshopping;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserPage extends Activity {
	 Button Shop;
	 Button topUp;
	 Button locator;
	
	 
	 
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.userpage);
       topUp = (Button)findViewById(R.id.topUp);         
       Shop = (Button)findViewById(R.id.Select);
      locator = (Button)findViewById(R.id.locator);
       // when the button is clicked it redirects to the page where the shops are listed 
      Shop.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
    		  Intent i=new Intent(
                    UserPage.this,
                    shopList.class);
             startActivity(i);
           }
   });
      topUp.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
    		  Intent i=new Intent(
                      UserPage.this,
                      TopUp.class);
               startActivity(i);
    		  
           }
   });
      locator.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
    		  Intent i=new Intent(
                      UserPage.this,
                      Locator.class);
               startActivity(i);
    		  
           }
   });
     
       
   }
}