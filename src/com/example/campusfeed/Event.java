package com.example.campusfeed;

import android.content.Context;
import android.view.View;
import java.util.*;
import android.widget.*;

public class Event
{
	String name, id, status, location, host, category, description,
			coordinates, locationSpecifics;
	static Context CONTEXT;
	Date datetime;

	public Event(String id, String name, String status)
	{
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Event(String id, String name, String status, String location,
			String host, String category, String description,
			String coordinates, String locationSpecifics, String date)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.location = location;
		this.host = host;
		this.category = category;
		this.description = description;
		this.coordinates = coordinates;
		this.locationSpecifics = locationSpecifics;
		this.datetime = new Date(Long.parseLong(date) * 1000);
	}

	public String getLocation()
	{
		return location;
	}

	public String getDescription()
	{
		return description;
	}

	public String getLocationSpecifics()
	{
		return locationSpecifics;
	}

	// Weird stuff happening here... :(
	public String getDate()
	{
		return (datetime.getMonth() + 1) + "/" + datetime.getDate() + "/"
				+ (datetime.getYear() + 1900);
	}

	// Weird stuff happening here... :(
	public String getTime()
	{
		if (datetime.getHours() == 0)
		{
			if (datetime.getMinutes() > 9)
				return 12 + ":" + datetime.getMinutes() + " AM";
			else
				return 12 + ":0" + datetime.getMinutes() + " AM";
		} else if (datetime.getHours() > 12)
		{
			if (datetime.getMinutes() > 9)
				return datetime.getHours() - 12 + ":" + datetime.getMinutes()
						+ " PM";
			else
				return datetime.getHours() - 12 + ":0" + datetime.getMinutes()
						+ " PM";
		} else
		{
			if (datetime.getMinutes() > 9)
				return datetime.getHours() + ":" + datetime.getMinutes()
						+ " AM";
			else
				return datetime.getHours() + ":0" + datetime.getMinutes()
						+ " AM";
		}
	}

	public Date getDateTime()
	{
		return datetime;
	}

	public String getName()
	{
		return name;
	}

	public static Event parseString(String urlSRC)
	{
		String[] array = urlSRC.split(", ");
		return new Event(array[0], array[1], "Active");
	}

	public String toString()
	{
		return name + "\n" + status;
	}

	public Button getButton()
	{
		Button button = new Button((Context) CONTEXT);
		button.setText(name);
		button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				// TODO
			}
		});
		return button;
	}

	public static void setContext(Context context)
	{
		CONTEXT = context;
	}
}
