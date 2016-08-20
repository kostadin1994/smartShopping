package com.example.utils;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import classes.groceryItem;
/* this is a helper class which creates the internal database 
 * also it holds the methods which will be responsible for storing 
 * and managing the database 
 */
public class dbHelper extends SQLiteOpenHelper {
   private static final int DATABASE_VERSION = 1;
   private static final String DATABASE_NAME = "itemDB";
   private static final String TABLE_ITEMS = "items";
   private static final String KEY_NAME = "name";
   private static final String KEY_AMOUNT = "amount";
   public dbHelper(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);  
   }

// method is initialised when its run for the first time and it creates the table 
   public void onCreate(SQLiteDatabase db) {
       String CREATE_TABLE = "CREATE TABLE items ( " +
               "name TEXT PRIMARY KEY, " + 
               "amount INT)";
       db.execSQL(CREATE_TABLE);
       System.out.println("created");
   }

// deletes the table upon upgrade 
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS items");
       this.onCreate(db);
   }
// adds an item to the database 
   public void addItem(groceryItem item){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(KEY_NAME, item.getName()); 
       values.put(KEY_AMOUNT, 1); 
       db.insert(TABLE_ITEMS,
               null, 
               values);
       db.close(); 
       System.out.println("added");
   }
// returns a boolean if the item exists 
   public boolean exists(groceryItem item){
	   			String name = item.getName();
	   			item.getAmount();
		    SQLiteDatabase db = this.getWritableDatabase();
		    String selectString = "SELECT * FROM " + TABLE_ITEMS+ " WHERE " + KEY_NAME + " =?";
		    Cursor cursor = db.rawQuery(selectString, new String[] {name}); 
		    boolean to_return = false;
		    if(cursor.moveToFirst()){
		        to_return = true;
		        cursor.close();
		        db.close();
		        return to_return; 
		    } else { 
		    cursor.close();   
		    db.close();  
		    return false;
		    }
		
		}
  // returns a boolean which says whether the given item is frequently bought by the user      
   public boolean isPopular(groceryItem item){
			String name = item.getName();
			item.getAmount();
   SQLiteDatabase db = this.getWritableDatabase();
  // creates the statement which will be executed 
   String selectString = "SELECT * FROM " + TABLE_ITEMS+ " WHERE " + KEY_NAME + " =?"+" AND "+KEY_AMOUNT+" >?";
  // defines the values in the "?" 
   Cursor cursor = db.rawQuery(selectString, new String[] {name,String.valueOf(5)}); 
   boolean to_return = false;
   if(cursor.moveToFirst()){
       to_return = true;
       cursor.close();
       db.close();
       return to_return; 
   } else { 
   cursor.close();   
   db.close();  
   return false;
   }

}


   // returns the items in the db in the form of arrayList<groceryItem> 
   public ArrayList<groceryItem> getAllItems() {
       ArrayList<groceryItem> items = new ArrayList<groceryItem>();
       String query = "SELECT  * FROM items";
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(query, null);
       groceryItem item = null;
       if (cursor.moveToFirst()) {
           do {
        	   item = new groceryItem();
               item.setAmount(Integer.parseInt(cursor.getString(1)));
               item.setName(cursor.getString(0));
               items.add(item);
           } while (cursor.moveToNext());
       }
       return items;
   }

  // increase the value of the item by 1 
   public void update(groceryItem item) {
	   String name = item.getName();
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.execSQL("UPDATE " + TABLE_ITEMS + " SET "
                + KEY_AMOUNT + " = " + KEY_AMOUNT +"+1"+ " WHERE " + KEY_NAME + " =?" , new String[]{name});
               
db.close();
       
   }

}