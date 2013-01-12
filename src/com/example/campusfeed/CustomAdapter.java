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

public class CustomAdapter extends ArrayAdapter<Event>
{
	private Context c;
	public ArrayList<Event> events;

	public CustomAdapter(Context context, int textViewResourceId,
			ArrayList<Event> events)
	{
		super(context, textViewResourceId, events);

		this.c = context;
		this.events = events;
	}

	// add method
	public void add(ArrayList<Event> list)
	{
		this.clear();
		this.addAll(list);
		this.notifyDataSetChanged();
	}

	// hold the elements in the layout
	public static class ViewHolder
	{
		public TextView one, two;
		public ImageView status;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		ViewHolder holder;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_layout, null);
			holder = new ViewHolder();
			holder.one = (TextView) v.findViewById(R.id.title);

			holder.two = (TextView) v.findViewById(R.id.date_time);
			holder.status = (ImageView) v.findViewById(R.id.imageView1);
			v.setTag(holder);
		} else
			holder = (ViewHolder) v.getTag();

		if (events.get(position) != null)
		{
			holder.one.setText(events.get(position).getName());
			holder.two.setText(events.get(position).getDateandTime());
			Date date = events.get(position).getDateTime();
			if (date.before(new Date()))
				// set the icon to a green dot
				holder.status.setImageResource(R.drawable.event_on);
			else
				holder.status.setImageResource(R.drawable.event_not_yet);
			if (Accounts.contains(events.get(position).getId()))
				v.setBackgroundColor(Color.YELLOW);
			else
				v.setBackgroundColor(Color.WHITE);
		} else
		{
			holder.one.setText("No events going on yet!");
			holder.two.setText("Why don't you add one?");
		}
		return v;
	}

}
