package com.example.smartshopping;
import java.util.ArrayList;
import java.util.Hashtable;

import com.example.utils.HelperHttp;
import com.example.utils.dbHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/* a class which implements the payment functionalities by fetching the balance of the user 
 and substracting the total price of the groceries by it if the balance is negative the user will
 be redirected to the top up page.
*/
// TODO create top up page 
import classes.groceryItem;

public class Payment extends Activity {

	Button button;
	TextView txt;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	  {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.payment);			 
		    Intent intent = getIntent();
		    final int Price= intent.getIntExtra("Price",0);
		    final SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
		    int balance = sp.getInt("balance", -1);
		    TextView textElement = (TextView) findViewById(R.id.payText);
		    final int newB = balance-Price;
		    // if the balance is sufficient the dialog will appear 
		    if(newB> 0 || newB==0){
		    	 AlertDialog.Builder builder = new AlertDialog.Builder(Payment.this);
	                builder.setTitle("Receipt");
	                builder.setMessage("Total price: "+Price+ "\n"+
	                					"Your balance is: "+"£"+ balance+"\n"+
	                					"New balance is: "+"£"+newB+"\n"+
	                					"Do you want to continue?")  
	                       .setCancelable(true)
	                       // OK button will launch the payment functionalities 
	                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id){
								executeAsyncTask();	                               
	           	            }
	           	        });
		    
	                // if the cancel button is clicked the user will be redirected to user page 
	                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        	 Intent intent = new Intent(Payment.this, UserPage.class);
                              startActivity(intent);  
                        }
                    }); 
	                AlertDialog alert = builder.create();
	                alert.show();              
		    	
		   
		    } else { 
		    	 textElement .setText("Insuficient funds,please, top up!");
		    	 
		    	
		    }
	  }
	}
	// method to execute the asynctask (payment) since connections have to be done on background
	private void executeAsyncTask(){
		  Hashtable<String,String> ht=new Hashtable<String,String>();
		 PaymentAsyncTask async=new PaymentAsyncTask();		  
		  Hashtable[] ht_array={ht};
		  async.execute(ht_array);
		 }
// a class which will execute the payment 
	private class PaymentAsyncTask extends AsyncTask<Hashtable<String,String>,Void,String>{
		 	// gets values from the intent and the Session 
			Intent intent = getIntent();
		    final int Price= intent.getIntExtra("Price",0);
		   final SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
		    int balance = sp.getInt("balance", -1);
		    final String username=sp.getString("username", "");
		    int newBalance=balance-Price;
		  @Override
		  protected String doInBackground(Hashtable<String,String>... params) {
		   Hashtable ht=params[0];
		   boolean Payment=HelperHttp.Pay("http://192.168.1.94/uniproject/payment.php", username, Price);
		   if (Payment==true){
			   Editor editor = sp.edit();
			   editor.putInt("balance", newBalance);
			   editor.commit();
		   Intent intent = new Intent(Payment.this, UserPage.class);
           startActivity(intent);  
					} else { 
						Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();
					}
				
			   
		  
		   
		   return null;
	
}
	}
}
