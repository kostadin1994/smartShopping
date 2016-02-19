package com.example.smartshopping;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserPage extends Activity {
	 Button Smart;
	 Button Shop;
	 
	 
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.userpage);
       Smart = (Button)findViewById(R.id.Smart);  
       // this will be the smart function 
       // database with all of the groceries for the user will be created (SQLlite) 
       // when item is bought the list is checked if the item exists , usage++ 
       // if not insert the item and add usage 1 
       // "Smart" will choose the top 5 products in your list or the top rated products :) 
       
       Shop = (Button)findViewById(R.id.Select);
       // when the button is clicked it redirects to the page where the shops are listed 
      Shop.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
    		  Intent i=new Intent(
                    UserPage.this,
                    shopList.class);
             startActivity(i);
           }

		
   });
     
       
   }
}