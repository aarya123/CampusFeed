package com.example.campusfeed;

import java.util.ArrayList;
import java.util.HashMap;

public class EventOrganizer
{
	static ArrayList<HashMap<String, Event>> eventList = new ArrayList<HashMap<String, Event>>();

	public enum Sorter
	{
		popular, today, social, sports, organizations, academic, sales;
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

	public static String[] getEventNames(Enum sorter)
	{
		ArrayList<String> names = new ArrayList<String>();
		if (sorter == Sorter.popular)
		{
			// TODO
		}
		if (sorter == Sorter.today)
		{
			// TODO
		} else
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getCategory() == sorter.toString())
					names.add(getEvent(i).getName());
		}
		return (String[]) names.toArray();
	}

	public static Event getEventByName(String name)
	{
		for (int i = 0; i < getNumOfEvents(); i++)
			if (name.equals(getEvent(i).getName()))
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

	private int[] numbers;

	public void sort(int[] values)
	{
		if (values == null || values.length == 0 || values.length == 1)
		{
			return;
		}
		this.numbers = values;
		quicksort(0, values.length - 1);
	}

	private void quicksort(int low, int high)
	{
		int i = low, j = high;
		int pivot = numbers[low + (high - low) / 2];
		while (i <= j)
		{
			while (numbers[i] < pivot)
			{
				i++;
			}
			while (numbers[j] > pivot)
			{
				j--;
			}
			if (i <= j)
			{
				swap(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private void swap(int i, int j)
	{
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
