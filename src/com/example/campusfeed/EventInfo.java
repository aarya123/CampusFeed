package com.example.campusfeed;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class EventInfo extends Activity
{

	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);
		Event currentEvent = EventOrganizer.getEvent(getIntent().getExtras().getInt("EventIndex"));
		TextView name = (TextView)findViewById(R.id.name);
		name.setText(currentEvent.getName());
		TextView description = (TextView)findViewById(R.id.eventInfo);
		description.setText(currentEvent.getDescription());
		TextView time = (TextView)findViewById(R.id.time);
		time.setText(currentEvent.getTime());
		TextView date = (TextView)findViewById(R.id.date);
		date.setText(currentEvent.getDate());
		TextView location = (TextView)findViewById(R.id.eventLocation);
		location.setText(currentEvent.getLocation());
		TextView locationSpecs=(TextView)findViewById(R.id.eventLocationSpecifics);
		locationSpecs.setText(currentEvent.getLocationSpecifics());
	}

}
