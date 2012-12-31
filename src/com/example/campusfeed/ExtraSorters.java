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
				getApplicationContext(), R.layout.list_layout, R.id.text,
				events);
		choiceListView.setAdapter(a);
		switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		// switcher.showNext();
		choiceListView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				clickCount = 1;
				eventListView = (ListView) findViewById(R.id.extraList);
				switcher.showNext();
				ArrayAdapter<String> b = null;
				if (position == 0)
				{
					b = new ArrayAdapter<String>(
							getApplicationContext(),
							R.layout.list_layout,
							R.id.text,
							EventOrganizer
									.getEventNames(EventOrganizer.Sorter.social));
				}
				if (position == 1)
				{
					b = new ArrayAdapter<String>(
							getApplicationContext(),
							R.layout.list_layout,
							R.id.text,
							EventOrganizer
									.getEventNames(EventOrganizer.Sorter.sales));
				}
				if (position == 2)
				{
					b = new ArrayAdapter<String>(
							getApplicationContext(),
							R.layout.list_layout,
							R.id.text,
							EventOrganizer
									.getEventNames(EventOrganizer.Sorter.organizations));
				}
				if (position == 3)
				{
					b = new ArrayAdapter<String>(
							getApplicationContext(),
							R.layout.list_layout,
							R.id.text,
							EventOrganizer
									.getEventNames(EventOrganizer.Sorter.sports));
				}
				if (position == 4)
				{
					b = new ArrayAdapter<String>(
							getApplicationContext(),
							R.layout.list_layout,
							R.id.text,
							EventOrganizer
									.getEventNames(EventOrganizer.Sorter.academic));
				}
				eventListView.setAdapter(b);
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
