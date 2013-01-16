package com.example.campusfeed;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class PromotionsAdapter extends PagerAdapter
{
	Bitmap[] promotions = null;
	int w, h;

	public PromotionsAdapter(Bitmap[] promotions, int w, int h)
	{
		this.promotions = promotions;
		this.w = w;
		this.h = h;
	}

	public int getCount()
	{
		return 5;
	}

	public Object instantiateItem(View collection, int position)
	{
		LayoutInflater inflater = (LayoutInflater) collection.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.promo_layout, null);
		((ViewPager) collection).addView(view, 0);
		ImageView i = (ImageView) view.findViewById(R.id.imageView1);
		try{
		i.setImageBitmap(Bitmap.createScaledBitmap(this.promotions[position],
				w, h, true));
		}
		catch(NullPointerException e)
		{
			Log.d("ERROR",e.getMessage());
		}
		return view;
	}

	public void destroyItem(View arg0, int arg1, Object arg2)
	{
		((ViewPager) arg0).removeView((View) arg2);
	}

	public boolean isViewFromObject(View arg0, Object arg1)
	{
		return arg0 == ((View) arg1);
	}

	public Parcelable saveState()
	{
		return null;
	}
}
