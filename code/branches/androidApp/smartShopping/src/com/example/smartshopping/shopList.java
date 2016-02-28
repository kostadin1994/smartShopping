package com.example.smartshopping;
import java.util.ArrayList;
import java.util.Hashtable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import classes.Shops;

import com.example.utils.*;
import com.example.adapters.*;
// this activity will show all the shops in the database 
public class shopList extends Activity {
   // reference to the shoplist used in the class
	ArrayList<Shops> shopList=new ArrayList<Shops>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);
		executeAsyncTask();
    }
	private void executeAsyncTask(){
		  Hashtable<String,String> ht=new Hashtable<String,String>();
		 GetShopAsyncTask async=new GetShopAsyncTask();		  
		  Hashtable[] ht_array={ht};
		  async.execute(ht_array);
		 }
	// async task which will execute the functionalities of the activity 
	private class GetShopAsyncTask extends AsyncTask<Hashtable<String,String>,Void,String>{
  		
		  @Override
		  protected String doInBackground(Hashtable<String,String>... params) {
		   Hashtable ht=params[0];
		   // get the Json from the URL
		   String json=HelperHttp.getJSONResponseFromURL("http://192.168.0.3:1234/uniproject/shopList.php", ht);
		 // if the string is not null parse the Json string in the shopList
		   if(json!=null) parseJsonString(shopList,json);
		   else{
		    return "No shops found";
		   }
		   return "SUCCESS";
		  }
		  // a method which will create shop instances from the json arrayList
		  protected void parseJsonString(ArrayList<Shops> shopList,String json){
			  try {
				   JSONArray array=new JSONArray(json);
				   for(int i=0;i<array.length();i++){
				    JSONObject j=array.getJSONObject(i);
				  // create shops and assign values from the json array to the instances of the shop class
				    Shops shop=new Shops();
				    shop.Shop_id=j.optString("id","");
				    shop.name=j.optString("name","");
				    shopList.add(shop);
				   }
				   
				  } catch (JSONException e) {
				   e.printStackTrace();
				  }
			  
		  } 
		  
		  
		 //after the jsonArray is created their values will be procesed in this method. Then they will be converted by
		  // the shopAdapter class and a list of items will be produced. 
		  protected void onPostExecute(String result){
		   
			  if(result=="SUCCESS")
			  {
				
			   shopAdapter adapter=new shopAdapter(shopList.this,R.id.text1,shopList);
			   final ListView listv=(ListView)findViewById(R.id.lv);
			   listv.setAdapter(adapter);
			   listv.setOnItemClickListener(new OnItemClickListener() {
				   
				   public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					   
		              Shops clickedShop=(Shops) parent.getItemAtPosition(position);
		              String name = clickedShop.name;
		              String ShopId=clickedShop.Shop_id;
		              Intent intent = new Intent(shopList.this, Grocery.class);
		              intent.putExtra("shopName", name);
		              intent.putExtra("ID", ShopId);
		              startActivity(intent);  
					}
					  });
					
			  }
			  else{}
		  }
		  
		 }
	
	
}