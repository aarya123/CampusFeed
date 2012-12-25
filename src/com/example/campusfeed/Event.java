package com.example.campusfeed;

import java.text.SimpleDateFormat;
import java.util.*;

public class Event
{
	String name, id, status, location, host, category, description,
			coordinates, locationSpecifics;
	Date datetime;
	int interest;

	public Event(String id, String name, String status)
	{
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Event(String id, String name, String status, String location,
			String host, String category, String description,
			String coordinates, String locationSpecifics, String date,
			int interest)
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
		this.interest = interest;
	}

	public String getLocation()
	{
		return location;
	}

	public String getCategory()
	{
		return category;
	}

	public String getDescription()
	{
		return description;
	}

	public String getLocationSpecifics()
	{
		return locationSpecifics;
	}

	// good stuff happening here :D
	public String getDate()
	{
		// create a simpledateformat obj
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		return sdf.format(datetime);

	}

	// good stuff happening here :D
	public String getTime()
	{
		// create a simple date format obj
		// Set default timezone to EST
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		return sdf.format(datetime);
	}

	public Date getDateTime()
	{
		return datetime;
	}

	public String getName()
	{
		return name;
	}

	public int getInterest()
	{
		return interest;
	}

	public String toString()
	{
		return name + "\n" + status;
	}
}
