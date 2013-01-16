package com.example.campusfeed;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AdvacedSearch extends Activity
{
	public EditText title;
	public static String date=null;
	public static Date time;
	public Spinner LocationChooser;
	public static Button TimeChooser,DateChooser, submit;
	public static ListView listView;
	public static CustomAdapter a;
	
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.advanced_search);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // force into portrait
		
																			
		
		// String[] locations=new
		// String[]{"Lawson Computer Science Building","Purdue Student Health Center","Cary Quadrangle"};
		
		title = (EditText) findViewById(R.id.advsrch_eventtitle);
		TimeChooser = (Button) findViewById(R.id.advsrch_settime);
		DateChooser = (Button) findViewById(R.id.advsrch_setdate);
		LocationChooser = (Spinner) findViewById(R.id.advsrch_setlocation);
		submit = (Button) findViewById(R.id.advsrch_submit);
		
		// Setting action listeners for the buttons
		
		TimeChooser.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				TimePickerEvent.fromAdv = true;
				
				FragmentManager fm = getFragmentManager();
				TimePickerEvent t = new TimePickerEvent();
				t.show(fm, "time_picker");
			}
		});
		DateChooser.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				DatePicker.fromAdv = true;
				FragmentManager fm = getFragmentManager();
				DatePicker d = new DatePicker();
				d.show(fm, "date_picker");
			}
		});
		submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				setContentView(R.layout.activity_searchable);	
				
				// Ignoring "ENTER"
				
				String arrtemp[] = title.getText().toString().split("\n");
				
				int numberOfMatches = EventOrganizer.adv_searchEvents(arrtemp[0], 
						LocationChooser.getSelectedItem().toString(),date, time).size();
				
				TextView queryinfo = (TextView)findViewById(R.id.number_of_matches);
				queryinfo.setText(numberOfMatches + " result(s)");
				
				// Setting the ListView
				
				listView = (ListView) findViewById(R.id.searchlist);
				a = new CustomAdapter(getApplicationContext(), R.id.searchlist,
						EventOrganizer.adv_searchEvents(arrtemp[0], 
								LocationChooser.getSelectedItem().toString(),date, time));
				listView.setAdapter(a);
				
				date = null;
				time = null;

				listView.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id)
					{
						if (!(listView.getItemAtPosition(0) == null))
						{
							Event selectedEvent = a.getItem(position);
							Intent eventInfo = new Intent(AdvacedSearch.this, EventInfo.class);
							eventInfo.putExtra("eventId", selectedEvent.getId());

							AdvacedSearch.this.startActivity(eventInfo);
						}
					}

				});
			}
		});
		
		
	}
	
	public static void setTime(Date chosenTime)
	{
		time = chosenTime;
	}
	
	public static void setDate(String chosenDate)
	{
		date = chosenDate;
	}
	
	// When SUBMIT button is pressed
	
	/*public void onClick(View v)
	{
		setContentView(R.layout.activity_searchable);
		
		int numberOfMatches = EventOrganizer.adv_searchEvents(title.getText().toString(), 
				LocationChooser.getSelectedItem().toString(),date, time).size();
		
		TextView queryinfo = (TextView)findViewById(R.id.number_of_matches);
		queryinfo.setText(numberOfMatches + " result(s)");
		
		// Setting the ListView
		
		listView = (ListView) findViewById(R.id.searchlist);
		a = new CustomAdapter(getApplicationContext(), R.id.searchlist,
				EventOrganizer.adv_searchEvents(title.getText().toString(), 
						LocationChooser.getSelectedItem().toString(),date, time));
		listView.setAdapter(a);

		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (!(listView.getItemAtPosition(0) == null))
				{
					Event selectedEvent = a.getItem(position);
					Intent eventInfo = new Intent(AdvacedSearch.this, EventInfo.class);
					eventInfo.putExtra("eventId", selectedEvent.getId());

					AdvacedSearch.this.startActivity(eventInfo);
				}
			}

		});
	}*/
}
