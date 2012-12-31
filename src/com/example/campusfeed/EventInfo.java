package com.example.campusfeed;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventInfo extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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
		map.addMarker(new MarkerOptions().position(
				currentEvent.getLatLng()).title("Home"));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentEvent.getLatLng(),17));
		
		
		
	}
}
