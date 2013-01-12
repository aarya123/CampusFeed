package com.example.campusfeed;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.ipaulpro.afilechooser.utils.FileUtils;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class createEvent extends Activity
{

	public static String date, time;
	public EditText title, desc, locationDetails;
	public Spinner location;
	public File poster = null;
	public File handout = null;
	public static Button setTime,setDate;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // force
																			// into
																			// portrait
		setContentView(R.layout.create_event);
		// String[] locations=new
		// String[]{"Lawson Computer Science Building","Purdue Student Health Center","Cary Quadrangle"};
		location = (Spinner) findViewById(R.id.eventLocation);
		title = (EditText) findViewById(R.id.eventTitle);
		desc = (EditText) findViewById(R.id.eventDescription);
		locationDetails = (EditText) findViewById(R.id.eventLocationDetails);
	    setTime = (Button) findViewById(R.id.setTime);
		setDate = (Button) findViewById(R.id.setDate);
		Button upPoster = (Button) findViewById(R.id.button1);
		Button upHandout = (Button) findViewById(R.id.button2);
		setTime.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				FragmentManager fm = getFragmentManager();
				TimePickerEvent t = new TimePickerEvent();
				t.show(fm, "time_picker");
			}
		});
		setDate.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				FragmentManager fm = getFragmentManager();
				DatePicker d = new DatePicker();
				d.show(fm, "date_picker");
			}
		});
		upPoster.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent target = FileUtils.createGetContentIntent();
				Intent intent = Intent.createChooser(target, "Pick Your image");
				try
				{
					startActivityForResult(intent, 00012);
				} catch (ActivityNotFoundException e)
				{
					// The reason for the existence of aFileChooser
				}
			}
		});
		upHandout.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent target = FileUtils.createGetContentIntent();
				Intent intent = Intent.createChooser(target,
						"Choose your Handout");
				try
				{
					startActivityForResult(intent, 00011);
				} catch (ActivityNotFoundException e)
				{
					// The reason for the existence of aFileChooser
				}
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
		case 00011:
			if (resultCode == RESULT_OK)
			{
				// The URI of the selected file
				final Uri uri = data.getData();
				// Create a File from this Uri
				try
				{
					handout = new File(getRealPathFromURI(uri));
				} catch (Exception e)
				{
					handout = FileUtils.getFile(uri);
				}
			}
			break;
		case 00012:
			if (resultCode == RESULT_OK)
			{
				// The URI of the selected file
				final Uri uri = data.getData();

				// Create a File from this Uri
				try
				{
					poster = new File(getRealPathFromURI(uri));
				} catch (Exception e)
				{
					poster = FileUtils.getFile(uri);
				}
				Toast.makeText(getApplicationContext(), poster.getName(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_create_event, menu);
		// SearchView
		// search=(SearchView)menu.findItem(R.id.menu_search).getActionView();
		// search.setQueryHint("Search an Organization or event name");

		return true;
	}

	private String getRealPathFromURI(Uri contentUri)
	{
		String[] proj = { MediaStore.Images.Media.DATA };
		CursorLoader loader = new CursorLoader(getApplicationContext(),
				contentUri, proj, null, null, null);
		Cursor cursor = loader.loadInBackground();
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.postEvent:
			// post the event by showing a progress bar
			ActionBar bar = getActionBar();
			String s = location.getSelectedItem().toString();
			bar.setTitle(s);
			new PostEvent().execute();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	class PostEvent extends AsyncTask<String, Void, String>
	{
		ProgressDialog p;

		protected void onPreExecute()
		{
			p = ProgressDialog.show(createEvent.this, "Posting Event",
					"Posting Event Handout and Poster", false);
		}

		protected String doInBackground(String... params)
		{
			// post the event
			// TODO Fix this
			String locationString = "84.344342,56.34322" + "|"
					+ location.getSelectedItem().toString();
			String titleString = title.getText().toString();
			String descString = desc.getText().toString();
			String locationDetailsString = locationDetails.getText().toString();
			String response = null;
			HttpPost http = new HttpPost("http://ezevents.6te.net/create.php");
			HttpClient h = new DefaultHttpClient();
			HttpResponse r = null;
			try
			{
				MultipartEntity entity = new MultipartEntity();
				entity.addPart("POSTER", new FileBody(poster));
				entity.addPart("HANDOUT", new FileBody(handout));
				entity.addPart("time", new StringBody(time));
				entity.addPart("date", new StringBody(date));
				entity.addPart("location", new StringBody(locationString));
				entity.addPart("location_details", new StringBody(
						locationDetailsString));
				entity.addPart("title", new StringBody(titleString));
				entity.addPart("user", new StringBody("mayank2333@gmail.com"));
				entity.addPart("description", new StringBody(descString));
				entity.addPart("cat", new StringBody("social"));
				http.setEntity(entity);
				// execute request
				r = h.execute(http);
				response = EntityUtils.toString(r.getEntity());
				// p.setMessage("Posting Event Poster and Handout...");
			} catch (ClientProtocolException e1)
			{
				Log.d("ERROR", e1.getMessage());
				// p.setMessage("Something went wrong!");
			} catch (IOException e1)
			{
				Log.d("ERROR", e1.getMessage());
				// p.setMessage("Something went wrong!");
			}
			// then connect again
			new DownloadDataThread().Download();
			return response;
		}

		public void onPostExecute(String result)
		{
			Intent eventInfo = new Intent(createEvent.this, EventInfo.class);
			eventInfo.putExtra("eventId", result);
			finish();
			startActivity(eventInfo);
			p.setMessage("Finished!");
			p.dismiss();
		}
	}
}
