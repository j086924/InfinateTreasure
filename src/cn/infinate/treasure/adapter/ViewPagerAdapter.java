package cn.infinate.treasure.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {
	
	private List<View> mViews=null;
	
	public ViewPagerAdapter(List<View> views) {
		this.mViews=views;
	}

	@Override
	public int getCount() {
		
		if (null!=mViews) {
			return mViews.size();
		}
		
		return 0 ;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0==arg1);
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(mViews.get(position));
	}
	
	
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(mViews.get(position), 0);
		return mViews.get(position);
	}

}
