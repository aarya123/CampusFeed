package com.example.campusfeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;

import org.json.*;

public class DownloadDataThread extends Thread
{
	// events LinkedList
	LinkedList<String> eventTitleLinked = new LinkedList<String>();
	// events title string array
	String[] eventTitles = null;
	String connected = null;

	public void run()
	{
		URL events = null;
		try
		{
			events = new URL("http://ezevents.6te.net/playingaroundandroid.php");
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		BufferedReader in = null;
		String src = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(events.openStream()));
			src = in.readLine();
			in.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// decode the json string
		src = URLDecoder.decode(src);
		System.out.println(src);
		JSONArray json = null;
		try
		{
			json = new JSONArray(src);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		for (int i = 0; i < json.length(); i++)
		{
			try
			{
				JSONObject event = json.getJSONObject(i);
				// add the title to the linkedlist for titles
				// just store the titles into the linked list.
				EventOrganizer.addEvent(new Event(event.getString("unique_id"),
						event.getString("title"), "active", event
								.getString("location"),
						event.getString("user"), event.getString("category"),
						event.getString("desc"), event.getString("latlng"),
						event.getString("location_details"), event
								.getString("date")));
				//eventTitleLinked.add(event.optString("title"));
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}

		// after adding all of the titles, we will convert the linked list into
		// an array of strings
		//eventTitles = new String[eventTitleLinked.size()];
		// convert the linked list into a string array.
		//eventTitleLinked.toArray(eventTitles);
	}

}
