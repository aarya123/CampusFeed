package com.example.campusfeed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab3 extends Activity
{
	// full scope vars for use in async task
	ListView listView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		String[] events = new String[] { "Social", "Sales", "Organizational",
				"Sports", "Academic" };
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);
		ArrayAdapter<String> a = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_layout, R.id.text,
				events);
		listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{

			}

		});

	}
}
