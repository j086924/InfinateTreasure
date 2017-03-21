package cn.infinate.treasure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class FinancialRegActivity extends Activity implements OnClickListener{
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_financial_user);
		initViews();
		initListeners();
	}

	private void initListeners() {
		
	}

	private void initViews() {
		
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.reg_next:
			
			break;

		default:
			break;
		}
		
	}
																					
}
