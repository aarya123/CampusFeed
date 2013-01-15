package com.example.campusfeed;

import java.io.File;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AdvacedSearch extends Activity
{
	public EditText title;
	public static String date, time;
	//public Spinner location;
	public static Button setTime,setDate, setLocation;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // force into portrait
																			
		setContentView(R.layout.advanced_search);
		// String[] locations=new
		// String[]{"Lawson Computer Science Building","Purdue Student Health Center","Cary Quadrangle"};
		
		title = (EditText) findViewById(R.id.advsrch_eventtitle);
	    setTime = (Button) findViewById(R.id.advsrch_settime);
		setDate = (Button) findViewById(R.id.advsrch_setdate);
		setLocation = (Button) findViewById(R.id.advsrch_setlocation);
		
		// Setting action listeners for the buttons
		
		setTime.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				FragmentManager fm = getFragmentManager();
				TimePickerEvent t = new TimePickerEvent();
				t.show(fm, "time_picker");
			}
		});
		setDate.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				FragmentManager fm = getFragmentManager();
				DatePicker d = new DatePicker();
				d.show(fm, "date_picker");
			}
		});
		
		setLocation.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				FragmentManager fm = getFragmentManager();
				TimePickerEvent t = new TimePickerEvent();
				t.show(fm, "time_picker");
			}
		});
	}
}
