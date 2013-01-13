package com.example.campusfeed;

import java.io.IOException;
import java.util.ArrayList;

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
	static String username = null, password = null, email = null;
	static ArrayList<String> starredEvents = null, createdEvents = null;
	static boolean signedIn = false;

	public static Boolean isSignedIn()
	{
		return signedIn;
	}

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

	public static void setStarredEvents(ArrayList<String> events)
	{
		starredEvents = events;
	}

	public static void setCreatedEvents(ArrayList<String> events)
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

	public static ArrayList<String> getStarredEvents()
	{
		return starredEvents;
	}

	public static ArrayList<String> getCreatedEvents()
	{
		return createdEvents;
	}

	public static Boolean contains(String uniqueID)
	{
		try
		{
			for (int i = 0; i < starredEvents.size(); i++)
			{
				if (starredEvents.get(i).equals(uniqueID))
					return true;
			}
			return false;
		} catch (NullPointerException e)
		{
			return false;
		}
	}

	public static void starEvent(String uniqueId)
	{
		starredEvents.add(uniqueId);
	}
}
