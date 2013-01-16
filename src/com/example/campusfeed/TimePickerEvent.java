package com.example.campusfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.app.TimePickerDialog;

public class TimePickerEvent extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener
{

	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(android.widget.TimePicker arg0, int arg1, int arg2)
	{
		String timePhp = arg1 + ":" + arg2 + ":" + "00";
		SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm:ss");
		Date d=null;
		try {
			d=timeFormat.parse(timePhp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat formatView=new SimpleDateFormat("h:mm a");
		
		createEvent.setTime.setText(formatView.format(d));
		createEvent.time = timePhp;
	}
}