package com.example.campusfeed;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.campusfeed.Tab1.UpdateInterest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ViewSwitcher;

public class ExtraSorters extends Activity
{
	ListView choiceListView, eventListView;
	ViewSwitcher switcher;
	CustomAdapter a, b;
	CategoriesAdapter adapter;
	int clickCount;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_sorters);
		clickCount = 0;
		String[] events = new String[] { "Social", "Sales", "Organizational",
				"Sports", "Academic" };
		choiceListView = (ListView) findViewById(R.id.choiceList);
		CategoriesAdapter a=new CategoriesAdapter(getApplicationContext(),R.id.choiceList,events);
		choiceListView.setAdapter(a);
		switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		choiceListView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				clickCount = 1;
				eventListView = (ListView) findViewById(R.id.extraList);
				b = null;
				if (position == 0)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.social));
				else if (position == 1)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.sales));
				else if (position == 2)
					b = new CustomAdapter(
							getApplicationContext(),
							R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.organizations));
				else if (position == 3)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.sports));
				else if (position == 4)
					b = new CustomAdapter(getApplicationContext(), R.id.list,
							EventOrganizer
									.getEvents(EventOrganizer.Sorter.academic));
				eventListView.setAdapter(b);
				switcher.showNext();
				eventListView.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id)
					{
						b.getItem(position).setInterest();
						Event goingTo = (Event) b.getItem(position);
						
						Log.d("APP", goingTo.getId());
						Intent eventInfo = new Intent(ExtraSorters.this,
								EventInfo.class);
						eventInfo.putExtra("eventId", goingTo.getId());
						eventInfo.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						ExtraSorters.this.startActivity(eventInfo);
						new UpdateInterest().execute(goingTo.getId());
					}
				});
			}
		});
	}

	public void onBackPressed()
	{
		if (clickCount == 1)
		{
			switcher.showPrevious();
			clickCount = 0;
		} else
			super.onBackPressed();
	}
	public class UpdateInterest extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpGet httpGet = new HttpGet(
					"http://ezevents.6te.net/interest.php?eventid="+params[0]);
			HttpClient h = new DefaultHttpClient();
			HttpResponse r = null;
			try
			{
				// execute request
				r = h.execute(httpGet);
			} catch (Exception e1){
				return "error";
			}
		
			return "done";
		}
		
		@Override
		protected void onPostExecute(String result){
			
		}
	}
}