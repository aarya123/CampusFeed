package com.example.campusfeed;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity implements
		SearchView.OnQueryTextListener
{
	// full scope vars for use in async task
	public static TabHost tabs;
	public PendingIntent pending;
	public AlarmManager manager;
	public static Object dl;

	public void onCreate(Bundle savedInstanceState)
	{
		dl = this.getSystemService(DOWNLOAD_SERVICE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Connection(getApplicationContext(), "ON_BOOT");
		// create tab host.
		tabs = getTabHost();
		// setup view flipper
		setupSlides();
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
		pending = PendingIntent.getService(getApplicationContext(), 00001,
				alarmmanager, PendingIntent.FLAG_CANCEL_CURRENT);
		manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),
				10000, pending);
		tabs.refreshDrawableState();
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);

		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();

		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{

	
		case R.id.adv_search:
			Intent advsrch = new Intent(this, AdvacedSearch.class);
			startActivity(advsrch);
			return true;

		case R.id.accSettings:
			Intent accSettings = new Intent(this, AccountSettings.class);
			startActivity(accSettings);
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{

		case R.id.adv_search:
			Intent advsrch = new Intent(this, AdvacedSearch.class);
			startActivity(advsrch);
			return true;
		case R.id.accSettings:
			Intent accSettings = new Intent(this, AccountSettings.class);
			startActivity(accSettings);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onPause()
	{
		super.onPause();
		manager.cancel(pending);
	}

	public void onResume()
	{
		super.onResume();
		Intent alarmmanager = new Intent(this, UpdateManager.class);
		pending = PendingIntent.getService(getApplicationContext(), 00001,
				alarmmanager, PendingIntent.FLAG_CANCEL_CURRENT);
		manager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),
				10000, pending);
	}

	public boolean onQueryTextChange(String newText)
	{
		return false;
	}

	public boolean onQueryTextSubmit(String query)
	{
		Log.d("APP", "submitted");
		return false;
	}

	public void setupSlides()
	{
		new GetPromos().execute();
		// download the promo flyers
		ViewPager pager = (ViewPager) findViewById(R.id.promotion_slider);

		PromotionsAdapter promos = new PromotionsAdapter();
		pager.setAdapter(promos);
		// add action swipe listener
	}

	class GetPromos extends AsyncTask<String, Void, String>
	{
		public ProgressDialog p;
		public boolean error = false;

		public boolean isOnline()
		{
			ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null)
			{
				return true;
			} else
			{
				return false;
			}
		}

		protected void onPreExecute()
		{
			p = ProgressDialog.show(MainActivity.this,
					"Starting Up Campus Feed", "Please Wait...", false);
		}

		protected String doInBackground(String... arg0)
		{
			if (!isOnline())
			{
				error = true;
				return null;
			} else
			{
				try
				{
					Thread.sleep(2200);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					return "done";
				}
				/*
				Bitmap[] promos = new Bitmap[5];
				try
				{
					InputStream pic1 = new URL(
							"http://ezevents.6te.net/promos/promotion1display.png")
							.openStream();
					InputStream pic2 = new URL(
							"http://ezevents.6te.net/promos/promo2.png")
				
							.openStream();
				
					promos[0] = BitmapFactory.decodeStream(pic1);
					promos[1] = BitmapFactory.decodeStream(pic2);
					promos[2] = promos[0];
					promos[3] = promos[0];
					promos[4] = promos[0];
					error = false;
				} catch (MalformedURLException e)
				{
					error = true;
					Log.d("APP", "ERROR IN BITMAP Get");
				} catch (IOException e)
				{
					error = true;
				}
				return promos;
				*/
				return "done";
			}
		}

		protected void onPostExecute(String result)
		{
			if (error)
			{
				p.setMessage("Internet Connection Required");
				p.dismiss();
			} else
			{
				if (result == null)
				{

				} else
				{
					/*
					Display screen = getWindowManager().getDefaultDisplay();
					Point size = new Point();
					ViewPager pager = (ViewPager) findViewById(R.id.promotion_slider);
					screen.getSize(size);
					int width = size.x;
					int height = pager.getHeight();
					PromotionsAdapter promos = new PromotionsAdapter(
						promotionImages, width, height);
					pager.setAdapter(promos);
					*/
					p.dismiss();
				}
			}
		}
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
	Context c;

	public Connection(Context c, String... params)
	{
		this.c = c;
		this.execute(params);
	}

	public boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) c
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	protected String doInBackground(String... params)
	{
		// check if there is a net
		// in this method, you do any network, and etc jobs.
		// call the download class
		DownloadDataThread main = new DownloadDataThread();
		// I changed method run to "Download"
		// check if user is connected to the internet
		if (isOnline())
		{
			main.Download();
			Log.d("APP", "IN BACKGROUND");
			return params[0];
		} else
		{
			return "OFFLINE";
		}
	}

	public void onPostExecute(String result)
	{
		if (result.equals("UPDATE"))
		{
			updateAllLists();
		} else if (result.equals("OFFLINE"))
		{
			Log.d("APP", "OFFLINE");
		}
	}

	public void updateAllLists()
	{
		try
		{
			Tab1.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.today));
			Tab2.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.popular));
		} catch (Exception e)
		{
			// since null pointer might go off if the array adapter has not
			// loaded yet.
		}
	}
}