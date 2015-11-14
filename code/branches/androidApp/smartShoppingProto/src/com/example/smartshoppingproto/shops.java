package com.example.smartshoppingproto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
// this class reads throught a Json array created from the .php and it returns the names of the shops
	// in a listView 
public class shops extends Activity {
	 public void onCreate(Bundle savedInstanceState)
	  {
	    super.onCreate(savedInstanceState);
	 // set the content view
	    setContentView(R.layout.list_viewer);

	    try
	    {
	    	// create a new reader
	    JsonReader read =new JsonReader();
	      String jsonInput = "http://192.168.0.6/uniproject/shopList.php";
	    
	      // create a JsonArray of the items in the input 
	      JSONArray jsonArray = new JSONArray(JsonReader.readJsonFromUrl(jsonInput));
	      int length = jsonArray.length();
	      List<String> listContents = new ArrayList<String>(length);
	      for (int i = 0; i < length; i++)
	      {
	    	  // add shop names 
	        listContents.add(jsonArray.getString(i));
	      }

	      ListView myListView = (ListView) findViewById(R.id.shopName);
	      myListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContents));
	    }
	    catch (Exception e)
	    {
	      
	    }
	
}
}
