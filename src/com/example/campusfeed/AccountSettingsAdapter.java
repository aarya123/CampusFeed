package com.example.campusfeed;

import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountSettingsAdapter extends ArrayAdapter<String>
{
	private Context c;
	public String[] choices;

	public AccountSettingsAdapter(Context context, int textViewResourceId,
			String[] choices)
	{
		super(context, textViewResourceId, choices);
		this.choices=choices;
		this.c = context;
	
	}

	
	// hold the elements in the layout
	public static class ViewHolder
	{
		public TextView choice;
		public ImageView icon;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{

		View v = convertView;
		ViewHolder holder;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.account_settings_list, null);
			/*
			 * 
			 * Each option has an icon and a textview. 
			 * We need to set the icons accordingly. I already set the text accordingly. 
			 * 
			 */
			holder = new ViewHolder();        
			holder.icon =(ImageView)v.findViewById(R.id.account_choice_icon);
		
			holder.choice=(TextView)v.findViewById(R.id.account_choice_textview);
			v.setTag(holder);
		} else{
		
			
			holder = (ViewHolder) v.getTag();
		holder.choice.setText(choices[position]);
		switch(position){
		
		case 1:
		// set the icon accordingly here
			break;
		case 2:
			// set the icon accordingly here
			
			break;
			
		case 3: 
			// set the icon accordingly here
			break;
			
		case 4:
			// set the icon accordingly here
			break;
			
		case 5:
			// set the icon accordingly here
			break;
		}
		
	}
		return v;
	}

}