package com.example.campusfeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends Activity
{
	EditText userName, passWord, email;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		userName = ((EditText) this.findViewById(R.id.userName));
		passWord = (EditText) this.findViewById(R.id.passWord);
		email = (EditText) this.findViewById(R.id.email);
		((Button) this.findViewById(R.id.submit))
				.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						new createAcc(email.getText().toString(), userName
								.getText().toString(), passWord.getText()
								.toString()).execute();
					}
				});
	}

}

class createAcc extends AsyncTask<String, Void, String>
{
	String email, user, pass;

	public createAcc(String eMail, String userName, String passWord)
	{
		email = eMail;
		user = userName;
		pass = passWord;
	}

	protected String doInBackground(String... params)
	{
		String url = "http://ezevents.6te.net/createaccount.php" + "?email="
				+ email + "&username=" + user + "&password=" + pass;
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
		return null;
	}
}
