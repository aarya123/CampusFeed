package com.example.campusfeed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Tab2 extends Activity
{
	// full scope vars for use in async task
	public  ListView listView;
	public static CustomAdapter a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
	
		MenuItem item=(MenuItem)findViewById(R.id.refresh);
		listView = (ListView) findViewById(R.id.list);
        // get array then turn into array list
	
		
        a=new CustomAdapter(getApplicationContext(),R.id.list,EventOrganizer.getEvents(EventOrganizer.Sorter.popular));;
	
	 listView.setAdapter(a);

	
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
			    Event goingTo=a.getItem(position);
			   
				Intent eventInfo = new Intent(Tab2.this, EventInfo.class);
				eventInfo.putExtra("eventId",
				goingTo.getId());
				eventInfo.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				Tab2.this.startActivity(eventInfo);
				
			
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// fire off the dialog box
				 ListOptionsDialog.longClicked=a.getItem(arg2);
				 FragmentManager fm = getFragmentManager();
			        ListOptionsDialog d = new ListOptionsDialog();
			        d.show(fm, "options");
					return false;
			}
			
		});
		
		
		
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
	   
		return getParent().onMenuItemSelected(featureId, item);
		
	}
	

}
