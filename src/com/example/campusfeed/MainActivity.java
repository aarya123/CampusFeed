package com.example.campusfeed;

import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity
{
	// full scope vars for use in async task
	ListView listView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Connection().execute();
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
		Intent more = new Intent(this, Tab3.class);
		tab3.setContent(more);
		// add the tabs to the tabHOST
		tabs.addTab(tab1);
		tabs.addTab(tab2);
		tabs.addTab(tab3);
		tabs.refreshDrawableState();
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*public boolean onMenuItemSelected(int featureId, MenuItem item)
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
	}*/

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
	}

}
