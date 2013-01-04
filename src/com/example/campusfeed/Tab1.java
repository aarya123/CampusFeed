package com.example.campusfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Tab1 extends Activity
{
	// full scope vars for use in async task
	public static ListView listView;
	public static CustomAdapter a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);
		a = new CustomAdapter(getApplicationContext(), R.id.list,
				EventOrganizer.getEvents(EventOrganizer.Sorter.today));
		listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (!listView.getItemAtPosition(position).toString()
						.equals("No Events Today!"))
				{
					Event goingTo = a.getItem(position);
					Intent eventInfo = new Intent(Tab1.this, EventInfo.class);
					Event e = (Event) listView.getItemAtPosition(position);
					Log.d("APP", e.getId());
					eventInfo.putExtra("eventId", goingTo.getId());
					Tab1.this.startActivity(eventInfo);
				}
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
			Log.d("APP", "REFRESH BUTTON PRESSED");
			Toast.makeText(getApplicationContext(), "Updated Event List",
					Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
