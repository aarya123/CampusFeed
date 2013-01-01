package com.example.campusfeed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EventOrganizer
{
	static ArrayList<HashMap<String, Event>> eventList = new ArrayList<HashMap<String, Event>>();

	public enum Sorter
	{
		popular, today, social, sports, organizations, academic, sales, location, date, time;
	}

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

	public static ArrayList<Event> getEventNames(Enum<Sorter> sorter)
	{
		ArrayList<Event> names = new ArrayList<Event>();
		if (sorter == Sorter.popular)
		{
			ArrayList<Event> events = new ArrayList<Event>();
			for (int i = 0; i < getNumOfEvents(); i++)
				events.add(getEvent(i));
			events = sort(events);

			for (int i = events.size() - 1; i > -1; i--)
				names.add(events.get(i));

		}
		if (sorter == Sorter.today)
		{
			Calendar c = Calendar.getInstance();
			String date = (c.get(Calendar.MONTH) + 1) + "/"
					+ c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR);

			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getDate().equals(date))
					names.add(getEvent(i));
			if (names.size() == 0)
				names.add(null);
		} else
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getCategory().equals(sorter.toString()))
					names.add(getEvent(i));
		}

		return names;
	}

	public static ArrayList<String> getEventNames(Sorter sorter, Event event)
	{
		ArrayList<String> names = new ArrayList<String>();

		if (sorter == Sorter.location)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getLocation().equals(event.getLocation()))
					names.add(getEvent(i).getName());
		}
		if (sorter == Sorter.date)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getDate().equals(event.getDate()))
					names.add(getEvent(i).getName());
		}
		if (sorter == Sorter.time)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getDateTime().equals(event.getDateTime()))
					names.add(getEvent(i).getName());
		}
		return names;
	}

	public static Event getEventByName(String name)
	{
		for (int i = 0; i < getNumOfEvents(); i++)
			if (name.equalsIgnoreCase(getEvent(i).getName()))
				return getEvent(i);
		return null;
	}

	public static Event getEventById(String id)
	{
		for (int i = 0; i < getNumOfEvents(); i++)
			if (id.equalsIgnoreCase(getEvent(i).getId()))
				return getEvent(i);
		return null;
	}

	public static String[] getEventNames()
	{
		String[] eventNames = new String[getNumOfEvents()];
		for (int i = 0; i < eventNames.length; i++)
			eventNames[i] = getEvent(i).getName();
		return eventNames;
	}

	private static ArrayList<Event> sort(ArrayList<Event> events)
	{
		if (events == null || events.size() == 0 || events.size() == 1)
		{
			return events;
		}
		return quicksort(0, events.size() - 1, events);
	}

	private static ArrayList<Event> quicksort(int low, int high,
			ArrayList<Event> toSort)
	{
		int i = low, j = high;
		Event pivot = toSort.get(low + (high - low) / 2);
		while (i <= j)
		{
			while (toSort.get(i).getInterest() < pivot.getInterest())
			{
				i++;
			}
			while (toSort.get(j).getInterest() > pivot.getInterest())
			{
				j--;
			}
			if (i <= j)
			{
				swap(i, j, toSort);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j, toSort);
		if (i < high)
			quicksort(i, high, toSort);
		return toSort;
	}

	private static void swap(int i, int j, ArrayList<Event> list)
	{
		Event temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

}
