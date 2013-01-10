package com.example.campusfeed;

import java.util.Calendar;

import android.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.app.TimePickerDialog;

public class TimePickerEvent extends DialogFragment
                            implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

 
	@Override
	public void onTimeSet(android.widget.TimePicker arg0, int arg1, int arg2) {
		
		String time=arg1+":"+arg2+":"+"00";	
		createEvent.time=time;
	}
}