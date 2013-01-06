package com.example.campusfeed;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventInfo extends Activity {
	Event currentEvent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);
		currentEvent = EventOrganizer.getEventById(getIntent().getExtras()
				.getString("eventId"));
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
		// set onLONGclick listener so once the use holds the touch for some
		// seconds, it'll fire off into google
		// maps with the lat and lng.
		map.setOnMapLongClickListener(new OnMapLongClickListener() {
			public void onMapLongClick(LatLng click) {
				LatLng location = currentEvent.getLatLng();

				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("geo:0,0?q=" + location.latitude + ","
								+ location.longitude + "(Location)"));
				startActivity(intent);
			}
		});

	}

	public void onClick(View v) {
		if (v.getId() == R.id.date) {
			Intent extraListViewer = new Intent(EventInfo.this,
					ExtraListViewer.class);
			extraListViewer.putExtra("sort", "date");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			EventInfo.this.startActivity(extraListViewer);
		}
		if (v.getId() == R.id.time) {
			Intent extraListViewer = new Intent(EventInfo.this,
					ExtraListViewer.class);
			extraListViewer.putExtra("sort", "time");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			EventInfo.this.startActivity(extraListViewer);
		}
		if (v.getId() == R.id.eventLocation) {
			Intent extraListViewer = new Intent(EventInfo.this,
					ExtraListViewer.class);
			extraListViewer.putExtra("sort", "location");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			EventInfo.this.startActivity(extraListViewer);
		}
	}

	/**
	 * Executes whenever something on the action bar is clicked
	 */
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "Updated Events",
					Toast.LENGTH_LONG).show();

			new Connection().execute("UPDATE");
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}