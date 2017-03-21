package cn.infinate.treasure.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.infinate.treasure.BaseFragmentActivity;
import cn.infinate.treasure.R;
import cn.infinate.treasure.utils.Constants;

public class ProjectFragment extends BaseFragment implements OnClickListener{
	
	public static String FRAGMENT_TAG=Constants.getClassName(ProjectFragment.class);
	
	private LinearLayout mLinearAllProjectStatus;
	private LinearLayout mLinearAllProjectProfession;
	private LinearLayout mLinearAllProjectArea;
	
	private BaseFragmentActivity mActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_project, null);
		initView(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity=(BaseFragmentActivity) getActivity();
	}

	public void initView(View view) {
		mLinearAllProjectStatus=(LinearLayout) view.findViewById(R.id.all_project_status);
		mLinearAllProjectProfession=(LinearLayout) view.findViewById(R.id.all_project_profession);
		mLinearAllProjectArea=(LinearLayout) view.findViewById(R.id.all_project_area);
		mLinearAllProjectStatus.setOnClickListener(this);
		mLinearAllProjectProfession.setOnClickListener(this);
		mLinearAllProjectArea.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.all_project_status:
			
			AlertDialog mStatusDialog=new AlertDialog.Builder(mActivity)
//			.setTitle(R.string.all_profession)
			.setItems(R.array.all_project_status,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					switch (which) {
					case 0:
						
						break;
                    case 1:
						
                    	
						break;
                    case 3:
						
                    	
						break;

					default:
						break;
					}
					
				}
			} )
			.create();
			mStatusDialog.show();
			
			break;
		case R.id.all_project_area:
			
			AlertDialog areaDialog=new AlertDialog.Builder(mActivity)
			.setItems(R.array.all_city_area,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					switch (which) {
					case 0:
						
						AlertDialog beijingDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.beijing,new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								switch (which) {
								case 0:
									
									break;
			                    case 1:
									
			                    	
									break;

								default:
									break;
								}
								
							}
						} )
						.create();
						beijingDialog.show();
						
						break;
                    case 1:
						
                    	
						break;

					default:
						break;
					}
					
				}
			} )
			.create();
        	areaDialog.show();
			
		  break;
			
		case R.id.all_project_profession:
			
			AlertDialog mProfessionDialog=new AlertDialog.Builder(mActivity)
//			.setTitle(R.string.all_profession)
			.setItems(R.array.all_profession,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					switch (which) {
					case 0:
						
						break;
                    case 1:
						
                    	
						break;

					default:
						break;
					}
					
				}
			} )
			.create();
			mProfessionDialog.show();
			
			break;

		default:
			break;
		}
		
	}


}
