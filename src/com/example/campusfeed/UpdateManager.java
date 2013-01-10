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
  
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent,int startId){
		
		super.onStart(intent, startId);

		Log.d("myApp", "Started Service");
		// call async task
		
	
		try{
		if(MainActivity.tabs==null){
				// do nothing.
			this.stopSelf();
		}
		else{
			new Connection(getApplicationContext(),"UPDATE");
			
			this.stopSelf();
		}
		

		}
		catch(Exception e){
			this.stopSelf();
		}
	
	
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		Log.d("myApp", "destroyed");
	}


	 // cant do network on broadcast receiver
	
	
	 
	}

