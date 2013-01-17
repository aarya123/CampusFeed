package com.example.campusfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class EventOrganizer
{
	static ArrayList<Event> eventList = new ArrayList<Event>();

	public enum Sorter
	{
		popular, today, social, sports, organizations, academic, sales, location, date, time, myCreatedEvents, myStarredEvents;
	}

	/**
	 * Gets the holding ArrayList<HashMap<String, Event>>
	 * 
	 * @return returns ArrayList<HashMap<String, Event>>
	 */
	public static ArrayList<Event> getList()
	{
		return eventList;
	}

	/**
	 * Add event to the list of events
	 * 
	 * @param event
	 *            to add
	 */
	public static void addEvent(Event event)
	{
		eventList.add(event);
	}

	/**
	 * Gets a specified event by index
	 * 
	 * @param index
	 *            of event wanted
	 * @return event specified
	 */
	public static Event getEvent(int index)
	{
		return eventList.get(index);
	}

	/**
	 * Gets the number of events in the list
	 * 
	 * @return amount of events
	 */
	public static int getNumOfEvents()
	{
		return eventList.size();
	}

	/**
	 * Gets a list of events depending on the sort type
	 * 
	 * @param Enum
	 *            <Sorter> method
	 * @return ArrayList<Event> in order of method
	 */
	public static ArrayList<Event> getEvents(Sorter sorter)
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
		} else if (sorter == Sorter.today)
		{
			Calendar c = Calendar.getInstance();
			String date = (c.get(Calendar.MONTH) + 1) + "/"
					+ c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR);
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getDate().equals(date))
					names.add(getEvent(i));
		} else if (sorter == Sorter.myCreatedEvents)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getHost().equals(Accounts.getUsername()))
					names.add(getEvent(i));
		} else if (sorter == Sorter.myStarredEvents)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (Accounts.isStarred(getEvent(i).getId()) > -1)
					names.add(getEvent(i));
		} else
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getCategory().equals(sorter.toString()))
					names.add(getEvent(i));
		}
		return names;
	}

	/**
	 * Gets a list of events depending on the sort type
	 * 
	 * @param Enum
	 *            <Sorter> method
	 * @param Event
	 *            to compare to
	 * @return ArrayList<Event> in order of method
	 */
	public static ArrayList<Event> getEvents(Sorter sorter, Event event)
	{
		ArrayList<Event> names = new ArrayList<Event>();
		if (sorter == Sorter.location)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getLocation().equals(event.getLocation()))
					names.add(getEvent(i));
		}
		if (sorter == Sorter.date)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getDate().equals(event.getDate()))
					names.add(getEvent(i));
		}
		if (sorter == Sorter.time)
		{
			for (int i = 0; i < getNumOfEvents(); i++)
				if (getEvent(i).getTime().equals(event.getTime()))
					names.add(getEvent(i));
		}
		return names;
	}

	/**
	 * searchEvents() is for the Search Widget on the home page, for advanced
	 * search, see "adv_searchEvents()"
	 * 
	 * Returns list of events whose name contains the search query
	 * 
	 * @param query
	 *            The string sent by the Searchable activity (the search query)
	 * 
	 * Note: simple event tag = "name location time"
	 */
	public static ArrayList<Event> searchEvents(String query)
	{
		ArrayList<Event> names = new ArrayList<Event>();
		// Splitting query on " "
		String arrquery[] = query.split(" ");
		// This loop adds events to the ArrayList
		for (int i = 0; i < getNumOfEvents(); i++)
		{
			// Checking if event tag contains the full query
			int containsFullQuery = 1;
			for (int k = 0; k < arrquery.length; k++)
			{
				/*
				 *  Ignoring the string after ":" so that
				 *  when users type 6:25, the :25 is ignored and all
				 *  events taking place from 6 to 7 will be displayed
				 *  Note: Have to improve this later, but good enough 
				 *  for the time being 
				 */
				String temparr[] = arrquery[k].split(":");
				if (!(getEvent(i).getSimpleTag().toLowerCase()
						.contains(temparr[0].toLowerCase())))
					containsFullQuery = 0;
			}
			// If event tag contains the full query, I'm adding it to the list
			if (containsFullQuery == 1)
				names.add(getEvent(i));
		}
		return names;
	}
	
	/**
	 * adv_searchEvents() is for the Advanced search feature, 
	 * 
	 * Returns list of events whose name contains the search query
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<Event> adv_searchEvents(String name, String location, String date, Date time)
	{
		Date begtime = new Date();
		Date endtime = new Date();
		
		if(time!=null)
		{
			/*
			 * If time  < 00:30
			 *
			 * begtime = 00:00
			 * endtime = 01:00
			 *
			 */
			
			if(time.getHours() == 0 && time.getMinutes() < 30)
			{
				begtime.setHours(0);
				begtime.setMinutes(0);
				begtime.setSeconds(0);
				
				endtime.setHours(1);
				endtime.setMinutes(0);
				endtime.setSeconds(0);
				
			}
			else

			/*
			 * If time  > 23:30
			 *
			 * begtime = 23:00
			 * endtime = 23:59
			 *
			 */
				
			if(time.getHours() == 23 && time.getMinutes() > 30)
			{
				begtime.setHours(23);
				begtime.setMinutes(0);
				begtime.setSeconds(0);

				endtime.setHours(23);
				endtime.setMinutes(59);
				endtime.setSeconds(0);

			}
			else
			if(time.getMinutes()==30)
			{
				begtime.setHours(time.getHours());
				begtime.setMinutes(0);
				begtime.setSeconds(0);
				
				endtime.setHours(time.getHours()+1);
				endtime.setMinutes(0);
				endtime.setSeconds(0);
				
			}
			else
			if(time.getMinutes()<30)
			{
				begtime.setHours(time.getHours()-1);
				begtime.setMinutes(30+time.getMinutes());
				begtime.setSeconds(0);

				endtime.setHours(time.getHours());
				endtime.setMinutes(30+time.getMinutes());
				endtime.setSeconds(0);

			}	
			else
			if(time.getMinutes()>30)
			{
				begtime.setHours(time.getHours());
				begtime.setMinutes(time.getMinutes()-30);
				begtime.setSeconds(0);

				endtime.setHours(time.getHours()+1);
				endtime.setMinutes(time.getMinutes()-30);
				endtime.setSeconds(0);

			}	
		}
		ArrayList<Event> names = new ArrayList<Event>();
		for(int i = 0; i < getNumOfEvents(); i++)
		{			
			// Only events starting with the search string will get displayed
			
			boolean isNameMatching=true;
			int sizeOfName = name.length();
			for(int k=0 ; k<sizeOfName; k++)
			{
				if(Character.toLowerCase(getEvent(i).getName().charAt(k)) != Character.toLowerCase(name.charAt(k)))
					isNameMatching = false;
			}
			
			if ( isNameMatching == true
			 && (location.equals("Any")|| getEvent(i).getLocation().toLowerCase().equals(location.toLowerCase()))
			 && (date==null || getEvent(i).getDate().toLowerCase().equals(date.toLowerCase()))
			 && (time==null || 
			 		(((getEvent(i).getTime_noformat().getHours()>begtime.getHours()) || 
					((getEvent(i).getTime_noformat().getHours()==begtime.getHours()) && (getEvent(i).getTime_noformat().getMinutes()>=begtime.getMinutes())))
			    &&  ((getEvent(i).getTime_noformat().getHours()<endtime.getHours()) || 
			    	((getEvent(i).getTime_noformat().getHours()==endtime.getHours()) && (getEvent(i).getTime_noformat().getMinutes()<=endtime.getMinutes()))))
			    )
			   )
			{
				names.add(getEvent(i));
			}
		}
		
		return names;
	}

	/**
	 * Gets an event by its name
	 * 
	 * @param Event
	 *            name
	 * @return Event of specified name
	 */
	public static Event getEventByName(String name)
	{
		for (int i = 0; i < getNumOfEvents(); i++)
			if (name.equalsIgnoreCase(getEvent(i).getName()))
				return getEvent(i);
		return null;
	}

	/**
	 * Gets an event by its id
	 * 
	 * @param Event
	 *            id
	 * @return Event of specified id
	 */
	public static Event getEventById(String id)
	{
		for (int i = 0; i < getNumOfEvents(); i++)
			if (id.equalsIgnoreCase(getEvent(i).getId()))
				return getEvent(i);
		return null;
	}

	/**
	 * Gets a list of event names
	 * 
	 * @return String[] of event names
	 */
	public static String[] getEventNames()
	{
		String[] eventNames = new String[getNumOfEvents()];
		for (int i = 0; i < eventNames.length; i++)
			eventNames[i] = getEvent(i).getName();
		return eventNames;
	}

	/**
	 * Preconditions of a quicksort
	 * 
	 * @param ArrayList
	 *            <Event> to sort
	 * @return ArrayList<Event> sorted by popularity
	 */
	private static ArrayList<Event> sort(ArrayList<Event> events)
	{
		if (events == null || events.size() == 0 || events.size() == 1)
		{
			return events;
		}
		return quicksort(0, events.size() - 1, events);
	}

	/**
	 * Quicksort by popularity
	 * 
	 * @param int begin index
	 * @param int end index
	 * @param ArrayList
	 *            <Event> to sort
	 * @return ArrayList<Event> sorted by popularity
	 */
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

	/**
	 * Swaps to indices
	 * 
	 * @param int first index to swap
	 * @param int last index to swap
	 * @param ArrayList
	 *            <Event> list to swap
	 */
	private static void swap(int i, int j, ArrayList<Event> list)
	{
		Event temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}