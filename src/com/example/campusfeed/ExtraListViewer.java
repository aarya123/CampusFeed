package com.example.campusfeed;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ExtraListViewer extends Activity
{
	ListView listView;
	CustomAdapter a;

	protected void onCreate(Bundle savedInstanceState)
	{ 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_list_viewer);
		listView = (ListView) findViewById(R.id.extraListSorter);
		TextView sortTypeText = (TextView) findViewById(R.id.sortTypeText);
		sortTypeText.setText(sortTypeText.getText()
				+ getIntent().getExtras().getString("sort"));
		a = new CustomAdapter(getApplicationContext(), R.id.list,
				EventOrganizer.getEvents(EventOrganizer.Sorter
						.valueOf((getIntent().getExtras().getString("sort"))),
						EventOrganizer.getEventById(getIntent().getExtras()
								.getString("eventId"))));
		listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Event goingTo = a.getItem(position);
				Intent eventInfo = new Intent(ExtraListViewer.this,
						EventInfo.class);
				eventInfo.putExtra("eventId", goingTo.getId());
				ExtraListViewer.this.startActivity(eventInfo);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		//getMenuInflater().inflate(R.menu.activity_extra_list_viewer, menu);
		return true;
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

}