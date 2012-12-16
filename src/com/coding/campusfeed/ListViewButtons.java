package com.coding.campusfeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListViewButtons extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.eventListView);
		SimpleAdapter eventListAdapter = new SimpleAdapter(getBaseContext(),
				EventOrganizer.getList(), R.layout.activity_list_view_buttons,
				new String[] { "Events" }, new int[] { R.id.eventButton });
		listView.setAdapter(eventListAdapter);
		listView.setTextFilterEnabled(true);
		downloadEvents();
	}

	public void clickHandler(View v)
	{
		Toast.makeText(getBaseContext(), "Hey event fired", Toast.LENGTH_LONG)
				.show();
	}

	public void downloadEvents()
	{
		URL events = null;
		try
		{
			events = new URL("http://ezevents.6te.net/geteventsandroid.php");
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		BufferedReader in = null;
		String src = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(events.openStream()));
			src=in.readLine();
			in.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(src);
		while (src.contains("<br>"))
		{
			String info=src.substring(0,
					src.indexOf("<br>"));
			String[] infoArray=info.split(",,, ");
			EventOrganizer.addEvent(new Event(infoArray[0],infoArray[1],"Active"));
			src = src.substring(src.indexOf("<br>") + 4);
		}
	}
}