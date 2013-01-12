package com.example.campusfeed;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccount extends Activity
{
	EditText userName, passWord, email;
	static TextView error;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		userName = (EditText) this.findViewById(R.id.userName);
		passWord = (EditText) this.findViewById(R.id.passWord);
		email = (EditText) this.findViewById(R.id.email);
		error = (TextView) this.findViewById(R.id.error);
		((Button) this.findViewById(R.id.submit))
				.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						if (email.getText().toString().contains(" ")
								|| !email.getText().toString().contains("@")
								|| userName.getText().toString()
										.replace(" ", "").contains(" ")
								|| passWord.getText().toString()
										.replace(" ", "").contains(" "))
						{
							error.setVisibility(View.VISIBLE);
						} else
						{
							new createAcc().execute(email.getText().toString(),
									userName.getText().toString(), passWord
											.getText().toString());
							error.setVisibility(View.GONE);
						}
					}
				});
	}
}

class createAcc extends AsyncTask<String, Void, String>
{

	protected String doInBackground(String... params)
	{
		String url = "http://ezevents.6te.net/createaccount.php" + "?email="
				+ params[0] + "&username=" + params[1] + "&password="
				+ params[2];
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
		if (r.getEntity().equals("fail"))
			CreateAccount.error.setVisibility(View.VISIBLE);
		else
			CreateAccount.error.setVisibility(View.GONE);
		return null;
	}
}
