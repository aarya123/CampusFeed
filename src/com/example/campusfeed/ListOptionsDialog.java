package com.example.campusfeed;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
							if (Accounts.isSignedIn())
							{
								int index;
								if ((index = Accounts.isStarred(longClicked
										.getId())) == -1)
								{
									Accounts.starEvent(longClicked.getId());
									new AccountOnline().execute(
											Accounts.getUsername(),
											longClicked.getId(), "star");
								} else
								{
									Accounts.unStar(index);
									new AccountOnline().execute(
											Accounts.getUsername(),
											longClicked.getId(), "unstar");
								}
							}
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

	class AccountOnline extends AsyncTask<String, Void, String>
	{
		protected String doInBackground(String... params)
		{
			if (params[2].equals("star"))
			{
				HttpGet httpGet = new HttpGet(
						"http://ezevents.6te.net/accounts_mobile.php?username="
								+ params[0] + "&starring_event_id=" + params[1]
								+ "&action=star_event");
				HttpClient h = new DefaultHttpClient();
				HttpResponse r = null;
				try
				{
					// execute request
					r = h.execute(httpGet);
				} catch (ClientProtocolException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				try
				{
					// will return the full json array outputted by php
					Accounts.s = EntityUtils.toString(r.getEntity());
				} catch (ParseException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				// Tab1.listView.invalidateViews();
				// Tab2.listView.invalidateViews();
				return "DONE";
				// connect to php file and add in starred
			}
			else{
				HttpGet httpGet = new HttpGet(
						"http://ezevents.6te.net/accounts_mobile.php?username="
								+ params[0] + "&unstarring_event_id=" + params[1]
								+ "&action=unstar_event");
				HttpClient h = new DefaultHttpClient();
				HttpResponse r = null;
				try
				{
					// execute request
					r = h.execute(httpGet);
				} catch (ClientProtocolException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				try
				{
					// will return the full json array outputted by php
					Accounts.s = EntityUtils.toString(r.getEntity());
				} catch (ParseException e1)
				{
					Log.d("ERROR", e1.getMessage());
				} catch (IOException e1)
				{
					Log.d("ERROR", e1.getMessage());
				}
				// Tab1.listView.invalidateViews();
				// Tab2.listView.invalidateViews();
				return "DONE";
				// connect to php file and add in starred
			}
		}
		
	}
}
