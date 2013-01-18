package com.example.campusfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoriesAdapter extends ArrayAdapter<String>
{
	private Context c;
	public String[] categories;

	public CategoriesAdapter(Context context, int textViewResourceId,
			String[] categories)
	{
		super(context, textViewResourceId, categories);
		this.categories = categories;
		this.c = context;
	}

	// hold the elements in the layout
	public static class ViewHolder
	{
		public TextView cat;
		public ImageView icon;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		ViewHolder holder;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.extra_sorters_layout, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) v.findViewById(R.id.cat_icon);
			holder.cat = (TextView) v
					.findViewById(R.id.cat_text);
			v.setTag(holder);
		} else
		{
			holder = (ViewHolder) v.getTag();
		}
		holder.cat.setText(categories[position]);
		if(position==0){
			holder.icon.setImageResource(R.drawable.social);
		}
		else
			if(position==1){
				holder.icon.setImageResource(R.drawable.football);
			}
			else
				if(position==2){
					holder.icon.setImageResource(R.drawable.university_icon);
				}
				else
					if(position==3){
						
					}
		return v;
	}
}