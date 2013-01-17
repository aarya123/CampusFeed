package com.example.campusfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.DatePickerDialog;

public class DatePicker extends DialogFragment implements
		DatePickerDialog.OnDateSetListener
{
	// Checking if AdvancedSearch is calling this function
	
	public static boolean fromAdv = false;

	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// Use the current date as the default date in the picker
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(android.widget.DatePicker arg0, int arg1, int arg2,
			int arg3)
	{
		if(fromAdv == true)
		{
			String date = (arg2+1)+"/"+arg3+"/"+arg1;
			SimpleDateFormat timeFormat1=new SimpleDateFormat("M/d/yyyy");
	
			Date d=null;
			try 
			{
				d=timeFormat1.parse(date);
			} 
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			
			SimpleDateFormat timeFormat2 =new SimpleDateFormat("M/d/yyyy");
			AdvacedSearch.DateChooser.setText(timeFormat2.format(d));
			AdvacedSearch.setDate(timeFormat2.format(d));
		}
		else
		{
			String dateMysql = arg1 + "-" + (arg2 + 1) + "-" + (arg3) + " ";
			createEvent.date = dateMysql;

			createEvent.setDate.setText((arg2+1)+"/"+arg3+"/"+arg1);
		}
		
	}
}