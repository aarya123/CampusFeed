package com.example.campusfeed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ListOptionsDialog extends DialogFragment
{
	public static Event longClicked;
	public static int x;

	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Options").setItems(R.array.listdialogoptions,
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						//TODO Star Event
						Accounts.starEvent(longClicked.getId(),
								"mayank2333@gmail.com");
						switch (which)
						{
						case 0:

							Intent eventInfo = new Intent(getActivity(),
									EventInfo.class);
							eventInfo.putExtra("eventId", longClicked.getId());
							eventInfo
									.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							startActivity(eventInfo);

							break;
						case 1:
							// star event
							// Accounts.starEvent(longClicked.getId(),
							// "mayank2333@gmail.com");
							break;
						case 2:
							// view creator
							Toast.makeText(getActivity(), Accounts.s,
									Toast.LENGTH_LONG).show();

							break;
						}
					}
				});
		return builder.create();
	}
}
