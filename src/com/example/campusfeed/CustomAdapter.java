package com.example.campusfeed;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Event> {
    private Context c;
    public ArrayList<Event> events;
	public CustomAdapter(Context context, int textViewResourceId, ArrayList<Event> events) {
	        super(context, textViewResourceId, events);
	 
	        this.c = context;
	        this.events=events;
	    }
	// add method
	public void add(ArrayList<Event> list){
		this.clear();
		this.addAll(list);
		this.notifyDataSetChanged();
	}
	 // hold the elements in the layout
	public static class ViewHolder{
public TextView one,two;
	}
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        ViewHolder holder;
	        if (v == null) {
	            LayoutInflater vi =
	                (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.list_layout, null);
	            holder = new ViewHolder();
	            holder.one = (TextView) v.findViewById(R.id.title);
	            holder.two = (TextView) v.findViewById(R.id.date_time);
	            v.setTag(holder);
	        }
	        else
	        holder=(ViewHolder)v.getTag();
	 
	      if(events.get(position)!=null){
	    	  // set the elements
	    	  holder.one.setText(events.get(position).getName());
	    	  holder.two.setText(events.get(position).getDateandTime());
	      }
	        return v;
	    }
	
	
	}


