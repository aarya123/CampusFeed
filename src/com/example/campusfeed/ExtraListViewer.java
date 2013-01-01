package com.example.campusfeed;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

public class ExtraListViewer extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_list_viewer);
		setupActionBar();
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_extra_list_viewer, menu);
		return true;
	}
	/**
	 * Sets up Action Bar
	 */
	public void setupActionBar()
	{
		ActionBar bar = getActionBar();
		ColorDrawable actionBarColor = new ColorDrawable();
		actionBarColor.setColor(Color.rgb(49, 132, 189));
		bar.setTitle("CampusFeed");
		bar.setBackgroundDrawable(actionBarColor);
	}

}
