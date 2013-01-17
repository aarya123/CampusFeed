package com.example.campusfeed;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ViewPoster extends Activity
{
	

	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.viewer);
		Intent i=getIntent();
		String url=i.getExtras().getString("url");
		ImageView poster=(ImageView)findViewById(R.id.poster_full_screen);
		UrlImageViewHelper.setUrlDrawable(poster, url);
		
	}
}
