package cn.infinate.treasure;


import java.util.Timer;
import java.util.TimerTask;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.infinate.treasure.fragment.BaseFragment;
import cn.infinate.treasure.fragment.CircleFragment;
import cn.infinate.treasure.fragment.ContactsFragment;
import cn.infinate.treasure.fragment.FragmentFactory;
import cn.infinate.treasure.fragment.HomepageFragment;
import cn.infinate.treasure.fragment.MeFragment;
import cn.infinate.treasure.fragment.ProjectFragment;
import cn.infinate.treasure.utils.Constants;

public class MainActivity extends BaseFragmentActivity implements OnClickListener
{

	 private static String TAG = Constants.getClassName(MainActivity.class);
	
	
	private ImageButton mHomePageImg;//主页
	private ImageButton mContactsImg;//人脉
	private ImageButton mCircleImg;//圈子
	private ImageButton mProjectImg;//项目
	private ImageButton mMeImg;//我的设置
	private View mStatusBar;
	
	
	private static int mCurrntTabInt = -1;
	private static String mCurrentFragmentTag;
	private static BaseFragment mCurrentFragment;
	private BaseFragment mToFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		initView();
		initEvents();
		initFirstPage();
	}

	private void initFirstPage() {//第一次进入初始化页面
		switchTabChoose(0);
	    switchContent(Constants.getClassName(HomepageFragment.class));
	}

	private void initEvents()
	{
		mHomePageImg.setOnClickListener(this);
		mContactsImg.setOnClickListener(this);
		mCircleImg.setOnClickListener(this);
		mProjectImg.setOnClickListener(this);
		mMeImg.setOnClickListener(this);
		
	}

	private void initView()
	{
		// ImageButton
		mHomePageImg = (ImageButton) findViewById(R.id.id_tab_homepage_img);
		mContactsImg = (ImageButton) findViewById(R.id.id_tab_contacts_img);
		mCircleImg = (ImageButton) findViewById(R.id.id_tab_circle_img);
		mProjectImg = (ImageButton) findViewById(R.id.id_tab_project_img);
		mMeImg= (ImageButton) findViewById(R.id.id_tab_me_img);
		mStatusBar=findViewById(R.id.status_bar);

	}
	
	public static void showText()
	{
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.id_tab_homepage_img:
			switchFragment(0,HomepageFragment.FRAGMENT_TAG);
			break;
		case R.id.id_tab_contacts_img:
			switchFragment(1,ContactsFragment.FRAGMENT_TAG);
			break;
		case R.id.id_tab_circle_img:
			switchFragment(2,CircleFragment.FRAGMENT_TAG);
			break;
		case R.id.id_tab_project_img:
			switchFragment(3,ProjectFragment.FRAGMENT_TAG);
			break;
		case R.id.id_tab_me_img:
			switchFragment(4,MeFragment.FRAGMENT_TAG);
			break;

		default:
			break;
		}//switch
	}

	private void switchFragment(int i, String tag) {//切换页面
		
		switchTabChoose(i);
		switchContent(tag);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public void switchContent(String tag) {
		 Log.d(TAG, "switchContent tag "+tag);
		 
		 mCurrentFragmentTag=tag;
		 mToFragment=(BaseFragment)getSupportFragmentManager()
	                .findFragmentByTag(tag);
		 
		 if (null!=mToFragment) {
			 
			    if (mCurrentFragment == mToFragment) {
	                return;
	            }
			    
	            if (!mToFragment.isAdded()) {
	                Log.d(TAG, "!toFragment.isAdded() " + tag);
	                FragmentTransaction mFmt = getSupportFragmentManager()
	                        .beginTransaction();
	                if (mCurrentFragment != null) {
	                    mFmt.hide(mCurrentFragment);
	                }
	                mFmt.add(R.id.lay_content_container, mToFragment, tag);
	                mFmt.commit();
	                mCurrentFragment = mToFragment;
	            } else {
	                Log.d(TAG, "toFragment.isAdded() " + tag);
	                if (mToFragment.isHidden()) {
	                    Log.d(TAG,
	                            "toFragment.isHidden() " + tag + mToFragment.getId());
	                }
	                FragmentTransaction mFmt = getSupportFragmentManager()
	                        .beginTransaction();
	                if (mCurrentFragment != null) {
	                    Log.d(TAG, "mCurrentFragment != null "
	                            + mCurrentFragment.getTag());
	                    mFmt.hide(mCurrentFragment);
	                } else {
	                    Log.d(TAG, "mCurrentFragment == null ");
	                }
	                mFmt.show(mToFragment).commit();
	                mCurrentFragment = mToFragment;
	            }
			
		}else
		{
			  Log.d(TAG, "toFragment == null " + tag);
			  mToFragment = FragmentFactory.getFragmentByTag(tag);
	            if (mToFragment == null) {
	                throw new NullPointerException(
	                        "you should create a new Fragment by Tag" + tag);
	            }

	            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
	                    .beginTransaction();
	            fragmentTransaction
	                    .add(R.id.lay_content_container, mToFragment, tag);
	            if (mCurrentFragment != null) {
	                fragmentTransaction.hide(mCurrentFragment);
	            }
	            fragmentTransaction.commit();
	            mCurrentFragment = mToFragment;
			
		}
		 
	}

	public void switchTabChoose(int i) {//切换底部导航项
		
		mCurrntTabInt=i;
		switch (i) {
		case 0:
			
			mHomePageImg.setSelected(true);
			mContactsImg.setSelected(false);
			mCircleImg.setSelected(false);
			mProjectImg.setSelected(false);
			mMeImg.setSelected(false);
			
			break;
        case 1:
        	
        	mHomePageImg.setSelected(false);
			mContactsImg.setSelected(true);
			mCircleImg.setSelected(false);
			mProjectImg.setSelected(false);
			mMeImg.setSelected(false);
			
			break;
        case 2:
        	
        	mHomePageImg.setSelected(false);
			mContactsImg.setSelected(false);
			mCircleImg.setSelected(true);
			mProjectImg.setSelected(false);
			mMeImg.setSelected(false);
			
			break;
        case 3:
        	
        	mHomePageImg.setSelected(false);
			mContactsImg.setSelected(false);
			mCircleImg.setSelected(false);
			mProjectImg.setSelected(true);
			mMeImg.setSelected(false);
			
			break;
        case 4:
        	
        	mHomePageImg.setSelected(false);
			mContactsImg.setSelected(false);
			mCircleImg.setSelected(false);
			mProjectImg.setSelected(false);
			mMeImg.setSelected(true);
			
			break;
			
		default:
			break;
		}//switch
		
	}
	
	
	 private static Boolean mIsQuit = false;
	 private Timer mTimer = new Timer();
	 
	 @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if (keyCode==KeyEvent.KEYCODE_BACK) {//点击返回键处理事件
			 
			 if (!mIsQuit) {
				 mIsQuit=true;
				 Toast.makeText(MainActivity.this, R.string.exit_tips, Toast.LENGTH_SHORT).show();
				 mTimer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						mIsQuit=false;
					}
				}, 2000);
				
			}else
			{
				finish();
				System.exit(0);
			}
			
		}
		 
		return false;
	}
	
	
	
	  @Override
	    protected void onDestroy() {
	        Log.v(TAG, "onDestroy");
	        mCurrentFragmentTag = null;
	        mCurrntTabInt = -1;
	        super.onDestroy();
	    }

	    @Override
	    public void finish() {
	        Log.v(TAG, "finish");
	        mCurrentFragmentTag = null;
	        mCurrntTabInt = -1;
	        super.finish();
	    }


}
