package com.example.campusfeed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class Accounts {
public static String s="none yet";
	public static void starEvent(String uniqueId,String email){
		new AccountOnline().execute(uniqueId,email);
	}
	public static void ReadAccountInfo(){
		
	
	}
	public static void MyEvents(){
		
	}
	public static void starredEvents(){
		
	}
	
}

class AccountOnline extends AsyncTask<String,Void,String>{

	@Override
	protected String doInBackground(String... params) {
		HttpGet httpGet = new HttpGet("http://ezevents.6te.net/accounts_mobile.php?ACCOUNT="+params[1]+"&event_id="+params[0]);
		HttpClient h = new DefaultHttpClient();
		HttpResponse r = null;
		try
		{
			// execute request
			r = h.execute(httpGet);
		} catch (ClientProtocolException e1)
		{
			Log.d("APP", "ERROR");
		} catch (IOException e1)
		{
		Log.d("APP", "ERROR");
		}
		
		try
		{
			// will return the full json array outputted by php
			Accounts.s = EntityUtils.toString(r.getEntity());
		} catch (ParseException e1)
		{
			Log.d("APP", "ERROR");
		} catch (IOException e1)
		{
			Log.d("APP", "ERROR");
		}
		return "DONE";
		// connect to php file and add in starred
	}
	@Override 
	protected void onPostExecute(String result){
		// done
	}
}