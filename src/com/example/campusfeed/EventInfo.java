package com.example.campusfeed;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EventInfo extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	        setupActionBar();
		setContentView(R.layout.activity_event_info);
		Event currentEvent = EventOrganizer.getEventById(getIntent()
				.getExtras().getString("eventId"));
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(currentEvent.getName());
		TextView description = (TextView) findViewById(R.id.eventInfo);
		description.setText(currentEvent.getDescription());
		TextView time = (TextView) findViewById(R.id.time);
		time.setText(currentEvent.getTime());
		TextView date = (TextView) findViewById(R.id.date);
		date.setText(currentEvent.getDate());
		TextView location = (TextView) findViewById(R.id.eventLocation);
		location.setText(currentEvent.getLocation());
		TextView locationSpecs = (TextView) findViewById(R.id.eventLocationSpecifics);
		locationSpecs.setText(currentEvent.getLocationSpecifics());
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.addMarker(new MarkerOptions().position(currentEvent.getLatLng())
				.title(currentEvent.getName()));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
				currentEvent.getLatLng(), 17));
		// set onLONGclick listener so once the use holds the touch for some seconds, it'll fire off into google
		// maps with the lat and lng.
		map.setOnMapLongClickListener(new OnMapLongClickListener(){

			@Override
			public void onMapLongClick(LatLng click) {
		
				LatLng location=currentEvent.getLatLng();
				
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("geo:0,0?q="+location.latitude+","+location.longitude+"(Location)"));
						startActivity(intent);
				
			}
	    	
	    })	;	
	
	}

	public void onClick(View v)
	{
		if (v.getId() == R.id.date)
		{
			System.out.println("Date");
		}
		if (v.getId() == R.id.time)
		{
			System.out.println("Time");
		}
		if (v.getId() == R.id.eventLocation)
		{
			System.out.println("Location");
		}
	}

	public void setupActionBar()
	{
		ActionBar bar = getActionBar();
		ColorDrawable actionBarColor = new ColorDrawable();
		actionBarColor.setColor(Color.rgb(49, 132, 189));
		bar.setTitle("CampusFeed");
		bar.setBackgroundDrawable(actionBarColor);
	}
}
