package com.coding.campusfeed;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class EventAdapter extends ArrayAdapter<Event>
{

	Context context;
	int layoutResourceId;
	ArrayList<Event> data = null;

	public EventAdapter(Context context, int layoutResourceId,
			ArrayList<Event> data)
	{
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		return data.get(position).getButton();
	}

}
