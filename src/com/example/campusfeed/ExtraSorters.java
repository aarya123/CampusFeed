package com.example.campusfeed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ViewSwitcher;

public class ExtraSorters extends Activity
{
	ListView choiceListView, eventListView;
	ViewSwitcher switcher;
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
				CustomAdapter b = null;
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
						Intent eventInfo = new Intent(ExtraSorters.this,
								EventInfo.class);
						eventInfo.putExtra("EventName", eventListView
								.getItemAtPosition(position).toString());
						ExtraSorters.this.startActivity(eventInfo);
					}
				});
			}
		});
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
