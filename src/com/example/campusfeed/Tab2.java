package com.example.campusfeed;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.campusfeed.Tab1.UpdateInterest;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Tab2 extends Activity
{
	// full scope vars for use in async task
	public static ListView listView;
	public static CustomAdapter a;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		listView = (ListView) findViewById(R.id.list);
		// get array then turn into array list
		a = new CustomAdapter(getApplicationContext(), R.id.list,
				EventOrganizer.getEvents(EventOrganizer.Sorter.popular));
		listView.setAdapter(a);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				a.getItem(position).setInterest();
				Event goingTo = a.getItem(position);
				Intent eventInfo = new Intent(Tab2.this, EventInfo.class);
				eventInfo.putExtra("eventId", goingTo.getId());
				new UpdateInterest().execute(""+position);
				eventInfo.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(eventInfo);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{
				// fire off the dialog box
				ListOptionsDialog.longClicked = a.getItem(arg2);
				FragmentManager fm = getFragmentManager();
				ListOptionsDialog.position_clicked=arg2;
				ListOptionsDialog d = new ListOptionsDialog();
				d.show(fm, "options");
				return false;
			}
		});
	}
	public class UpdateInterest extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpGet httpGet = new HttpGet(
					"http://ezevents.6te.net/interest.php?eventid="+a.getItem(Integer.parseInt(params[0])).getId());
			HttpClient h = new DefaultHttpClient();
			HttpResponse r = null;
			try
			{
				// execute request
				r = h.execute(httpGet);
			} catch (ClientProtocolException e1)
			{
				Log.d("APP", "ERROR1");
			} catch (IOException e1)
			{
				Log.d("APP", "ERROR2");
			}
			return "done";
		}
		
		@Override
		protected void onPostExecute(String result){
			
		}
	}
}
