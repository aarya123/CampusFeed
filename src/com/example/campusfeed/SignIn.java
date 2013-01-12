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
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends Activity
{
	EditText userName, passWord;
	TextView error;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		userName = (EditText) this.findViewById(R.id.userNameSignIn);
		passWord = (EditText) this.findViewById(R.id.passWordSignIn);
		error = (TextView) this.findViewById(R.id.errorSignIn);
		((Button) this.findViewById(R.id.submitSignIn))
				.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						if (userName.getText().toString().replace(" ", "")
								.equals("")
								|| passWord.getText().toString()
										.replace(" ", "").equals(""))
						{
							error.setVisibility(View.VISIBLE);
						} else
						{
							new signInConnect().execute(userName.getText()
									.toString(), passWord.getText().toString());
						}
					}
				});
	}

	class signInConnect extends AsyncTask<String, Void, String>
	{
		ProgressDialog p = null;

		protected void onPreExecute()
		{
			p = ProgressDialog.show(SignIn.this, "Signing In",
					"Please Wait...", false);
		}

		protected String doInBackground(String... params)
		{
			String url = "http://ezevents.6te.net/accounts_mobile.php"
					+ "?username=" + params[0] + "&password=" + params[1]
					+ "&action=log_in";
			HttpGet httpGet = new HttpGet(url);
			HttpClient h = new DefaultHttpClient();
			HttpResponse r = null;
			try
			{
				// execute request
				r = h.execute(httpGet);
			} catch (ClientProtocolException e1)
			{
				Log.d("APP", e1.getMessage());
			} catch (IOException e1)
			{
				Log.d("APP", e1.getMessage());
			}
			try
			{
				return EntityUtils.toString(r.getEntity());
			} catch (ParseException e)
			{
				Log.d("ERROR", e.getMessage());
			} catch (IOException e)
			{
				Log.d("ERROR", e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(String result)
		{
			if (result.equals("error") || result == null)
			{
				p.setMessage("Invalid Login");
				p.dismiss();
				error.setVisibility(View.VISIBLE);
			} else
			{
				Accounts.setUsername(userName.getText().toString());
				Accounts.setPassword(passWord.getText().toString());
				String[] array = result.split("\\|");
				Accounts.setEmail(array[0]);
				Accounts.setStarredEvents(array[1].split(",,,"));
				Accounts.setCreatedEvents(array[2].split(",,,"));
				error.setVisibility(View.GONE);
				Tab1.listView.invalidateViews();
				try
				{
					Tab2.listView.invalidateViews();
				} catch (NullPointerException e)
				{
					// Tab 2 hasn't been opened yet
				}
				p.dismiss();
				SignIn.this.finish();
			}
		}
	}
}
