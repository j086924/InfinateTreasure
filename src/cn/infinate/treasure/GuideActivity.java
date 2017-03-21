package cn.infinate.treasure;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import cn.infinate.treasure.adapter.ViewPagerAdapter;
import cn.infinate.treasure.utils.Constants;

public class GuideActivity extends Activity implements OnClickListener,OnPageChangeListener{
	
	private ViewPager mViewPager;
	private ViewPagerAdapter mViewPagerAdapter;
	private List<View> mViews;
	private Button mLoginButton;
	private Button mRegisterButton;
	private ImageView[] mPoints;
	private int mCurrentIndex;
	private Boolean mIsfirst=true;
	
	private SharedPreferences mPreferences;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide_activity);
		mPreferences=getSharedPreferences(Constants.TREA_PREFERENCES, Context.MODE_PRIVATE);
		mIsfirst=mPreferences.getBoolean("isfrist", true);
		//添加判断是否已经登录过，登陆过，直接进入主界面
	    //enterHomePageActivity();
		
		if (!mIsfirst) {
			enterWelcomeActivity();
			
		}else {
			initViews();
			initData();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private void initViews() {
		
		mViews=new ArrayList<View>();
		mViewPager=(ViewPager) findViewById(R.id.main_viewpager);
		mViewPagerAdapter=new ViewPagerAdapter(mViews);
		mLoginButton=(Button) findViewById(R.id.enter_login);
		mRegisterButton=(Button) findViewById(R.id.enter_register);
		mLoginButton.setOnClickListener(this);
		mRegisterButton.setOnClickListener(this);
		
	}
	

	private void initData() {
		
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		
		for (int i = 0; i < Constants.GUIDE_PICS.length; i++) {
			
			ImageView img=new ImageView(this);
			img.setLayoutParams(layoutParams);
			img.setScaleType(ScaleType.FIT_XY);
			img.setImageResource(Constants.GUIDE_PICS[i]);
			mViews.add(img);
			
		}//for
		
		mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.setOnPageChangeListener(this);
		initPoints();
	}


	private void initPoints() {
		
		LinearLayout pointLinear=(LinearLayout) findViewById(R.id.main_linear);
		mPoints=new ImageView[Constants.GUIDE_PICS.length];
		
		for (int i = 0; i < mPoints.length; i++) {
			
			mPoints[i]=(ImageView)pointLinear.getChildAt(i);
			mPoints[i].setEnabled(true);
			mPoints[i].setOnClickListener(this);
			mPoints[i].setTag(i);
			
		}//for
		
		mCurrentIndex=0;
		mPoints[mCurrentIndex].setEnabled(false);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		
		setCurrentDot(position);
		
	}

	private void setCurrentDot(int position) {//设置当前引导界面的位置
		
		if (position==Constants.GUIDE_PICS.length-1) {
			mLoginButton.setVisibility(View.VISIBLE);
			mRegisterButton.setVisibility(View.VISIBLE);
		} else {
			mLoginButton.setVisibility(View.GONE);
			mRegisterButton.setVisibility(View.GONE);

		}
		
		if (position<0||position>Constants.GUIDE_PICS.length-1||mCurrentIndex==position) {
			return;
		}
		
		mPoints[position].setEnabled(false);
		mPoints[mCurrentIndex].setEnabled(true);
		mCurrentIndex=position;
		
		
	}

	@Override
	public void onClick(View v) {
		
//		int position=(Integer) v.getTag();
//		setCurrentView(position);
//		setCurrentDot(position);
		
		SharedPreferences.Editor editor=mPreferences.edit();
		if (mIsfirst) {
			editor.putBoolean("isfrist", false);
		}
		editor.commit();
		
		switch (v.getId()) {
		case R.id.enter_login://进入登录界面
			enterHomePageActivity();
			break;
			
		case R.id.enter_register:
			enterRegisterActivity();
			break;

		default:
			break;
		}
		
	}
	
	private void enterRegisterActivity() {
		Intent intent=new Intent();
		intent.setClass(this, RegisterActivity.class);
		startActivity(intent);
		
	}

	private void enterHomePageActivity() {
		Intent intent=new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}
	
	private void enterWelcomeActivity() {
		Intent intent=new Intent();
		intent.setClass(this, WelcomeActivity.class);
		startActivity(intent);
		finish();
		
	}

	private void setCurrentView(int position) {
		
		if (position<0||position>=Constants.GUIDE_PICS.length) {
			return;
		}
		mViewPager.setCurrentItem(position);
		
	}


}
