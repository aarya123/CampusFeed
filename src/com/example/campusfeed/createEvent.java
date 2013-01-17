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
	Spinner location, categories;
	File poster = null;
	Button upPoster, upHandout;
	File handout = null;
	public ProgressDialog p;
	static Button setTime, setDate;
	HashMap<String, String> locationsHashMap;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // force
																			// into
																			// portrait
		locationsHashMap = new HashMap<String, String>();
		locationsHashMap.put("Aerospace Science Laboratory",
				"40.416303,-86.928527");
		locationsHashMap.put("Agricultural and Biological Engineering ",
				"40.421632,-86.91655");
		locationsHashMap.put("Animal Disease Diagnostic Laboratory",
				"40.418197,-86.916002");
		locationsHashMap.put("Animal Sciences Teaching Laboratory",
				"40.423212,-86.919858");
		locationsHashMap.put("Beering Hall of Liberal Arts and Education",
				"40.425442,-86.916237");
		locationsHashMap.put("Biochemistry Building", "40.422783,-86.916432");
		locationsHashMap.put("Bowen Laboratory for Civil Engineering",
				"40.410294,-86.917133");
		locationsHashMap.put("Brown Laboratory of Chemistry",
				"40.42652,-86.91193");
		locationsHashMap.put("Chaffee Hall", "40.4161,-86.942673");
		locationsHashMap.put("Class of 1950 Lecture Hall",
				"40.426322,-86.915044");
		locationsHashMap.put("Combustion Research Laboratory",
				"40.415485,-86.941245");
		locationsHashMap.put("Composites Laboratory", "40.416113,-86.92888");
		locationsHashMap.put("Doyle Laboratory", "40.418701,-86.915493");
		locationsHashMap.put("Electrical Engineering Building",
				"40.428588,-86.91193");
		locationsHashMap.put("Entomology Environmental Laboratory",
				"40.422818,-86.914788");
		locationsHashMap.put("Equine Health Sciences Annex",
				"40.417552,-86.914162");
		locationsHashMap.put("Flight Operations Building",
				"40.416491,-86.930294");
		locationsHashMap.put("Forestry Building", "40.422761,-86.914123");
		locationsHashMap.put("Forestry Products Building",
				"40.423065,-86.915101");
		locationsHashMap.put("Forney Hall of Chemical Engineering",
				"40.429587,-86.914317");
		locationsHashMap.put("Fowler Memorial House", "40.424886,-86.922265");
		locationsHashMap.put("Gas Dynamics Research Laboratory",
				"40.416207,-86.94148");
		locationsHashMap.put("Grissom Hall", "40.426371,-86.910795");
		locationsHashMap.put("Haas Hall", "40.426796,-86.916315");
		locationsHashMap.put("Hampton Hall of Civil Engineering",
				"40.430254,-86.914749");
		locationsHashMap.put("Hansen Life Sciences Research Building",
				"40.4222,-86.916941");
		locationsHashMap.put("Heavilon Hall", "40.426239,-86.911813");
		locationsHashMap.put("Heine Pharmacy Building", "40.429651,-86.916002");
		locationsHashMap.put("Herrick Laboratories", "40.422944,-86.921012");
		locationsHashMap.put("Hicks Undergraduate Library",
				"40.424512,-86.912674");
		locationsHashMap.put("High Pressure Research Laboratory",
				"40.414873,-86.939132");
		locationsHashMap.put("Hockmeyer Hall of Structural Biology",
				"40.421101,-86.921146");
		locationsHashMap.put("Horticulture Building", "40.421776,-86.914475");
		locationsHashMap.put("Horticulture Greenhouse", "40.420869,-86.914749");
		locationsHashMap.put("Johnson Hall of Nursing", "40.429359,-86.915493");
		locationsHashMap.put("Knoy Hall of Technology", "40.427772,-86.911147");
		locationsHashMap.put("Krannert Building", "40.423632,-86.91099");
		locationsHashMap.put("Lawson Computer Science Building",
				"40.427767,-86.916941");
		locationsHashMap.put("Life Science Animal Building",
				"40.422539,-86.917724");
		locationsHashMap.put("Lilly Hall of Life Sciences",
				"40.423342,-86.918037");
		locationsHashMap.put("Lynn Hall of Veterinary Medicine",
				"40.419364,-86.914984");
		locationsHashMap.put(
				"Martin C. Jischke Hall of Biomedical Engineering",
				"40.422153,-86.921169");
		locationsHashMap.put("Materials and Electrical Engineering",
				"40.429404,-86.912713");
		locationsHashMap.put("Mathematial Sciences Building",
				"40.426143,-86.915688");
		locationsHashMap.put("Matthews Hall", "40.424807,-86.916134");
		locationsHashMap.put("Mechanical Engineering Building",
				"40.428281,-86.912856");
		locationsHashMap.put("Neil Armstrong Hall of Engineering",
				"40.431134,-86.914457");
		locationsHashMap.put("Nelson Hall of Food Science",
				"40.421607,-86.91561");
		locationsHashMap.put("Niswonger Aviation Technology Building",
				"40.416478,-86.929154");
		locationsHashMap.put("Nuclear Engineering Building",
				"40.427136,-86.911147");
		locationsHashMap.put("Peirce Hall", "40.426644,-86.91505");
		locationsHashMap.put("Pfendler Hall of Agriculture",
				"40.42359,-86.915375");
		locationsHashMap.put("Physics Building", "40.430091,-86.913442");
		locationsHashMap.put("Potter Engineering Center", "40.427487,-86.9124");
		locationsHashMap.put("Poultry Science Building", "40.42361,-86.919897");
		locationsHashMap.put("Propulsion Research Laboratory",
				"40.415789,-86.940698");
		locationsHashMap.put("Psychological Sciences Building",
				"40.427077,-86.914905");
		locationsHashMap.put("Rawls Hall", "40.423762,-86.909894");
		locationsHashMap.put("Recitation Building", "40.425812,-86.915219");
		locationsHashMap.put("Smith Hall", "40.42359,-86.916902");
		locationsHashMap.put("National Soil Erosion Laboratory",
				"40.421354,-86.919612");
		locationsHashMap.put("Stanley Coulter Hall", "40.426385,-86.914318");
		locationsHashMap.put("Stone Hall", "40.42454,-86.915219");
		locationsHashMap.put("University Hall", "40.42516,-86.915159");
		locationsHashMap.put("Veterinary Pathobiology Research Building",
				"40.419792,-86.916158");
		locationsHashMap.put("Wetherill Laboratory of Chemistry",
				"40.42646,-86.913064");
		locationsHashMap.put("Whistler Hall of Agricultural Research",
				"40.422957,-86.915532");
		locationsHashMap.put("Armory", "40.427987,-86.916237");
		locationsHashMap.put("Hovde Hall of Administration",
				"40.428216,-86.914397");
		locationsHashMap
				.put("Purdue Police Department", "40.422341,-86.922265");
		locationsHashMap.put("Purdue University Student Health Center",
				"40.430213,-86.916648");
		locationsHashMap.put("Schleman Hall of Student Services",
				"40.428826,-86.914905");
		locationsHashMap.put("Terminal Building", "40.416287,-86.930954");
		locationsHashMap.put("Young Hall", "40.423216,-86.910273");
		locationsHashMap.put("Cary Quadrangle", "40.432037,-86.917949");
		locationsHashMap.put("Earhart Residence Hall", "40.425752,-86.924927");
		locationsHashMap.put("First Street Towers", "40.42488,-86.924566");
		locationsHashMap.put("Ford Dining Court", "40.432214,-86.919681");
		locationsHashMap.put("Harrison Residence Hall", "40.425007,-86.926805");
		locationsHashMap.put("Hawkins Residence Hall", "40.422979,-86.911726");
		locationsHashMap.put("Hillenbrand Residence Hall",
				"40.426597,-86.926805");
		locationsHashMap.put("Hilltop Apartments", "40.434336,-86.921717");
		locationsHashMap
				.put("McCutcheon Residence Hall", "40.42504,-86.928058");
		locationsHashMap.put("Meredith Residence Hall", "40.426347,-86.923361");
		locationsHashMap.put("Owen Residence Hall", "40.432321,-86.920699");
		locationsHashMap.put("Purdue Village Administration Building",
				"40.42016,-86.923987");
		locationsHashMap.put("Shreve Residence Hall", "40.426866,-86.924927");
		locationsHashMap.put("Tarkington Residence Hall",
				"40.430571,-86.920699");
		locationsHashMap.put("Wiley Dining Court", "40.428509,-86.920846");
		locationsHashMap.put("Wiley Residence Hall", "40.429458,-86.920699");
		locationsHashMap.put("Windsor Halls", "40.426303,-86.921157");
		locationsHashMap.put("Birck Boilermaker Golf Complex",
				"40.439358,-86.926178");
		locationsHashMap.put("Black Cultural Center", "40.42752,-86.919603");
		locationsHashMap.put("Boilermaker Aquatic Center",
				"40.428178,-86.923439");
		locationsHashMap.put("Dauch Alumni Center", "40.421878,-86.910834");
		locationsHashMap.put("Elliott Hall of Music", "40.42775,-86.915067");
		locationsHashMap.put("Lambert Fieldhouse", "40.432114,-86.915923");
		locationsHashMap.put("Latino Cultural Center", "40.430482,-86.918859");
		locationsHashMap.put("Mackey Arena", "40.432891,-86.91659");
		locationsHashMap.put("Memorial Mall", "40.425074,-86.914357");
		locationsHashMap.put("Mollenkopf Athletic Center",
				"40.435638,-86.916863");
		locationsHashMap.put("Bell Tower", "40.427273,-86.914064");
		locationsHashMap.put("Engineering Fountain", "40.428638,-86.913791");
		locationsHashMap.put("Purdue Memorial Union", "40.424999,-86.911539");
		locationsHashMap.put("Rankin Track and Field", "40.429752,-86.922813");
		locationsHashMap
				.put("Recreational Sports Center", "40.428472,-86.9225");
		locationsHashMap.put("Ross-Ade Stadium", "40.434470,-86.918507");
		locationsHashMap.put("Slayter Center of Performing Arts",
				"40.431914,-86.922598");
		locationsHashMap.put("Stewart Center", "40.425032,-86.912791");
		locationsHashMap.put("Varsity Soccer Complex", "40.437655,-86.938937");
		locationsHashMap.put("Visitor Information Center",
				"40.429713,-86.911979");

		setContentView(R.layout.create_event);
		location = (Spinner) findViewById(R.id.eventLocation);
		title = (EditText) findViewById(R.id.eventTitle);
		desc = (EditText) findViewById(R.id.eventDescription);
		locationDetails = (EditText) findViewById(R.id.eventLocationDetails);
		categories = (Spinner) findViewById(R.id.eventCategory);
		setTime = (Button) findViewById(R.id.setTime);
		setDate = (Button) findViewById(R.id.setDate);
		upPoster = (Button) findViewById(R.id.button1);
		upHandout = (Button) findViewById(R.id.button2);
		setTime.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				DatePicker.fromAdv=false;
				FragmentManager fm = getFragmentManager();
				TimePickerEvent t = new TimePickerEvent();
				t.show(fm, "time_picker");
			}
		});
		setDate.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				DatePicker.fromAdv=false;
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

			
			String s = location.getSelectedItem().toString();

			new PostEvent().execute();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	class PostEvent extends AsyncTask<String, Void, String>
	{
		

		protected void onPreExecute()
		{
			p = ProgressDialog.show(createEvent.this, "Please Wait",
					"Posting The Event!", false);
		}

		protected String doInBackground(String... params)
		{
			// post the event
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
				entity.addPart(
						"latlng",
						new StringBody(locationsHashMap.get(location
								.getSelectedItem())));
				entity.addPart("location", new StringBody(location
						.getSelectedItem().toString()));
				entity.addPart("location_details", new StringBody(
						locationDetails.getText().toString()));
				entity.addPart("title", new StringBody(title.getText()
						.toString()));
				entity.addPart("user", new StringBody(Accounts.getUsername()));
				entity.addPart("description", new StringBody(desc.getText()
						.toString()));
				entity.addPart("cat", new StringBody(categories
						.getSelectedItem().toString().toLowerCase()));
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
			p.setMessage("");
	
			
		
			new RefreshForPost().execute(result);
	
	

			
		}
	}
	
 class RefreshForPost extends AsyncTask<String,Void,String>{

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	new DownloadDataThread().Download();
	return params[0];
	
	}
	@Override 
	protected void onPostExecute(String result){
		Intent eventInfo = new Intent(createEvent.this, EventInfo.class);
		
		
		eventInfo.putExtra("eventId", result);
		p.dismiss();
	    startActivity(eventInfo);
		finish();
	}
	}
}
