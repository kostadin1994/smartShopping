package com.example.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import classes.Shops;
import classes.groceryItem;

import com.example.smartshopping.*;

public class groceryAdapter extends ArrayAdapter<groceryItem>{


	private Context context;
	ArrayList<Shops> dataObject;
	public groceryAdapter(Context context, int textViewResourceId,
			ArrayList<groceryItem> dataObject) {
		super(context, textViewResourceId, dataObject);
		this.context=context;
	}


	// create the view
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView=convertView;
		if(rowView==null){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		rowView = inflater.inflate(R.layout.grocery_rows, parent, false);
		
		}
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		TextView textView1 = (TextView) rowView.findViewById(R.id.price);
		// gets the name and the price of the item at the given position 
		textView.setText(""+getItem(position).name);
		textView1.setText(""+"£"+getItem(position).Price+" per "+getItem(position).per);
		return rowView;
	}

	

	
	}