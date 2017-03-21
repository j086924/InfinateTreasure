package cn.infinate.treasure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.view.View.OnTouchListener;
import cn.infinate.treasure.R;

public class RefreshListView extends ListView implements OnScrollListener,OnTouchListener {
	
	private View mHeaderView;//头部
	private TextView mRefreshText;
	private TextView mDateText;
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	
	private int mHeadViewWidth;
	private int mHeadViewHeight;
	
	private final static int NONE_STATE=0;//正常状态
	private final static int PULL_STATE=1;//下拉状态
	private final static int RELEASE_STATE=2;//释放状态
	private int mHeadState;
	
	private float mStartY;
	private int mFirstItemIndex;//第一项的索引
	private boolean mIsRemark;//用于初始化一次onTouch的标志
	

	public RefreshListView(Context context) {
		this(context, null);
	}
	
	public RefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews(context);
	}

	private void initViews(Context context) {
		
		LayoutInflater mInflater=LayoutInflater.from(context);
		mHeaderView=mInflater.inflate(R.layout.listview_header, null);
		mRefreshText=(TextView) mHeaderView.findViewById(R.id.head_tipstext);
		mImageView=(ImageView) mHeaderView.findViewById(R.id.head_imageView);
		mProgressBar=(ProgressBar) mHeaderView.findViewById(R.id.head_progressBar);
		
		measureHeaderView(mHeaderView);
		initHeadView(mHeaderView);
		setOnScrollListener(this);//添加滚动事件
		
	}


	/**
	 * 初始化headview，并添加到listview中
	 */
	private void initHeadView(View view) {
		
		mHeadViewWidth=view.getMeasuredWidth();
		mHeadViewHeight=view.getMeasuredHeight();
		view.setPadding(view.getPaddingLeft(), -mHeadViewHeight, view.getPaddingRight(), view.getPaddingBottom());
		Log.e("size", "width:" + mHeadViewWidth + " height:"
                + mHeadViewHeight);
		view.invalidate();
		addHeaderView(view);
		
		mHeadState=NONE_STATE;
		
	}

	
	/**
	 * 
	 *  Function:
	 *  重新测量headview
	 *  @author jon  DateTime 2016-1-22 下午3:42:03
	 *  @param view
	 */
	private void measureHeaderView(View view) {
		
		ViewGroup.LayoutParams mLayoutParams=view.getLayoutParams();
		if (null==mLayoutParams) {
			mLayoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int mChildWidth=ViewGroup.getChildMeasureSpec(0, 0, mLayoutParams.width);
		int mLayoutParamsHeight=mLayoutParams.height;
		
		int mChildHeight;
		
		if (mLayoutParamsHeight>0) {
			mChildHeight=MeasureSpec.makeMeasureSpec(mLayoutParamsHeight, MeasureSpec.EXACTLY);//父View决定子View的高度
		}else
		{
			mChildHeight=MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);//没有约束，自动匹配
		}
		
		view.measure(mChildWidth, mChildHeight);//重新测量headView的宽高
		
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mFirstItemIndex=firstVisibleItem;
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (mFirstItemIndex==0&&!mIsRemark) {
				mStartY=event.getY();
				mIsRemark=true;
			}
			
			break;
		 case MotionEvent.ACTION_MOVE:
			 
			 float mTempY=event.getY();
			 
			 if (mFirstItemIndex==0&&!mIsRemark) {
					mStartY=mTempY;
					mIsRemark=true;
				}
			 
			 if (mHeadState!=NONE_STATE&&(mTempY-mStartY)>0&&(mTempY-mStartY)<mHeadViewHeight) 
			 {
		        	mHeadState=PULL_STATE;
		        	updateHeaderViewByState();
				
			  }
			 break;
				
        case MotionEvent.ACTION_UP:
        	
        	if (mHeadState==PULL_STATE) {
        		mHeadState=RELEASE_STATE;
        		updateHeaderViewByState();
			}
        	
        	if (mHeadState==RELEASE_STATE) {
        		mHeadState=NONE_STATE;
        		updateHeaderViewByState();
			}
			
			break;

		default:
			break;
		}
		
		return false;
	}
	
	
    /**
     * 
     *  根据状态，显示相应的布局
     * 
     *  @author jon  DateTime 2016-1-22 下午5:10:02
     */
	private void updateHeaderViewByState() {
		
		switch (mHeadState) {
		case NONE_STATE:
			
			mHeaderView.setPadding(mHeaderView.getPaddingLeft(), -1 * mHeadViewHeight, mHeaderView.getPaddingRight(), mHeaderView.getPaddingBottom());
			mProgressBar.setVisibility(View.GONE);
			mImageView.setImageResource(View.VISIBLE);
			mRefreshText.setVisibility(View.VISIBLE);
			mDateText.setVisibility(View.VISIBLE);
			
			break;
			
        case PULL_STATE:
        	
			mProgressBar.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
			mRefreshText.setVisibility(View.VISIBLE);
			mDateText.setVisibility(View.VISIBLE);
			
			break;
			
        case RELEASE_STATE:
        	
        	mHeaderView.setPadding(mHeaderView.getPaddingLeft(), 0, mHeaderView.getPaddingRight(), mHeaderView.getPaddingBottom());
			mProgressBar.setVisibility(View.VISIBLE);
			mImageView.setVisibility(View.GONE);
			mRefreshText.setVisibility(View.VISIBLE);
			mDateText.setVisibility(View.GONE);
			
			break;

		default:
			break;
		}
		
	}



}
