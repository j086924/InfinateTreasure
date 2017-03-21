package cn.infinate.treasure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.infinate.treasure.ContactsDeatilActivity;
import cn.infinate.treasure.R;
import cn.infinate.treasure.utils.Constants;
import cn.infinate.treasure.view.RoundImageView;

public class HomepageFragment  extends BaseFragment implements OnClickListener{
	
	public static String FRAGMENT_TAG=Constants.getClassName(HomepageFragment.class);
	private LinearLayout mContactsRecommend1;
	private LinearLayout mContactsRecommend2;
	private LinearLayout mContactsRecommend3;
	private LinearLayout mContactsRecommend4;
	private LinearLayout mContactsRecommend5;
	private RoundImageView mContactsRecommend1Img;
	private TextView mContactsRecommend1Txt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_homepage, null);
		initViews(view);
		initData();
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initListeners();
		
	}
	

	private void initData() {
		
//		new ImageService().setImageBitmap(mContactsRecommend1Img,Constants.IMG_URLS[1]);//更新第一张推荐的图片
	}

	private void initListeners() {
		mContactsRecommend1.setOnClickListener(this);
		mContactsRecommend2.setOnClickListener(this);
		mContactsRecommend3.setOnClickListener(this);
		mContactsRecommend4.setOnClickListener(this);
		mContactsRecommend5.setOnClickListener(this);
		
	}

	private void initViews(View view) {
		mContactsRecommend1=(LinearLayout) view.findViewById(R.id.contacts_recommend1);
		mContactsRecommend2=(LinearLayout) view.findViewById(R.id.contacts_recommend2);
		mContactsRecommend3=(LinearLayout) view.findViewById(R.id.contacts_recommend3);
		mContactsRecommend4=(LinearLayout) view.findViewById(R.id.contacts_recommend4);
		mContactsRecommend5=(LinearLayout) view.findViewById(R.id.contacts_recommend5);
		mContactsRecommend1Img=(RoundImageView) mContactsRecommend1.findViewById(R.id.contacts_recommend1_img);
	}

	@Override
	public void onClick(View v) {
		Intent mIntent=new Intent();
		switch (v.getId()) {
		case R.id.contacts_recommend1:
			
			//判断是否登录，没登录进入注册登录界面，登录进入人脉界面
//			switchContent(new ContactsFragment());
			//Intent mIntent=new Intent();
			mIntent.setClass(getActivity(), ContactsDeatilActivity.class);
			getActivity().startActivity(mIntent);
			
			break;
		case R.id.contacts_recommend2:
			
			//判断是否登录，没登录进入注册登录界面，登录进入人脉界面
//			switchContent(new ContactsFragment());
			mIntent.setClass(getActivity(), ContactsDeatilActivity.class);
			getActivity().startActivity(mIntent);
			
			break;
		case R.id.contacts_recommend3:
			
			//判断是否登录，没登录进入注册登录界面，登录进入人脉界面
		//	switchContent(new ContactsFragment());
			mIntent.setClass(getActivity(), ContactsDeatilActivity.class);
			getActivity().startActivity(mIntent);
			
			break;
		case R.id.contacts_recommend4:
			
			//判断是否登录，没登录进入注册登录界面，登录进入人脉界面
		//	switchContent(new ContactsFragment());
			mIntent.setClass(getActivity(), ContactsDeatilActivity.class);
			getActivity().startActivity(mIntent);
			
			break;
		case R.id.contacts_recommend5:
			
			//判断是否登录，没登录进入注册登录界面，登录进入人脉界面
		//	switchContent(new ContactsFragment());
			mIntent.setClass(getActivity(), ContactsDeatilActivity.class);
			getActivity().startActivity(mIntent);
			
			break;

		default:
			break;
		}
		
	}

	private void switchContent(Fragment to) {
		
		
		
	}


}
