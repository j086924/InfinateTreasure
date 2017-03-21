package cn.infinate.treasure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CommonRegActivity extends Activity implements OnClickListener{
	
	private String phoneNo;
	private EditText nickName;
	private EditText pwd;
	private EditText rePwd;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_common_user);
		initViews();
		initListeners();
	}

	private void initListeners() {
		
	}

	private void initViews() {
		
		
	}

	@Override
	public void onClick(View v) {
		
		
	}
																					
}
