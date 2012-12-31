package com.example.campusfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab2 extends Activity
{
	// full scope vars for use in async task
	private ListView listView;
	public static ArrayAdapter<String> a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);
		// get array and bind the data to the ListView
		a = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_layout, R.id.text,
				EventOrganizer.getEventNames(EventOrganizer.Sorter.popular));
		listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent eventInfo = new Intent(Tab2.this, EventInfo.class);
				eventInfo.putExtra("EventName",
						listView.getItemAtPosition(position).toString());
				Tab2.this.startActivity(eventInfo);
			}
		});
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		return getParent().onMenuItemSelected(featureId, item);
	}
}