package cn.infinate.treasure.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.infinate.treasure.R;
import cn.infinate.treasure.utils.Constants;

public class MeFragment extends BaseFragment{
	
	public static String FRAGMENT_TAG=Constants.getClassName(MeFragment.class);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_me, null);
		return view;
	}


}
