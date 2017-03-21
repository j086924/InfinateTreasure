package cn.infinate.treasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class WelcomeActivity extends Activity implements OnClickListener {
	
	private Button mLoginButton;//登录
	private Button mRegisterButton;//注册
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome_activity);
		initViews();
		initListeners();
	}

	private void initListeners() {
		mLoginButton.setOnClickListener(this);
		mRegisterButton.setOnClickListener(this);
	}

	private void initViews() {
		mLoginButton=(Button) findViewById(R.id.wel_enter_login);
		mRegisterButton=(Button) findViewById(R.id.wel_enter_register);
		
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.wel_enter_login:
			enterHomePageActivity();
			break;
        case R.id.wel_enter_register:
        	enterRegisterActivity();
			break;

		default:
			break;
		}
		
	}
	
	private void enterRegisterActivity() {//进入注册界面
		Intent intent=new Intent();
		intent.setClass(this, RegisterActivity.class);
		startActivity(intent);
		
	}

	private void enterHomePageActivity() {//进入主页面
		Intent intent=new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}

}
