package com.example.campusfeed;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

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
				if (!(listView.getItemAtPosition(0) == null))
				{
					Event goingTo = a.getItem(position);
					Intent eventInfo = new Intent(Tab1.this, EventInfo.class);
					eventInfo.putExtra("eventId", goingTo.getId());

					Tab1.this.startActivity(eventInfo);
				} else
				{
					// go to creating event page.
				}
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{
				// fire off the dialog box
				ListOptionsDialog.longClicked = a.getItem(arg2);
				FragmentManager fm = getFragmentManager();
				ListOptionsDialog d = new ListOptionsDialog();
				d.show(fm, "options");
				return false;
			}
		});
	}
}
