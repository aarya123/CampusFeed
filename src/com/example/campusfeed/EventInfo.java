package com.example.campusfeed;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class EventInfo extends Activity
{
	public Event currentEvent;
	public ProgressBar fetching;
	public ImageView posterspot;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);
		currentEvent = EventOrganizer.getEventById(getIntent().getExtras()
				.getString("eventId"));
		// fetching =(ProgressBar)findViewById(R.id.fetchPosteAndHandout);

		TextView description = (TextView) findViewById(R.id.eventInfo);
		description.setText(currentEvent.getDescription());
		TextView time = (TextView) findViewById(R.id.time);
		time.setText(currentEvent.getTime());
		TextView date = (TextView) findViewById(R.id.date);
		TextView user = (TextView) findViewById(R.id.postedBy);
		user.setText("By: " + currentEvent.getUser());
		date.setText(currentEvent.getDate());
		TextView location = (TextView) findViewById(R.id.eventLocation);
		posterspot = (ImageView) findViewById(R.id.event_info_poster_spot);
		Button numViews =(Button)findViewById(R.id.button1);
		numViews.setText("  "+currentEvent.getInterest());
		Button numStarred=(Button)findViewById(R.id.button2);
		numStarred.setText("  "+currentEvent.starredNum);
		numStarred.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("APP", "STARRED");
			}
		});
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(currentEvent.getName());
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
		// Toast.makeText(getApplicationContext(), currentEvent.posterPath,
		// Toast.LENGTH_LONG).show();
		try
		{
			UrlImageViewHelper.setUrlDrawable(posterspot,
					"http://ezevents.6te.net/" + currentEvent.posterPath);
		} catch (Exception e)
		{
			Log.d("Error", e.toString());
		}
		// set onLongClick listener so once the use holds the touch for some
		// seconds, it'll fire off into google maps with the lat and lng.
		map.setOnMapLongClickListener(new OnMapLongClickListener()
		{
			public void onMapLongClick(LatLng click)
			{
				LatLng location = currentEvent.getLatLng();

				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("geo:0,0?q=" + location.latitude + ","
								+ location.longitude + "("+currentEvent.getName()+")"));
				startActivity(intent);
			}
		});
		posterspot.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Log.d("clicked", "imageview");
				Intent viewer = new Intent(v.getContext(), ViewPoster.class);
				viewer.putExtra("url", "http://ezevents.6te.net/"
						+ currentEvent.posterPath);
				startActivity(viewer);

			}
		});
	}

	public void onClick(View v)
	{
		if (v.getId() == R.id.date)
		{
			Intent extraListViewer = new Intent(this, TDLSortViewer.class);
			extraListViewer.putExtra("sort", "date");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			startActivity(extraListViewer);
		}
		if(v.getId()==R.id.button2){
			// then call star the event
			Log.d("APP", "CLICKED ON STAR");
		}
		if (v.getId() == R.id.time)
		{
			Intent extraListViewer = new Intent(this, TDLSortViewer.class);
			extraListViewer.putExtra("sort", "time");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			startActivity(extraListViewer);
		}
		if (v.getId() == R.id.eventLocation)
		{
			Intent extraListViewer = new Intent(this, TDLSortViewer.class);
			extraListViewer.putExtra("sort", "location");
			extraListViewer.putExtra("eventId", currentEvent.getId());
			startActivity(extraListViewer);
		}

	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_event_info, menu);
		// SearchView
		// search=(SearchView)menu.findItem(R.id.menu_search).getActionView();
		// search.setQueryHint("Search an Organization or event name");
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	class getPosterandHandouts extends AsyncTask<String, Void, Bitmap>
	{
		protected Bitmap doInBackground(String... arg0)
		{
			Bitmap bitmap = null;
			try
			{
				// url = new URL("http://ezevents.6te.net/"
				// + currentEvent.posterPath);
				// URLConnection connection = url.openConnection();
				// get the files
				Log.d("GET", "http://ezevents.6te.net/"
						+ currentEvent.posterPath);
				// InputStream pic1=new
				// URL("http://ezevents.6te.net/"+currentEvent.posterPath).openStream();
				// bitmap=BitmapFactory.decodeStream(pic1);
			} catch (Exception e)
			{
				Log.d("ERROR", e.getMessage());
				return null;
			}
			return bitmap;
		}

		protected void onPostExecute(Bitmap poster)
		{
			if (poster == null)
			{

			} else
			{

			}
		}
	}
	class AccountOnline extends AsyncTask<String, Void, String>
	{
		protected String doInBackground(String... params)
		{
			if (params[2].equals("star"))
			{
				HttpGet httpGet = new HttpGet(
						"http://ezevents.6te.net/accounts_mobile.php?username="
								+ params[0] + "&starring_event_id=" + params[1]
								+ "&action=star_event");
				HttpClient h = new DefaultHttpClient();
				HttpResponse r = null;
				try
				{
					// execute request
					r = h.execute(httpGet);
				} catch (ClientProtocolException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				try
				{
					// will return the full json array outputted by php
					Accounts.s = EntityUtils.toString(r.getEntity());
				} catch (ParseException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				new DownloadDataThread().Download();
			
				return "STARRED";
				// connect to php file and add in starred
			} else
			{
				HttpGet httpGet = new HttpGet(
						"http://ezevents.6te.net/accounts_mobile.php?username="
								+ params[0] + "&unstarring_event_id="
								+ params[1] + "&action=unstar_event");
				HttpClient h = new DefaultHttpClient();
				HttpResponse r = null;
				try
				{
					// execute request
					r = h.execute(httpGet);
				} catch (ClientProtocolException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				try
				{
					// will return the full json array outputted by php
					Accounts.s = EntityUtils.toString(r.getEntity());
				} catch (ParseException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				return "ELSE";
			}
		}
		@Override
		protected void onPostExecute(String result){
			
			if(result=="STARRED"){
				
			}
			else{
				
			}
		}
		

	}

}