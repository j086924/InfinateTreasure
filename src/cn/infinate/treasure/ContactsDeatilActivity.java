package cn.infinate.treasure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class ContactsDeatilActivity extends Activity implements OnClickListener{
	
	private ImageView mImgBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contacts_detail_activity);
		initViews();
	}

	private void initViews() {
		
		mImgBack=(ImageView) findViewById(R.id.contacts_detail_back);
		mImgBack.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.contacts_detail_back:
			onBackPressed();
			break;

		default:
			break;
		}//switch
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	}


}
