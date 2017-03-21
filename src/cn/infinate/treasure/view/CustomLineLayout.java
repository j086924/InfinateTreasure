package cn.infinate.treasure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomLineLayout extends LinearLayout {
	
	private RoundImageView mImageView;
	private TextView mTextView;

	public CustomLineLayout(Context context) {
		this(context, null);
	}
	
	public CustomLineLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public CustomLineLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews();
	}

	private void initViews() {
		
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
	}
	


}
