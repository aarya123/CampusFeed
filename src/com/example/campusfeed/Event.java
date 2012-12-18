package com.example.campusfeed;

import android.content.Context;
import android.view.View;
import android.widget.*;

public class Event
{
	String name, id, status, location, host, category, description,
			coordinates, locationSpecifics, date;
	static Context CONTEXT;

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
		this.date = date;
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
