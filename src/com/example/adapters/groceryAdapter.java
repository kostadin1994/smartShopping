package com.example.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import classes.groceryItem;

import com.example.smartshopping.*;

public class groceryAdapter extends ArrayAdapter<groceryItem>{


	private Context context;
	public groceryAdapter(Context context, int textViewResourceId,
			ArrayList<groceryItem> dataObject) {
		super(context, textViewResourceId, dataObject);
		this.context=context;
		
	}


	// a method to create the view for every item. 
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView=convertView;
		if(rowView==null){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		rowView = inflater.inflate(R.layout.grocery_rows, parent, false);
		
		}
		
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		TextView textView1 = (TextView) rowView.findViewById(R.id.price);
		TextView suggestion = (TextView) rowView.findViewById(R.id.popular);
		// check if the item "suggested" by the "Smart" algorithm 
		boolean suggested = getItem(position).suggested;
	
		
		if (suggested ==true){
			textView.setText(""+getItem(position).name);
			// display suggestion if the item is found in the internal db
			suggestion.setVisibility(View.VISIBLE);
			suggestion.setTextColor(Color.CYAN);
		
		} else { 
			textView.setText(""+getItem(position).name);
			suggestion.setVisibility(View.GONE);
			
		}
		textView1.setText(""+"£"+getItem(position).Price+" per "+getItem(position).per);
		return rowView;
	
	
		
	}

	

	}
	