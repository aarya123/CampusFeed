package com.example.campusfeed;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class Tab1 extends Activity
{
// full scope vars for use in async task
	ListView listView;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// todo add the sort function here to display the events //
		// sample array
		String[] events=new String[]{"Event 1","Event 2","Event 3"};
		
		setContentView(R.layout.tabs);
		  listView=  (ListView) findViewById(R.id.list);
		ArrayAdapter<String> a=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_layout,R.id.text,events);
	     
	      
	    
	      listView.setAdapter(a);
	      
	      	
	      listView.setOnItemClickListener(new OnItemClickListener(){

	    		
	    		public void onItemClick(AdapterView<?> parent, View view,
	    			    int position, long id) {
	    			
	    			
	    			
	    		}
	      	
	      });
		
	}
}
