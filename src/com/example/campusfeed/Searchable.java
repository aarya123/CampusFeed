package com.example.campusfeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      
	      // Calling EventOrganizer to obtain list of events
	      listView = (ListView) findViewById(R.id.searchlist);
			a = new CustomAdapter(getApplicationContext(), R.id.searchlist,
					EventOrganizer.searchEvents(query));
			listView.setAdapter(a);
	      
	      EventOrganizer.searchEvents(query);
	      Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
	    }
	}
	
	
	

}
