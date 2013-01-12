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
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

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
				"http://ezevents.6te.net/mobile_sorter_final.php");
		HttpClient h = new DefaultHttpClient();
		HttpResponse r = null;
		try
		{
			// execute request
			r = h.execute(httpGet);
		} catch (ClientProtocolException e1)
		{
			Log.d("APP", "ERROR1");
		} catch (IOException e1)
		{
			Log.d("APP", "ERROR2");
		}
		String response = null;
		String jsonArray = null;
		try
		{
			// will return the full json array outputted by php
			response = EntityUtils.toString(r.getEntity());
		} catch (ParseException e1)
		{
			Log.d("APP", "ERROR3");
		} catch (IOException e1)
		{
			Log.d("APP", "ERROR4");
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
			Log.d("APP", e.toString());
		}
		// I am just clearing out the list for now and adding in all the data.
		// The problem was with deleting events and the arraylist still keeping
		// it. The memoy use is still low.
		EventOrganizer.eventList.clear();
		for (int i = 0; i < json.length(); i++)
		{
			try
			{
				JSONObject event = json.getJSONObject(i);
				EventOrganizer.addEvent(new Event(event.getString("unique_id"),
						event.getString("title"), "active", event
								.getString("location"),
						event.getString("user"), event.getString("category"),
						event.getString("desc"), event
								.getString("location_details"), event
								.getString("date"), event.getInt("interest"),
						event.getString("latlng").replace(" ", "")
								.replace("\n", ""), null, null));

			} catch (JSONException e)
			{
				Log.d("APP", e.toString() + i);
			}
		}
	}

	// DISREGARD THIS. IT IS NOT IN USE YET.
	public static void getPoster(String unique_id)
	{

		DownloadManager.Request r = new DownloadManager.Request(
				Uri.parse("http://ezevents.6te.net/posters" + unique_id));
		// This put the download in the same Download dir the browser uses
		r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
				"fileName");
		r.setAllowedOverRoaming(false);
		// When downloading music and videos they will be listed in the player
		// (Seems to be available since Honeycomb only)
		r.allowScanningByMediaScanner();

		// Notify user when download is completed
		// (Seems to be available since Honeycomb only)
		r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

		// Start download
		DownloadManager dm = (DownloadManager) MainActivity.dl;
		dm.enqueue(r);
	}

}
