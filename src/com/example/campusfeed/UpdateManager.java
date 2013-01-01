package com.example.campusfeed;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class UpdateManager extends Service  {
  
    
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	public void onStart(Intent intent,int startId){
		
		super.onStart(intent, startId);

		Log.d("myApp", "Started Service");
		// call async task
		try{
		new Connection().execute("UPDATE");
		this.stopSelf();
		}
		catch(Exception e){
			this.stopSelf();
		}
	
	
	}
	
	public void onDestroy(){
		super.onDestroy();
		
		Log.d("myApp", "destroyed");
	}


	 // cant do network on broadcast receiver
	
	
	 
	}

