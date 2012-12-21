package com.example.campusfeed;

import java.io.IOException;
import java.net.URLDecoder;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.*;

public class DownloadDataThread extends Thread
{
	// I'll upgrade this class to work with when the user does not have an
	// internet connection, so it does not just crash.

	public void Download()
	{
		// USING HTTP OBJECTS
		// provided by Apache Foundation for android
		// more secure and fast
		HttpGet httpGet = new HttpGet(
				"http://ezevents.6te.net/playingaroundandroid.php");
		HttpClient h = new DefaultHttpClient();
		HttpResponse r = null;
		try
		{
			// execute request
			r = h.execute(httpGet);
		} catch (ClientProtocolException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		String response = null;
		String jsonArray = null;
		try
		{
			// will return the full json array outputted by php
			response = EntityUtils.toString(r.getEntity());
		} catch (ParseException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		// decode it.
		jsonArray = URLDecoder.decode(response);
		JSONArray json = null;
		try
		{
			// set the array to a jsonarray obj.
			json = new JSONArray(jsonArray);
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
