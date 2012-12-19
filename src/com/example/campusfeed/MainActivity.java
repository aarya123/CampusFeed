package com.example.campusfeed;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
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

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.eventListView);
		DownloadDataThread downloader = new DownloadDataThread();
		downloader.start();
		try
		{
			downloader.join(5000);
		} catch (Exception e)
		{
		} finally
		{
			ArrayAdapter<String> a = new ArrayAdapter<String>(this,
					android.R.layout.simple_selectable_list_item,
					EventOrganizer.getEventNames());
			listView.setAdapter(a);
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					Intent eventInfo = new Intent(MainActivity.this, EventInfo.class);
					eventInfo.putExtra("EventIndex", position);
					MainActivity.this.startActivity(eventInfo);
				}
			});
		}
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
}