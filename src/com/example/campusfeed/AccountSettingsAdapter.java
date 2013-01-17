package com.example.campusfeed;

import com.example.campusfeed.EventOrganizer.Sorter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AccountSettingsAdapter extends ArrayAdapter<String>
{
	private Context c;
	public String[] choices;

	public AccountSettingsAdapter(Context context, int textViewResourceId,
			String[] choices)
	{
		super(context, textViewResourceId, choices);
		this.choices = choices;
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
			holder = new ViewHolder();
			holder.icon = (ImageView) v.findViewById(R.id.account_choice_icon);
			holder.choice = (TextView) v
					.findViewById(R.id.account_choice_textview);
			v.setTag(holder);
		} else
		{
			holder = (ViewHolder) v.getTag();
		}
		holder.choice.setText(choices[position]);
		if (choices[position].equals("Create an Event"))
		{
		} else if (choices[position].equals("My Created Events"))
		{
		} else if (choices[position].equals("My Starred Events"))
		
			{
				holder.icon.setImageResource(R.drawable.star);
		
		} else if (choices[position].equals("Log Out"))
		{
			holder.icon.setImageResource(R.drawable.arrow_left);
		} else if (choices[position].equals("Sign In"))
		{
			holder.icon.setImageResource(R.drawable.log_in);

		} else if (choices[position].equals("Create an Account"))
		{
		}
		return v;
	}
}