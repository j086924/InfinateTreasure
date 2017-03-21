package cn.infinate.treasure;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import cn.infinate.treasure.utils.ImageService;

public class PersonImageShow extends Activity implements OnClickListener {
	private ImageView mImageView;
	
	private Handler mHandler=new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			Bitmap mBitmap=(Bitmap) msg.obj;
			mImageView.setImageBitmap(mBitmap);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.person_image);
		initViews();
		obtainData();
	}

	private void obtainData() {
		final String img_url=getIntent().getStringExtra("img_url");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Bitmap mBitmap=ImageService.getBitmapFromUrl(img_url);
				Message msg=new Message();
				msg.obj=mBitmap;
				mHandler.sendMessage(msg);
			}
		}).start();
		
		
	}

	private void initViews() {
		mImageView=(ImageView) findViewById(R.id.person_img);
		mImageView.setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	}

	@Override
	public void onClick(View v) {
		onBackPressed();
	}
	
	

}
