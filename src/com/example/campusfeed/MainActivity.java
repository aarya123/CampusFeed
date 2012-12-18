package com.example.campusfeed;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
		ActionBar actionBar = getActionBar();
		actionBar.addTab(
				actionBar.newTab().setText("Popular")
						.setTabListener(new TabListener()
						{

							@Override
							public void onTabReselected(Tab tab,
									FragmentTransaction ft)
							{
								// TODO

							}

							@Override
							public void onTabSelected(Tab tab,
									FragmentTransaction ft)
							{
								// TODO

							}

							@Override
							public void onTabUnselected(Tab tab,
									FragmentTransaction ft)
							{
								// TODO

							}
						}), true);
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
					Toast.makeText(getApplicationContext(),
							"Clicked on item: " + position, Toast.LENGTH_SHORT)
							.show();
				}

			});
		}
	}
	/*public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.game_menu, menu);
	    return true;
	}*/
}