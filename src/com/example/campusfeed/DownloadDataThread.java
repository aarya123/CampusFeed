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
				EventOrganizer.addEvent(new Event(event.getString("unique_id"),
						event.getString("title"), "active", event
								.getString("location"),
						event.getString("user"), event.getString("category"),
						event.getString("desc"), event.getString("latlng"),
						event.getString("location_details"), event
								.getString("date")));
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

}
