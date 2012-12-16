package com.coding.campusfeed;

import java.util.ArrayList;
import java.util.HashMap;

public class EventOrganizer
{
	static ArrayList<HashMap<String, Event>> eventList = new ArrayList<HashMap<String, Event>>();

	public static ArrayList<HashMap<String, Event>> getList()
	{
		return eventList;
	}

	public static void addEvent(Event event)
	{
		HashMap<String, Event> map = new HashMap<String, Event>();
		map.put("Events", event);
		eventList.add(map);
	}

	public static Event getEvent(int index)
	{
		return eventList.get(index).get("Events");
	}

	public static int getNumOfEvents()
	{
		return eventList.size();
	}
}
