package com.example.campusfeed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Tab2 extends Activity
{
	// full scope vars for use in async task
	public ListView listView;
	public static CustomAdapter a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		MenuItem item = (MenuItem) findViewById(R.id.refresh);
		listView = (ListView) findViewById(R.id.list);
		// get array then turn into array list

		a = new CustomAdapter(getApplicationContext(), R.id.list,
				EventOrganizer.getEventNames(EventOrganizer.Sorter.popular));

		listView.setAdapter(a);

		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Event goingTo = a.getItem(position);
				Intent eventInfo = new Intent(Tab2.this, EventInfo.class);
				eventInfo.putExtra("eventId", goingTo.getId());
				Tab2.this.startActivity(eventInfo);
			}
		});

	}

	/**
	 * Executes whenever something on the action bar is clicked
	 */
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.refresh:
			new Connection().execute();
			Log.d("APP", "BUTTON PRESSED");
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
