package com.example.campusfeed;

import java.text.SimpleDateFormat;
import com.google.android.gms.maps.model.LatLng;
import java.util.*;

public class Event
{
	private String name, id, status, location, host, category, description,
			locationSpecifics;
	private Date datetime;
	private int interest;
	private LatLng latlng;

	/**
	 * Constructor for event
	 * 
	 * @param id
	 * @param name
	 * @param status
	 * @param location
	 * @param host
	 * @param category
	 * @param description
	 * @param locationSpecifics
	 * @param date
	 * @param interest
	 * @param latlng
	 */
	public Event(String id, String name, String status, String location,
			String host, String category, String description,
			String locationSpecifics, String date, int interest, String latlng)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.location = location;
		this.host = host;
		this.category = category;
		this.description = description;
		this.locationSpecifics = locationSpecifics;
		this.datetime = new Date(Long.parseLong(date) * 1000);
		this.interest = interest;
		String[] latAndLang = latlng.split(",");
		this.latlng = new LatLng(Double.parseDouble(latAndLang[0]),
				Double.parseDouble(latAndLang[1]));
	}

	/**
	 * Updates the event
	 * 
	 * @param id
	 * @param name
	 * @param status
	 * @param location
	 * @param host
	 * @param category
	 * @param description
	 * @param locationSpecifics
	 * @param date
	 * @param interest
	 * @param latlng
	 */
	public void Update(String id, String name, String status, String location,
			String host, String category, String description,
			String locationSpecifics, String date, int interest, String latlng)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.location = location;
		this.host = host;
		this.category = category;
		this.description = description;
		this.locationSpecifics = locationSpecifics;
		this.datetime = new Date(Long.parseLong(date) * 1000);
		this.interest = interest;
		String[] latAndLang = latlng.split(",");
		this.latlng = new LatLng(Double.parseDouble(latAndLang[0]),
				Double.parseDouble(latAndLang[1]));
	}

	/**
	 * Gets the location (building)
	 * 
	 * @return building
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * Gets the category of the event
	 * 
	 * @return category
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * Gets the event description
	 * 
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Gets location specifics, like room number
	 * 
	 * @return location specifics
	 */
	public String getLocationSpecifics()
	{
		return locationSpecifics;
	}

	/**
	 * Gets the date of the event
	 * 
	 * @return date of the event
	 */
	public String getDate()
	{
		// create a simpledateformat obj
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		return sdf.format(datetime);

	}

	/**
	 * Gets date and time as M/d/yy/h:mm a
	 * 
	 * @return date and time
	 */
	public String getDateandTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy h:mm a");
		return sdf.format(datetime);
	}

	/**
	 * Gets the time of the event in EST
	 * 
	 * @return time
	 */
	public String getTime()
	{
		// create a simple date format obj
		// Set default timezone to EST
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		return sdf.format(datetime);
	}

	/**
	 * Gets date and time of the event
	 * 
	 * @return time of the event
	 */
	public Date getDateTime()
	{
		return datetime;
	}

	/**
	 * Gets the event name
	 * 
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets amount of interest in an event
	 * 
	 * @return event interest
	 */
	public int getInterest()
	{
		return interest;
	}

	/**
	 * Gets event name and status
	 * 
	 * @return name and status
	 */
	public String toString()
	{
		return name + "\n" + status;
	}

	/**
	 * Gets latitude and longitude location
	 * 
	 * @return LatLng for google map usage
	 */
	public LatLng getLatLng()
	{
		return latlng;
	}

	/**
	 * Gets the event id
	 * 
	 * @return id
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 * Get the host for the event
	 * 
	 * @return host
	 */
	public String getHost()
	{
		return host;
	}
}
