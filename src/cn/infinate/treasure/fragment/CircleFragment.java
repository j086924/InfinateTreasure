package cn.infinate.treasure.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.infinate.treasure.BaseFragmentActivity;
import cn.infinate.treasure.R;
import cn.infinate.treasure.adapter.CircleListAdapter;
import cn.infinate.treasure.adapter.ContactsListAdapter;
import cn.infinate.treasure.db.DBHelper;
import cn.infinate.treasure.utils.Constants;
import cn.infinate.treasure.view.CustomListView;

public class CircleFragment extends BaseFragment{
	
	public static String FRAGMENT_TAG=Constants.getClassName(CircleFragment.class);
	
	private CustomListView mListView;
	private BaseFragmentActivity mActivity;
	private CircleListAdapter mListAdapter;
	private List<String> mList;
	private ProgressDialog mProgressDialog;
	
	
	private int mCount = 10;//加载数据条数
	private static final int REFRESH_CIRCLE_HEAD_FINISH = 0x13;//上拉刷新
	private static final int REFRESH_CIRCLE_FOOT_FINISH = 0x14;//下拉刷新
	private static final int UPDATE_CIRCLE_LISTVIEW_ADAPTER = 0x15;//下拉刷新
	
	
	private Handler mHandler=new Handler()
	{
		
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_CIRCLE_HEAD_FINISH:
				
				if (null!=mListAdapter) {
					mListAdapter.notifyDataSetChanged();
				
				}
				mListView.onRefreshComplete();
				
				break;
				
            case REFRESH_CIRCLE_FOOT_FINISH:
            	
            	if (null!=mListAdapter) {
					mListAdapter.notifyDataSetChanged();
				}
            	mListView.onLoadMoreComplete();
            	
				break;
				
            case UPDATE_CIRCLE_LISTVIEW_ADAPTER:
            	mListView.setAdapter(mListAdapter);
            	break;

			default:
				break;
			}
			
		};
		
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_circle, null);
		initView(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity=(BaseFragmentActivity) getActivity();
		mListView=(CustomListView) this.getView().findViewById(R.id.circle_list);
		initData();
		loadData();
		
		mListView.setOnRefreshListener(new CustomListView.OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				RefreshData(0);
				
			}
		});
		
		mListView.setOnLoadListener(new CustomListView.OnLoadMoreListener() {
			
			@Override
			public void onLoadMore() {
				RefreshData(1);
				
			}

		});
	}

	private void initView(View view) {
		
	}
	
	
protected void RefreshData(final int type) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				switch (type) {
				case 0://这里是下拉刷新数据
					
					break;
					
                case 1://这里是上拉刷新数据
					
					break;

				default:
					break;
				}//switch
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (type==0) {
					mHandler.sendEmptyMessage(REFRESH_CIRCLE_HEAD_FINISH);
					
				}else if(type==1)
				{
					mHandler.sendEmptyMessage(REFRESH_CIRCLE_FOOT_FINISH);
					
				}
				
			}
			
			
		}).start();
		
	}

private void loadData() {
	
	loadingDataDialog();
	
	new Thread(new Runnable() {

		public void run() {
			
			mListAdapter=new CircleListAdapter(mActivity, mList);
	    	
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	mHandler.sendEmptyMessage(UPDATE_CIRCLE_LISTVIEW_ADAPTER);
	    	
	    	if (null!=mProgressDialog) {
	    		mProgressDialog.dismiss();
			}

		}
	}).start();
	
}

private void initData() {
	
	mList=new ArrayList<String>();
	mList=Arrays.asList(Constants.IMG_URLS);
	
}  

private void loadingDataDialog() {

	mProgressDialog = new ProgressDialog(mActivity);
	mProgressDialog.setMessage("正在加载数据，请稍后");
	mProgressDialog.show();

}


}
