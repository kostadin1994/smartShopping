package com.example.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import classes.Shops;

import com.example.smartshopping.*;
// adapter class which will "place" Json array in the textview 
public class shopAdapter extends ArrayAdapter<Shops>{

	private Context context;
	ArrayList<Shops> dataObject;
	public shopAdapter(Context context, int textViewResourceId,
			ArrayList<Shops> dataObject) {
		super(context, textViewResourceId, dataObject);
		this.context=context;
	}

	@Override
	// create the view
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView=convertView;
		if(rowView==null){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		rowView = inflater.inflate(R.layout.row_layout, parent, false);
		
		}
		TextView textView = (TextView) rowView.findViewById(R.id.text1);
		TextView textView1 = (TextView) rowView.findViewById(R.id.text2);
		textView.setText(""+getItem(position).name);
		textView1.setText("Distance to shop");

		
		return rowView;
	}

	

	

}
