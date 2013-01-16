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
	public String posterPath, handoutPath;

	public Event(String id, String name, String status, String location,
			String host, String category, String description,
			String locationSpecifics, String date, int interest, String latlng,
			String posterPath, String handoutPath)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.location = location;
		this.posterPath = posterPath;
		this.handoutPath = handoutPath;
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

	public String getDateandTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy h:mm a");
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
	
	//get hour (Used by SearchView)
	
	public String getHour()
	{
		// create a simple date format obj
		// Set default timezone to EST
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		SimpleDateFormat sdf = new SimpleDateFormat("ha");
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

	public LatLng getLatLng()
	{
		return latlng;
	}

	public String getId()
	{
		return this.id;
	}

	public String getHost()
	{
		return host;
	}
	
	// This tag will be used by the SearchView widget
	
	public String getSimpleTag()
	{
		return (name+" "+location+" "+getHour());
	}
	
	// This tag will be used by advanced search
	
	/*public String getAdvancedTag()
	{
		return (name+location+getHour()+);
	}*/
}