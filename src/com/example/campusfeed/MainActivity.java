package com.example.campusfeed;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	// full scope vars for use in async task
	ListView listView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.eventListView);
		// start async task
		new Connection().execute();

	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.more:
			Toast.makeText(getApplicationContext(), "Clicked on more",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.popular:
			Toast.makeText(getApplicationContext(), "Clicked on popular",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.today:
			Toast.makeText(getApplicationContext(), "Clicked on today",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "Clicked on refresh",
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	// NOTE: Right now the listview shows up with WHITE TEXT!!. i'll fix that
	// tomorrow!
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
	public class Connection extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			// in this method, you do any network, and etc jobs.
			// call the download class
			DownloadDataThread main = new DownloadDataThread();
			// I changed method run to "Download"
			main.Download();

			// the return here will basically pass the string or whatever to
			// onPostExecute
			// maybe if we arent using this method to return our actual data, we
			// can use
			// --------> read // this return value to tell onPostExecute if we
			// did not connect. so if no, then set some ui element
			// to say, "Please have an internet connection ready" or
			// something...
			return "complete";
		}

		@Override
		protected void onPostExecute(String events)
		{
			/*
			 * Once done we can then update the ui and set onclick listeners and etc.
			 
			 */
			// set the array adapter.
			 ArrayAdapter<String> a = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_selectable_list_item,
					EventOrganizer.getEventNames());
			// listview var has full scope so we can use it in this inner class.
			listView.setAdapter(a);
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					Intent eventInfo = new Intent(MainActivity.this,
							EventInfo.class);
					System.out.println(listView.getItemAtPosition(position).toString());
					eventInfo.putExtra("EventName", listView.getItemAtPosition(position).toString());
					MainActivity.this.startActivity(eventInfo);
				}
			});

		}
	}

}
