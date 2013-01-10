package com.example.campusfeed;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
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
		setupActionBar();

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		// set the color of the action bar

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
		// tabs.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
		// //unselected
		tabs.setOnTabChangedListener(new OnTabChangeListener()
		{

			@Override
			public void onTabChanged(String arg0)
			{
				// TODO Auto-generated method stub
				// tabs.getTabWidget().getChildAt(tabs.getCurrentTab()).setBackgroundResource(R.drawable.tabone);

			}

		});

		// new Updater().execute();

	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "Updated Events",
					Toast.LENGTH_LONG).show();
			new Connection(getApplicationContext(), "UPDATE");
			return true;
		case R.id.signIn:
			return true;
		case R.id.createAcc:
			this.overridePendingTransition(R.anim.slide_in_up,
					R.anim.slide_out_down);
			Intent createAcc = new Intent(MainActivity.this,
					CreateAccount.class);
			MainActivity.this.startActivity(createAcc);
			return true;
		case R.id.create:
			Intent create = new Intent(this, createEvent.class);
			startActivity(create);
			return true;

		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		manager.cancel(pending);

	}

	@Override
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

	@TargetApi(14)
	public void setupActionBar()
	{
		ActionBar bar = getActionBar();
		ColorDrawable actionBarColor = new ColorDrawable();
		Color c = new android.graphics.Color();
		actionBarColor.setColor(Color.rgb(49, 132, 189));
		bar.setTitle("");
		// new TabWidget(getApplicationContext()).getChildAt(0).set

		bar.setBackgroundDrawable(actionBarColor);
		// new
		// TabHost(getApplicationContext()).getChildAt(0).setBackgroundResource(R.drawable.ic_launcher);

	}

	@Override
	public boolean onQueryTextChange(String newText)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query)
	{
		Log.d("APP", "submitted");

		return false;
	}

	public void setupSlides()
	{

		// download the promo flyers
		new GetPromos().execute();
		// add action swipe listener

	}

	class GetPromos extends AsyncTask<String, Void, Bitmap[]>
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

		@Override
		protected void onPreExecute()
		{
			p = ProgressDialog.show(MainActivity.this,
					"Starting Up Campus Feed", "Please Wait...", false);
		}

		@Override
		protected Bitmap[] doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub

			if (!isOnline())
			{
				error = true;
				return null;

			} else
			{

				Bitmap[] promos = new Bitmap[5];

				try
				{
					InputStream pic1 = new URL(
							"http://ezevents.6te.net/promos/promo1.jpg")
							.openStream();
					promos[0] = BitmapFactory.decodeStream(pic1);
					promos[1] = promos[0];
					promos[2] = promos[0];
					promos[3] = promos[0];
					promos[4] = promos[0];
					error = false;
				} catch (MalformedURLException e)
				{
					// TODO Auto-generated catch block
					error = true;
					Log.d("APP", "ERROR IN BITMAP Get");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					error = true;
				}
				return promos;
			}
		}

		@TargetApi(13)
		@Override
		protected void onPostExecute(Bitmap[] promotionImages)
		{
			if (error)
			{
				p.setMessage("Internet Connection Required");
				p.dismiss();
			} else
			{
				Display screen = getWindowManager().getDefaultDisplay();
				Point size = new Point();
				ViewPager pager = (ViewPager) findViewById(R.id.promotion_slider);
				screen.getSize(size);
				int width = size.x;
				int height = pager.getHeight();
				PromotionsAdapter promos = new PromotionsAdapter(
						promotionImages, width, height);

				pager.setAdapter(promos);

				p.dismiss();
			}
		}

	}

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

	@Override
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

	@Override
	public void onPostExecute(String result)
	{

		if (result.equals("UPDATE"))
		{
			updateAllLists();

		} else if (result.equals("OFFLINE"))
		{
			Log.d("APP", "OFFLINE");
		} else
		{

		}
	}

	public void updateAllLists()
	{
		try
		{

			Tab1.a.clear();
			Tab1.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.today));
			Tab1.a.notifyDataSetChanged();

			Tab2.a.clear();
			Tab2.a.add(EventOrganizer.getEvents(EventOrganizer.Sorter.popular));
			Tab2.a.notifyDataSetChanged();

		} catch (Exception e)
		{
			// since null pointer might go off if the array adapter has not
			// loaded yet.
		}
	}

}
