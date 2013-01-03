package com.example.campusfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1 extends Activity
{
	// full scope vars for use in async task
	public static ListView listView;
	public static ArrayAdapter<String> a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);

		CustomAdapter a = new CustomAdapter(getApplicationContext(), R.id.list,
				EventOrganizer.getEventNames(EventOrganizer.Sorter.popular));
		try
		{
			listView.setAdapter(a);
			listView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					if (!listView.getItemAtPosition(position).toString()
							.equals("No Events Today!"))
					{
						Intent eventInfo = new Intent(Tab1.this,
								EventInfo.class);
						Event e = (Event) listView.getItemAtPosition(position);
						Log.d("APP", e.getId());
						// eventInfo.putExtra("eventId",
						// );
						// Tab1.this.startActivity(eventInfo);
						// TODO fix this!
					}
				}
			});
		} catch (Exception e)
		{

		}

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
