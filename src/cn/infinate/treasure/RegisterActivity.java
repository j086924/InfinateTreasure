package cn.infinate.treasure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.infinate.treasure.utils.Constants;
import cn.infinate.treasure.utils.PhoneVerify;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends Activity implements OnClickListener{
	
	private Button mRegButton;//注册下一步的按钮
	private EditText mPhoneText;
	private EditText mCodeText;
	private RadioButton mRegAgree;
	private ImageView mBackImageView;
	private TextView mObtainCode;
	
	
	private static final int MSG_SEND_SHOW=0X1100;//验证码发送剩余事件显示
    private static final int MSG_RE_SEND=0X1101;//验证码发送成功
    private static final int MSG_VERIFY_SUCCESS=0X1102;//验证码验证成功
    private EventHandler eventHandler;
    private boolean ready;
    private boolean vaifResult=false;
    private boolean running=false;
    
    
    
    private Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SEND_SHOW:
				int i=(Integer) msg.obj;
				mObtainCode.setText("剩余"+i+"s");
				break;
            case MSG_RE_SEND:
            	mObtainCode.setClickable(true);
            	mObtainCode.setText(R.string.register_obtain_code);
            	mObtainCode.setBackgroundResource(R.color.register_code_color);
				break;
				
            case MSG_VERIFY_SUCCESS:
            	int event=msg.arg1;
            	int result=msg.arg2;
            	Object data=msg.obj;
            	Log.e("event", "event=" + event);  
            	if (result==SMSSDK.RESULT_COMPLETE) {
            		if (event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
            			Toast.makeText(RegisterActivity.this, "提交验证码成功",  
                                Toast.LENGTH_SHORT).show(); 
            			handler.sendEmptyMessage(MSG_RE_SEND);
					}else if(event==SMSSDK.EVENT_GET_VERIFICATION_CODE)
					{
						Toast.makeText(RegisterActivity.this, "验证码已经发送",  
                                Toast.LENGTH_SHORT).show();
						running=false;
						mObtainCode.setClickable(false);
						mObtainCode.setText(R.string.register_obtain_code);
						mObtainCode.setBackgroundResource(R.color.register_code_color_gray);
						
					}else{
						
					}
				}//if
				
				break;

			default:
				break;
			}//switch
			
		};
	};
	                                                        
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_phone_check);
		initSDK();
		initViews();
		initListeners();
	}

	private void initSDK() {
		//初始化SDK
		SMSSDK.initSDK(this, Constants.APPKEY, Constants.APPSECRET);
				
			eventHandler = new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
						Message msg = new Message();
						msg.what=MSG_VERIFY_SUCCESS;
						msg.arg1 = event;
						msg.arg2 = result;
						msg.obj = data;
						handler.sendMessage(msg);
					}
				};
				// 注册回调监听接口
				SMSSDK.registerEventHandler(eventHandler);
				ready=true;
	}

	private void initListeners() {
		mRegButton.setOnClickListener(this);
		mBackImageView.setOnClickListener(this);
		mObtainCode.setOnClickListener(this);
		
	}

	private void initViews() {
		
		mRegButton=(Button) findViewById(R.id.reg_next);
		mPhoneText=(EditText) findViewById(R.id.reg_phone_input);
		mCodeText=(EditText) findViewById(R.id.reg_code_input);
		mRegAgree=(RadioButton) findViewById(R.id.reg_radio);
		mBackImageView=(ImageView) findViewById(R.id.reg_main_back);
		mObtainCode=(TextView)findViewById(R.id.reg_obtain_code);
		
	}

	@Override
	public void onClick(View v) {
		String phoneNum=mPhoneText.getText().toString();
		switch (v.getId()) {
		case R.id.reg_next:
			
			
			//手機號碼為空時提示
			if (mPhoneText.getText().toString()==null||mPhoneText.getText().toString().trim().equals("")) {
				Toast.makeText(RegisterActivity.this, R.string.register_error_phone_tips, Toast.LENGTH_SHORT).show();
				return;
			}
			//驗證碼為空時提示
			if (mCodeText.getText().toString()==null||mCodeText.getText().toString().trim().equals("")) {
				Toast.makeText(RegisterActivity.this, R.string.register_error_code_tips, Toast.LENGTH_SHORT).show();
				return;
			}
			
			final Map<String, String> parms=new HashMap<String, String>();
			parms.put("appkey", Constants.APPKEY);
			parms.put("phone", phoneNum);
			parms.put("zone", "86");
			parms.put("code", mCodeText.getText().toString());
			
			new Thread(new Runnable() {//開啟子線程，進行服務器校驗操作
				@Override
				public void run() {
					vaifResult=sendPostRequest(Constants.SERVER_URL, parms, "utf-8");
				}
			}).start();
			
			if (vaifResult) {//驗證驗證碼是否正確
					AlertDialog mregDialog=new AlertDialog.Builder(this)
					.setItems(R.array.register_user_industry,new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							switch (which) {
							case 0:
								
								Intent mComIntent=new Intent();
								mComIntent.setClass(RegisterActivity.this, CommonRegActivity.class);
								startActivity(mComIntent);
								
								break;
		                    case 1:
		                    	
		                    	Intent mFinaIntent=new Intent();
		                    	mFinaIntent.setClass(RegisterActivity.this, FinancialRegActivity.class);
								startActivity(mFinaIntent);
								
								break;

							default:
								break;
							}
							
						}
					} )
					.create();
					mregDialog.show();
				}else{
					Toast.makeText(RegisterActivity.this, "驗證碼有誤，請重新輸入", Toast.LENGTH_SHORT).show();
				}
			
			
			
			break;
			
			case R.id.reg_main_back:
				onBackPressed();
				break;
				
			case R.id.reg_obtain_code:
				
				if (!PhoneVerify.isPhoneNO(phoneNum)) {
					return;
				}
				SMSSDK.getVerificationCode("86", phoneNum);
				mObtainCode.setClickable(false);
				new Thread(new Runnable() {//短信倒计时提醒
					
					@Override
					public void run() {
						running=true;
						
						for (int i = 30; i >0; i--) {
							if(running)
							{
								Message msg=new Message();
								msg.what=MSG_SEND_SHOW;
								msg.obj=i;
								handler.sendMessage(msg);
								if (i<=0) {
									break;
								}
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
							}else
							{
								break;
							}
							
						}//for
						//handler.sendEmptyMessage(MSG_RE_SEND);
					}
				}).start();
				break;

		default:
			break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (ready) {
			SMSSDK.unregisterEventHandler(eventHandler);
			ready=false;
		}
	}
	
	//通過post進行短信校驗碼驗證，服務器暫時還為mob提供
	public static boolean sendPostRequest(String path, Map<String, String> params, String enc){
		
		StringBuilder sb = new StringBuilder(path);
		sb.append('?');
		if(params!=null && !params.isEmpty()){
			for(Map.Entry<String, String> entry : params.entrySet()){
				try {
					sb.append(entry.getKey()).append('=')
						.append(URLEncoder.encode(entry.getValue(), enc)).append('&');
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			sb.deleteCharAt(sb.length()-1);
		}
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(sb.toString());
			conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("POST");
			conn.setConnectTimeout(5 * 1000);

			if(conn.getResponseCode()==200){
					return true;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (ProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (null!=conn) {
				conn.disconnect();
			}
		}
		return false;
	}
																					
}
