package com.example.campusfeed;

import java.io.File;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.ipaulpro.afilechooser.utils.FileUtils;
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

	static String date, time;
	EditText title, desc, locationDetails;
	Spinner location;
	File poster = null;
	Button upPoster, upHandout;
	File handout = null;
	static Button setTime, setDate;
	HashMap<String, String> locations;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // force
																			// into
																			// portrait
		locations = new HashMap<String, String>();
		locations.put("Lawson Computer Science Building",
				"40.427767,-86.916941");
		locations.put("Purdue Student Health Center", "40.430213,-86.916648");
		locations.put("Purdue Memorial Union", "40.424999,-86.911539");
		locations.put("Cary Quadrangle", "40.432037,-86.917949");
		locations.put("Electrical Engineering Building", "40.428588,-86.91193");
		setContentView(R.layout.create_event);
		location = (Spinner) findViewById(R.id.eventLocation);
		title = (EditText) findViewById(R.id.eventTitle);
		desc = (EditText) findViewById(R.id.eventDescription);
		locationDetails = (EditText) findViewById(R.id.eventLocationDetails);
		setTime = (Button) findViewById(R.id.setTime);
		setDate = (Button) findViewById(R.id.setDate);
		upPoster = (Button) findViewById(R.id.button1);
		upHandout = (Button) findViewById(R.id.button2);
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
				upHandout.setText(handout.getName());
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
				upPoster.setText(poster.getName());
			}
		}
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_create_event, menu);
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
			String locationString = locations.get(location.getSelectedItem());
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
				entity.addPart("user", new StringBody(Accounts.getUsername()));
				entity.addPart("description", new StringBody(descString));
				entity.addPart("cat", new StringBody("social"));
				http.setEntity(entity);
				// execute request
				r = h.execute(http);
				response = EntityUtils.toString(r.getEntity());
				// p.setMessage("Posting Event Poster and Handout...");
			} catch (Exception e1)
			{
				Log.d("ERROR", e1.getMessage());
				// p.setMessage("Something went wrong!");
				return e1.getMessage();
			}
			// then connect again
			new DownloadDataThread().Download();
			return response;
		}

		public void onPostExecute(String result)
		{
			p.setMessage("Finished!");
			p.dismiss();
			Intent eventInfo = new Intent(createEvent.this, EventInfo.class);
			eventInfo.putExtra("eventId", result);
			finish();
			// startActivity(eventInfo);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}
	}
}
