package com.example.campusfeed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class onPostEventDialog extends DialogFragment
{
	public static Event longClicked;
	public static int x;

	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Options");
		return builder.create();
	}

}
