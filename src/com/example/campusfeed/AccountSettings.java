package com.example.campusfeed;

import com.example.campusfeed.EventOrganizer.Sorter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ViewSwitcher;

public class AccountSettings extends Activity
{
	ViewSwitcher switcher;
	ListView switchedList;
	CustomAdapter b;
	int clickCount = 0;
	String[] choices;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_settings);
		switcher = (ViewSwitcher) findViewById(R.id.viewSwitcherSettings);
		switchedList = (ListView) findViewById(R.id.settingsListView);
		// ACCOUNT OPTIONS
		if (Accounts.isSignedIn())
		{
			choices = new String[] { "Create an Event", "My Created Events",
					"My Starred Events", "Log Out"/*, "My Groups", "My School", "About CampusFeed" */};
		} else
		{
			choices = new String[] { "Sign In", "Create an Account"/*, "My Groups", "My School", "About CampusFeed" */};
		}
		ListView accountChoices = (ListView) findViewById(R.id.account_settings_list);
		AccountSettingsAdapter adapter = new AccountSettingsAdapter(
				this.getApplicationContext(), R.id.account_settings_list,
				choices);
		accountChoices.setSelector(android.R.color.holo_blue_light);
		accountChoices.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		accountChoices.setAdapter(adapter);
		accountChoices.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{
				if (choices[position].equals("Create an Event"))
				{
					Intent createEvent = new Intent(AccountSettings.this,
							createEvent.class);
					startActivity(createEvent);
				} else if (choices[position].equals("My Created Events"))
				{
					b = new CustomAdapter(AccountSettings.this, R.id.list,
							EventOrganizer.getEvents(Sorter.myCreatedEvents));
					switchedList.setSelector(android.R.color.holo_blue_light);
					switchedList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
					switchedList.setAdapter(b);
					switchedList
							.setOnItemClickListener(new OnItemClickListener()
							{
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int position, long arg3)
								{
									Event goingTo = (Event) b.getItem(position);
									Log.d("APP", goingTo.getId());
									Intent eventInfo = new Intent(
											AccountSettings.this,
											EventInfo.class);
									eventInfo.putExtra("eventId",
											goingTo.getId());
									AccountSettings.this
											.startActivity(eventInfo);
								}
							});
					clickCount = 1;
					switcher.showNext();
				} else if (choices[position].equals("My Starred Events"))
				{
					b = new CustomAdapter(AccountSettings.this, R.id.list,
							EventOrganizer.getEvents(Sorter.myStarredEvents));
					switchedList.setSelector(android.R.color.holo_blue_light);
					switchedList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
					switchedList.setAdapter(b);
					switchedList
							.setOnItemClickListener(new OnItemClickListener()
							{
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int position, long arg3)
								{
									Event goingTo = (Event) b.getItem(position);
									Log.d("APP", goingTo.getId());
									Intent eventInfo = new Intent(
											AccountSettings.this,
											EventInfo.class);
									eventInfo.putExtra("eventId",
											goingTo.getId());
									AccountSettings.this
											.startActivity(eventInfo);
								}
							});
					clickCount = 1;
					switcher.showNext();
				} else if (choices[position].equals("Log Out"))
				{
					Accounts.signOut();
					AccountSettings.this.finish();
				} else if (choices[position].equals("Sign In"))
				{
					Intent signIn = new Intent(AccountSettings.this,
							SignIn.class);
					startActivity(signIn);
					AccountSettings.this.finish();
				} else if (choices[position].equals("Create an Account"))
				{
					Intent createAcc = new Intent(AccountSettings.this,
							CreateAccount.class);
					startActivity(createAcc);
					AccountSettings.this.finish();
				}
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
}