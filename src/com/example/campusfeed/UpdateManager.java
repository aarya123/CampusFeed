package com.example.campusfeed;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateManager extends Service
{

	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		Log.d("APP", "Started Service");
		// call async task
		try
		{
			if (MainActivity.tabs == null)
			{
				// do nothing.
				this.stopSelf();
			} else
			{
				new Connection(getApplicationContext(), "UPDATE");
				this.stopSelf();
			}
		} catch (Exception e)
		{
			this.stopSelf();
		}
	}

	public void onDestroy()
	{
		super.onDestroy();
		Log.d("APP", "Done Updating");
	}
	// cant do network on broadcast receiver
}
