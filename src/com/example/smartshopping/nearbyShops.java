package com.example.smartshopping;
import java.util.ArrayList;
import java.util.Hashtable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import classes.Shops;
import android.widget.AdapterView.OnItemClickListener;
import com.example.adapters.shopAdapter;
import com.example.utils.GPSTracker;
import com.example.utils.HelperHttp;
import android.view.View;
 /* This activity creates a list of shops which are in the radius selected by the user 
  * the radius is received via Intent from Locator.java 
  * 
  */
public class nearbyShops extends Activity {
	ArrayList<Shops> shopList=new ArrayList<Shops>();
	ArrayList<Shops> closeShops=new ArrayList<Shops>();
    Button btnShowLocation;
    GPSTracker gps;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);
        
        
        btnShowLocation = (Button) findViewById(R.id.Button01);
  
        executeAsyncTask();
      	
         
      
    }
    private void executeAsyncTask(){
		  Hashtable<String,String> ht=new Hashtable<String,String>();
		 GetShopAsyncTask async=new GetShopAsyncTask();		  
		  Hashtable[] ht_array={ht};
		  async.execute(ht_array);
		 }
	private class GetShopAsyncTask extends AsyncTask<Hashtable<String,String>,Void,String>{
		 
      
		public boolean isClose(Location loc, Location distance,int radius) {
				 
			  
			    double lat1 = loc.getLatitude();
			    double lat2 = distance.getLatitude();
			   double  lon1 = loc.getLongitude();
			    double lon2 = distance.getLongitude();
			    double earthRadius = 6371000; //meters
			    double dLat = Math.toRadians(lat2-lat1);
			    double dLng = Math.toRadians(lon2-lon1);
			    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
			               Math.sin(dLng/2) * Math.sin(dLng/2);
			    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			    float dist = (float) (earthRadius * c);
			    if(dist<radius){
			    	return true;
			    } else {
			    	return false;
			    }

			}
		  @Override
		  protected String doInBackground(Hashtable<String,String>... params) { 
			  Hashtable ht=params[0];
		   // get the Json from the URL
		   String json=HelperHttp.getJSONResponseFromURL("http://192.168.1.94/uniproject/shopList.php", ht);
		 // if the string is not null parse the Json string in the shopList
		   if(json!=null) parseJsonString(shopList,json);
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
				    shop.latitude=j.optDouble("latitude");
				    shop.longitude=j.optDouble("longitude");
				    shopList.add(shop);
				   }
				   
				   } catch (JSONException e) {
				   e.printStackTrace();
				  }
			  
		  } 
		  
		  
 /*  after the jsonArray is created their values will be procesed in this method. 
  *  Then they will be converted by the shopAdapter class and a list of items will be produced. 
		  */
		  protected void onPostExecute(String result){
			  Intent intent = getIntent();
			    final int radius= intent.getIntExtra("radius",0);
			  if(result=="SUCCESS")
			  {
				  
				  gps = new GPSTracker(nearbyShops.this);  
			        double latitude = gps.getLatitude();
			        double longitude = gps.getLongitude();
				  Location myLocation = new Location("myLocation");
			       myLocation.setLatitude(latitude);
			       myLocation.setLongitude(longitude);
			     
			       for(int i =0;i<shopList.size();i++){
			    	   Shops shop = shopList.get(i);
			    	   Location shopLocation = new Location("shopLocation");
			    	   shopLocation.setLatitude(shop.latitude);
			    	   shopLocation.setLongitude(shop.longitude);
			    	  
			    	   boolean close =isClose(myLocation, shopLocation,radius);
			    	   if(close==true){
			    		   closeShops.add(shop);
			    	
			    	   
			       } if (closeShops.isEmpty()){
			    	   Toast.makeText(getApplicationContext(), "No shops found. Please choose different option", Toast.LENGTH_SHORT).show();
			    	   Intent intentError = new Intent(nearbyShops.this, Locator.class);
			    	   startActivity(intentError);
			       }
			   shopAdapter adapter=new shopAdapter(nearbyShops.this,R.id.text1,closeShops);
			   final ListView listv=(ListView)findViewById(R.id.lv);
			   listv.setAdapter(adapter);
			   listv.setOnItemClickListener(new OnItemClickListener() {
				   
				   public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					   
		              Shops clickedShop=(Shops) parent.getItemAtPosition(position);
		              String name = clickedShop.name;
		              String ShopId=clickedShop.Shop_id;
		              Intent intent = new Intent(nearbyShops.this, Grocery.class);
		              intent.putExtra("shopName", name);
		              intent.putExtra("ID", ShopId);
		              startActivity(intent);  
					}
					  });
					
			  }
			  
			  }
			 
		 }
	}
}