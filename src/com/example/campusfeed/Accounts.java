package com.example.campusfeed;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.AsyncTask;
import android.util.Log;

public class Accounts
{
	// TODO Fix this
	static String s;
	static String username, password, email;
	static String[] starredEvents, createdEvents;

	public static void setUsername(String user)
	{
		username = user;
	}

	public static void setPassword(String pass)
	{
		password = pass;
	}

	public static void setEmail(String addy)
	{
		email = addy;
	}

	public static void setStarredEvents(String[] events)
	{
		starredEvents = events;
	}

	public static void setCreatedEvents(String[] events)
	{
		createdEvents = events;
	}

	public static String getUsername()
	{
		return username;
	}

	public static String getEmail()
	{
		return email;
	}

	public static String getPassword()
	{
		return password;
	}

	public static String[] getStarredEvents()
	{
		return starredEvents;
	}

	public static String[] setCreatedEvents()
	{
		return createdEvents;
	}

	public static void starEvent(String uniqueId, String email)
	{
		new AccountOnline().execute(uniqueId, email);
	}
}

class AccountOnline extends AsyncTask<String, Void, String>
{
	protected String doInBackground(String... params)
	{
		HttpGet httpGet = new HttpGet(
				"http://ezevents.6te.net/accounts_mobile.php?ACCOUNT="
						+ params[1] + "&event_id=" + params[0]);
		HttpClient h = new DefaultHttpClient();
		HttpResponse r = null;
		try
		{
			// execute request
			r = h.execute(httpGet);
		} catch (ClientProtocolException e1)
		{
			Log.d("ERROR", e1.getMessage());
		} catch (IOException e1)
		{
			Log.d("ERROR", e1.getMessage());
		}
		try
		{
			// will return the full json array outputted by php
			Accounts.s = EntityUtils.toString(r.getEntity());
		} catch (ParseException e1)
		{
			Log.d("ERROR", e1.getMessage());
		} catch (IOException e1)
		{
			Log.d("ERROR", e1.getMessage());
		}
		return "DONE";
		// connect to php file and add in starred
	}
}