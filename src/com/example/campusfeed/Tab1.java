package com.example.campusfeed;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1 extends Activity
{
	// full scope vars for use in async task
	public static ListView listView;
    public static ArrayAdapter<String>a;

	   
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);


		CustomAdapter a=new CustomAdapter(getApplicationContext(),R.id.list,EventOrganizer.getEventNames(EventOrganizer.Sorter.popular));;
		
		
	try{
        listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (!listView.getItemAtPosition(position).toString()
						.equals("No Events Today!"))
				{
					Intent eventInfo = new Intent(Tab1.this, EventInfo.class);
					Event e=(Event) listView.getItemAtPosition(position);
		
					Log.d("APP", e.getId());
					//eventInfo.putExtra("eventId",
							//);
				//	Tab1.this.startActivity(eventInfo);
				}
			}
		});
		}
		catch(Exception e){
			
		}
		
	    
	}
	@Override
	public void onResume(){
		super.onResume();
	    // download again and update the list
		//call the async task
		
	
	}
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.refresh:
			new Connection().execute();
			Log.d("APP", "BUTTON PRESSED");
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	
}
