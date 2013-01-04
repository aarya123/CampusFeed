package com.example.campusfeed;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends TabActivity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Connection().execute("ON_BOOT");
		// create tab host.
		TabHost tabs = getTabHost();
		// tabs
		// first tab
		TabSpec tab1 = tabs.newTabSpec("Tab 1");
		tab1.setIndicator("Today");
		Intent today = new Intent(this, Tab1.class);
		tab1.setContent(today);
		// tab 2
		TabSpec tab2 = tabs.newTabSpec("Tab 2");
		tab2.setIndicator("Popular");
		Intent popular = new Intent(this, Tab2.class);
		tab2.setContent(popular);
		// tab 3
		TabSpec tab3 = tabs.newTabSpec("Tab 3");
		tab3.setIndicator("More");
		Intent more = new Intent(this, ExtraSorters.class);
		tab3.setContent(more);
		// add the tabs to the tabHOST
		tabs.addTab(tab1);
		tabs.addTab(tab2);
		tabs.addTab(tab3);
		// start up the background alarm manager for periodcal updates
		Intent alarmmanager = new Intent(this, UpdateManager.class);
		PendingIntent pending = PendingIntent.getService(
				getApplicationContext(), 00001, alarmmanager,
				PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),
				60000, pending);
		tabs.refreshDrawableState();
		// new Updater().execute();
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Executes whenever something on the action bar is clicked
	 */
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "Updated Events",
					Toast.LENGTH_LONG).show();
			new Connection().execute("UPDATE");
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}

/*
 * Inner AsyncTask class starts here. 
 * with it being an innner class
 * we can easily update elements of the ui since all elements would be public to to this class.
 * by using async tasks, we don't cause lag by joining on the main view thread. So basically, all elements come up streamlined.
 * 
 */
// the first generic is what doInBackground takes as a param.
// the second is for progress. I'll add that in later. We can use Int for
// it.
// the third is what onPostExecute takes as a param.
// ** doInBackground, once completed, will return it's value to
// onPostExecute. ***
// onPostExecute is what you use to update the ui thread.
class Connection extends AsyncTask<String, Void, String>
{
	// the return here will basically pass the string or whatever to
	// onPostExecute
	// maybe if we arent using this method to return our actual data, we
	// can use
	// --------> read // this return value to tell onPostExecute if we
	// did not connect. so if no, then set some ui element
	// to say, "Please have an internet connection ready" or
	// something...
	protected String doInBackground(String... params)
	{
		// in this method, you do any network, and etc jobs.
		// call the download class
		DownloadDataThread main = new DownloadDataThread();
		main.Download();
		Log.d("APP", "IN BACKGROUND");
		// the return here will basically pass the string or whatever to
		// onPostExecute
		// maybe if we arent using this method to return our actual data, we
		// can use
		// --------> read // this return value to tell onPostExecute if we
		// did not connect. so if no, then set some ui element
		// to say, "Please have an internet connection ready" or
		// something...
		return params[0];
	}

	public void onPostExecute(String result)
	{
		Log.d("APP", "FINISHED");
		if (result.equals("UPDATE"))
		{
			updateAllLists();
		}
	}

	public void updateAllLists()
	{
		try
		{
			Tab1.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.popular));
			Tab2.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.today));
		} catch (Exception e)
		{
			// since null pointer might go off if the array adapter has not
			// loaded yet
		}
	}
}
