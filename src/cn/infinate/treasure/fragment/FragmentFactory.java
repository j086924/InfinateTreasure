package cn.infinate.treasure.fragment;

import android.support.v4.app.Fragment;

public class FragmentFactory  {//页面工厂类
	
	public static BaseFragment getFragmentByTag(String tag)
	{
		if (tag.equals(HomepageFragment.FRAGMENT_TAG)) {
			return new HomepageFragment();
			
		}else if(tag.equals(ContactsFragment.FRAGMENT_TAG))
		{
			return new ContactsFragment();
			
		}else if(tag.equals(CircleFragment.FRAGMENT_TAG))
		{
			return new CircleFragment();
		}else if(tag.equals(ProjectFragment.FRAGMENT_TAG))
		{
			return new ProjectFragment();
		}else if(tag.equals(MeFragment.FRAGMENT_TAG))
		{
			return new MeFragment();
		}else
		{
			return null;
		}
		
	}

}
