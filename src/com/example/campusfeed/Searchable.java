package com.example.campusfeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.SearchManager;
import android.content.Intent;

/**
 * This class manages the searchview on the homepage
 */

public class Searchable extends Activity {

	public static ListView listView;

	public static CustomAdapter a;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);

		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);

	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
		{
			String query = intent.getStringExtra(SearchManager.QUERY);

			// Calling EventOrganizer to store list of events
			// in
			listView = (ListView) findViewById(R.id.searchlist);
			a = new CustomAdapter(getApplicationContext(), R.id.searchlist,
					EventOrganizer.searchEvents(query));
			listView.setAdapter(a);

			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					if (!(listView.getItemAtPosition(0) == null))
					{
						Event selectedEvent = a.getItem(position);
						Intent eventInfo = new Intent(Searchable.this, EventInfo.class);
						eventInfo.putExtra("eventId", selectedEvent.getId());

						Searchable.this.startActivity(eventInfo);
					}
				}

			});
		}
	}




}
