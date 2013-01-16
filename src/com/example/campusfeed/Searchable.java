package com.example.campusfeed;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.SearchManager;
import android.content.Intent;

/**
 * This class manages the searchview on the homepage
 */

public class Searchable extends Activity
{
	ListView listView;
	CustomAdapter a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		handleIntent(getIntent());
	}

	protected void onNewIntent(Intent intent)
	{
		setIntent(intent);
		handleIntent(intent);
	}

	private void handleIntent(Intent intent)
	{
		if (Intent.ACTION_SEARCH.equals(intent.getAction()))
		{
			String query = intent.getStringExtra(SearchManager.QUERY);
			// getting number of results
			int numberOfMatches = EventOrganizer.searchEvents(query).size();
			TextView queryinfo = (TextView) findViewById(R.id.number_of_matches);
			queryinfo.setText("Searching for '" + query + "' ("
					+ numberOfMatches + " result(s))");
			// Storing results in listView using CustomAdapter
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
						Intent eventInfo = new Intent(Searchable.this,
								EventInfo.class);
						eventInfo.putExtra("eventId", selectedEvent.getId());
						Searchable.this.startActivity(eventInfo);
					}
				}
			});
		}
	}
}