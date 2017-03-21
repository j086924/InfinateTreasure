package cn.infinate.treasure;

import cn.infinate.treasure.utils.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class BaseFragmentActivity extends FragmentActivity {
	public static String BASE_TAG=Constants.getClassName(BaseFragmentActivity.class);
	
	protected Activity mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		 Log.d(BASE_TAG, "onStart");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(BASE_TAG, "onResume");
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(BASE_TAG, "onStop");
	}
	
	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Log.d(BASE_TAG, "onDetachedFromWindow");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(BASE_TAG, "onDestroy");
	}
	
	
	@Override
	public void finish() {
		super.finish();
		Log.d(BASE_TAG, "finish");
	}
	
	


}
