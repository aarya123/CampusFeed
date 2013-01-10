package com.example.campusfeed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ViewSwitcher;

public class ExtraSorters extends Activity
{
	ListView choiceListView, eventListView;
	ViewSwitcher switcher;
	CustomAdapter a, b;
	int clickCount;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_sorters);
		clickCount = 0;
		String[] events = new String[] { "Social", "Sales", "Organizational",
				"Sports", "Academic" };
		choiceListView = (ListView) findViewById(R.id.choiceList);
		ArrayAdapter<String> a = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.plainlistlayout,
				R.id.eventTitle, events);
		choiceListView.setAdapter(a);
		switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		choiceListView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				clickCount = 1;
				eventListView = (ListView) findViewById(R.id.extraList);
				 b = null;
				if (position == 0)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.social));
				else if (position == 1)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.sales));
				else if (position == 2)
					b = new CustomAdapter(
							getApplicationContext(),
							R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.organizations));
				else if (position == 3)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.sports));
				else if (position == 4)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.academic));
				eventListView.setAdapter(b);
				switcher.showNext();
				eventListView.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id)
					{
						Event goingTo = (Event) b.getItem(position);
						Log.d("APP", goingTo.getId());
						Intent eventInfo = new Intent(ExtraSorters.this,
								EventInfo.class);
						eventInfo.putExtra("eventId", goingTo.getId());
						ExtraSorters.this.startActivity(eventInfo);
					}
				});
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
			Toast.makeText(getApplicationContext(),
		               "Updated Events", Toast.LENGTH_LONG).show();

			new Connection(getApplicationContext(),"UPDATE");
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	public void onBackPressed()
	{
		if (clickCount == 1)
		{
			switcher.showPrevious();
			clickCount = 0;
		} else
			super.onBackPressed();
	}
}