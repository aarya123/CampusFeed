package com.example.campusfeed;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountSettings extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_settings);
		// ACCOUNT OPTIONS
		String[] choices=new String[]{"Create an Event","My Events","Following","My Groups","My School","About CampusFeed"};
		ListView accountChoices=(ListView)findViewById(R.id.account_settings_list);
		AccountSettingsAdapter adapter=new AccountSettingsAdapter(this.getApplicationContext(),R.id.account_settings_list,choices);
	accountChoices.setSelector(android.R.color.holo_blue_light);
	accountChoices.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		accountChoices.setAdapter(adapter);
		accountChoices.setOnItemClickListener(new OnItemClickListener(){
/*
 * (non-Javadoc)
 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
 * 
 * we need to set the switcher here. Based on each option
 * 
 */
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
			if(position==0){
				Toast.makeText(getApplicationContext(), position+"", Toast.LENGTH_LONG).show();
			}
			else
				if(position==1){
					// choice
				}
				else
					if(position==2){
						// choice...
					}
					else
						if(position==3){
							
						}
						else
							if(position==4){
								
							}
							else
								if(position==5){
								
								}
				
			}
			
		});
	}
}