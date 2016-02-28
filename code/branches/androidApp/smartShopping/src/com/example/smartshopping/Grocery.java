package com.example.smartshopping;
import java.util.ArrayList;
import java.util.Hashtable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import classes.groceryItem;
import com.example.utils.*;
import com.example.adapters.*;
/* This activity creates a list of grocery items for the selected shop 
 * the async task will do this. When the list is generated the frequently bought 
 * items from it will be highlighted 
 */
public class Grocery extends Activity  {
	// reference to the datastructures used in the activity 
	ArrayList<groceryItem> groceryList=new ArrayList<groceryItem>();
	ArrayList<String> IDlist=new ArrayList<String>();
	Button Pay;
	   public void onCreate(Bundle savedInstanceState) {
	       
			super.onCreate(savedInstanceState);
	        setContentView(R.layout.grocery);
	       executeAsyncTask();
	     
					
	    }
	   private void executeAsyncTask(){
			  Hashtable<String,String> ht=new Hashtable<String,String>();
			 GetGroceryAsyncTask async=new GetGroceryAsyncTask();		  
			  Hashtable[] ht_array={ht};
			  async.execute(ht_array);
			  
			 }

/* this class contains the methods which will be initialised on background during the creation of the page 
 * 
 * 
 */
	private class GetGroceryAsyncTask extends AsyncTask<Hashtable<String,String>,Void,String>{
		Intent intent = getIntent();
		String id = intent.getStringExtra("ID");
		@Override
		  protected String doInBackground(Hashtable<String,String>... params) {
		   // get the corresponding Json string 
		   String json=HelperHttp.postGroceryID("http://192.168.0.3:1234/uniproject/grocery.php",id);
		 // if the string is not null parse the Json string in the shopList
		   if(json!=null) parseJsonString(groceryList,json);
		   else{
		    return "No items";
		   }
		   return "SUCCESS";
		  }
		 // populate the groceryList with values from the json query 
		  protected void parseJsonString(ArrayList<groceryItem> groceryList,String json){
			  final dbHelper db = new dbHelper(getApplicationContext());
			  try {
				   JSONArray array=new JSONArray(json);
				   for(int i=0;i<array.length();i++){
				    JSONObject j=array.getJSONObject(i);
				  // stores the value from the json array as objects
				    groceryItem item=new groceryItem();
				    groceryItem to_check = new groceryItem();
				    item.ID=j.optString("id","");
				    item.name=j.optString("name","");
				    item.setName(j.optString("name",""));
				    boolean exists = db.exists(item);
				    item.Price=j.optInt("price");
				    item.per=j.optString("per","");
				    item.Comments=j.optString("comment","");
				    item.exists=exists;
				    item.suggested=db.isPopular(item);
				     groceryList.add(item);		   
				   }
				   
				  } catch (JSONException e) {
				   e.printStackTrace();
				  }
			  
		  } 
		  
		  
		 //after the jsonArray is created their values will be procesed in this method. Then they will be converted by
		  // the shopAdapter class and a list of items will be produced. 
		  protected void onPostExecute(String result){
			  final dbHelper db = new dbHelper(Grocery.this);
			  
			  if(result=="SUCCESS")
			  {
				  
			Pay= (Button)findViewById(R.id.buttonPay);
			// populate the adapter with the groceryList items
			    groceryAdapter adapter=new groceryAdapter(Grocery.this,R.id.text1,groceryList);
		final ListView listv=(ListView)findViewById(R.id.Item);
			listv.setAdapter(adapter);		
			//listener for the button 
			   Pay.setOnClickListener(new OnClickListener() {
			    	  public void onClick(View v) {
			    		  	ArrayList <groceryItem> selected = new ArrayList<groceryItem>();
			    		  	int price=0;
			    		  	// creates a response for the chosen items and gives prints out the price 
			    		    StringBuffer responseText = new StringBuffer();
			    		    responseText.append("The following items were selected");
			    		    // counts how many items were selected 
			    		    int count=0; 
			    		    for(int i=0;i<groceryList.size();i++){
			    		     groceryItem item = groceryList.get(i);
			    		     
			    		     if(item.isSelected()){
			    		      responseText.append("\n" + item.getName());
			    		      price+=item.getPrice();
			    		      selected.add(item);
			    		      count++;
			    		     }
			    		    }
			    		   if (count>0){
			    		    final int totalPrice=price;
			    		    final ArrayList<groceryItem>items = selected;
			    		    responseText.append("\n"+"Total price for the sected items is: £"+price+"\n");
			    		    responseText.append("Do you want to buy the selected items");
			    	         /* When the OK button is clicked the user will be send to 
		                        * the payment page.The groceryList and the total price 
		                        * are send to the next page via Intent. They will be used 
		                        * for payment and internal db storage 
		                        */
			    		    AlertDialog.Builder builder = new AlertDialog.Builder(Grocery.this);
			                builder.setTitle("Receipt");
			                builder.setMessage(responseText)  
			                       .setCancelable(true)
			                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                           public void onClick(DialogInterface dialog, int id) {
			                        	   Intent intent = new Intent(Grocery.this, Payment.class);
			                        	   intent.putExtra("Price", totalPrice);
			                        	   String name;
			                                for(int i = 0; i < items.size(); i++){
			                                	groceryItem item= items.get(i);
			                                	if(db.exists(item)==true){
			                                		
			                                		db.update(item);
			                                		
			                                	} else {
			                                		db.addItem(item);
			                                	}
			                                }
			                                ArrayList<groceryItem> items = db.getAllItems();
			                                String to_return = null;
			                                for (int i = 0; i<items.size();i++){
			                                	groceryItem item = items.get(i);
			                                	String namez = item.getName()+item.amount;
			                                	to_return=to_return+namez;
			                                }
			                                Toast.makeText(getApplicationContext(), to_return, Toast.LENGTH_LONG).show();
			                        	  startActivity(intent);
			                           }
			                       }); 
			                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		                           public void onClick(DialogInterface dialog, int id) {
				                        
		                           }
		                       }); 
			                AlertDialog alert = builder.create();
			                alert.show();               
			              // prints out error message 
			           } else { 
			        	 
			    		    AlertDialog.Builder builderAlert = new AlertDialog.Builder(Grocery.this);
			                builderAlert.setTitle("List is empty");
			                builderAlert.setMessage("Please,select items")  
			                       .setCancelable(true)
			                       
			                       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			                           public void onClick(DialogInterface dialog, int id) {
			                        
			                           }
			                       }); 
			                AlertDialog alert = builderAlert.create();
			                alert.show();               
			           }
			    	  }
			    	  });
			  // key listener for item click events
			   listv.setOnItemClickListener(new OnItemClickListener() {
				   public void onItemClick(AdapterView<?> parent, View view,
				     int position, long id) {
					   // gets the item at the position where the event occured
					   groceryItem item = (groceryItem) parent.getItemAtPosition(position);
					  // checks if the item has been selected before 	
					   if(item.isSelected()){
					   		item.setSelected(false);
					   		view.setBackgroundColor(Color.WHITE);
					   // if its not the background color will change 		
					   	} else {
					   		item.setSelected(true);
					   		view.setBackgroundColor(Color.YELLOW);
					   		
					   	}
				   }
				  }); 
			  //long clicks will show additional information about the items 
			   listv.setLongClickable(true);
			
			   
			   listv.setOnItemLongClickListener(new OnItemLongClickListener() {
		            public boolean onItemLongClick(AdapterView<?> parent, View view,
		                    int position, long id) {
		            	  groceryItem item = (groceryItem) parent.getItemAtPosition(position);
		            	  String info = item.getDescription();
			    		    AlertDialog.Builder builder = new AlertDialog.Builder(Grocery.this);
			                builder.setTitle("Product description");
			                builder.setMessage(info)  
			                       .setCancelable(false)
			                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                           public void onClick(DialogInterface dialog, int id) {
			                           }
			                       });                     
			                AlertDialog alert = builder.create();
			                alert.show();               
		                
		              return true;
		            }
		        }); 
			
			  }
			  else{} 
			
		  } 
		  
		 }
	
}