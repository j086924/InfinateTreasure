package cn.infinate.treasure;

import cn.infinate.treasure.utils.Constants;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class TreasureApplication extends Application {
	
	@Override
	public void onCreate() {
//		initData();
		
	}

	private void initData() {
		initFirstPreferences();
	}

	private void initFirstPreferences() {
		SharedPreferences mPreferences=getSharedPreferences(Constants.TREA_PREFERENCES, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=mPreferences.edit();
		editor.putBoolean("isfrist", true);
		editor.commit();
		
	}


}
