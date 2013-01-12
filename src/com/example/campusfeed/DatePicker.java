package com.example.campusfeed;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.DatePickerDialog;

public class DatePicker extends DialogFragment implements
		DatePickerDialog.OnDateSetListener
{

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
		String dateMysql = arg1 + "-" + (arg2 + 1) + "-" + (arg3) + " ";
		createEvent.date = dateMysql;
	
		createEvent.setDate.setText("Selected: "+(arg2+1)+"/"+arg3+"/"+arg1);
		
	}
}