package cn.infinate.treasure.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import cn.infinate.treasure.BaseFragmentActivity;
import cn.infinate.treasure.PersonImageShow;
import cn.infinate.treasure.R;
import cn.infinate.treasure.adapter.ContactsListAdapter;
import cn.infinate.treasure.db.DBHelper;
import cn.infinate.treasure.utils.Constants;
import cn.infinate.treasure.view.CustomListView;

public class ContactsFragment extends BaseFragment implements OnClickListener,OnItemClickListener{
	
	public static String FRAGMENT_TAG=Constants.getClassName(ContactsFragment.class);
	private LinearLayout mLinearAllProfession;
	private LinearLayout mLinearAllArea;
	private CustomListView mListView;
	private ProgressDialog mProgressDialog;
	
	private BaseFragmentActivity mActivity;
	private ContactsListAdapter mListAdapter;
	private List<String> mList;
	
	private DBHelper mDbHelper;
	
	private int mCount = 10;//加载数据条数
	private static final int REFRESH_HEAD_FINISH = 0x10;//上拉刷新
	private static final int REFRESH_FOOT_FINISH = 0x11;//下拉刷新
	private static final int UPDATE_LISTVIEW_ADAPTER = 0x12;//下拉刷新
	
	private Handler mHandler=new Handler()
	{
		
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_HEAD_FINISH:
				
				if (null!=mListAdapter) {
					mListAdapter.notifyDataSetChanged();
				
				}
				mListView.onRefreshComplete();
				
				break;
				
            case REFRESH_FOOT_FINISH:
            	
            	if (null!=mListAdapter) {
					mListAdapter.notifyDataSetChanged();
				}
            	mListView.onLoadMoreComplete();
            	
				break;
				
            case UPDATE_LISTVIEW_ADAPTER:
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
		View view=inflater.inflate(R.layout.tab_contacts, null);
		initView(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity=(BaseFragmentActivity) getActivity();
		mListView=(CustomListView) this.getView().findViewById(R.id.refresh_list);
		initData();
		loadData();
		mListView.setOnItemClickListener(this);
		
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
					mHandler.sendEmptyMessage(REFRESH_HEAD_FINISH);
					
				}else if(type==1)
				{
					mHandler.sendEmptyMessage(REFRESH_FOOT_FINISH);
					
				}
				
			}
			
			
		}).start();
		
	}

	private void loadData() {
		
		loadingDataDialog();
		
    	new Thread(new Runnable() {

			public void run() {
				
				mListAdapter=new ContactsListAdapter(mActivity, mList);
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	mHandler.sendEmptyMessage(UPDATE_LISTVIEW_ADAPTER);
		    	
		    	if (null!=mProgressDialog) {
		    		mProgressDialog.dismiss();
				}

			}
		}).start();
		
	}

	private void initData() {
		
		mDbHelper=DBHelper.getInstance(mActivity);
		
		mList=new ArrayList<String>();
		mList=Arrays.asList(Constants.IMG_URLS);
//		readAssetMySql();
		
	}  

	public void initView(View view) {
		
		mLinearAllProfession=(LinearLayout) view.findViewById(R.id.contacts_all_profession);
		mLinearAllArea=(LinearLayout) view.findViewById(R.id.contacts_all_area);
		mLinearAllProfession.setOnClickListener(this);
		mLinearAllArea.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.contacts_all_profession:
			
			
			AlertDialog alertDialog=new AlertDialog.Builder(mActivity)
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
			alertDialog.show();
			
			break;
        case R.id.contacts_all_area:
        	
        	//final String[] city=mActivity.getResources().getStringArray(R.array.all_city_area);
        	
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
						
                    	AlertDialog tianjingDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.tianjing,new DialogInterface.OnClickListener() {
							
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
                    	tianjingDialog.show();
						
                    	
						break;
                    case 2:
                    	
                    	AlertDialog hebeiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.hebei,new DialogInterface.OnClickListener() {
							
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
                    	hebeiDialog.show();
						
                    	
						break;
					case 3:
						
						AlertDialog shanxiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.shanxi,new DialogInterface.OnClickListener() {
							
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
						shanxiDialog.show();
						
                    	
						break;
                    case 4:
                    	
                    	AlertDialog neimengDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.neimenggu,new DialogInterface.OnClickListener() {
							
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
                    	neimengDialog.show();
						
						
                    	
						break;
                    case 5:
                    	
                    	AlertDialog liaoningDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.liaoning,new DialogInterface.OnClickListener() {
							
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
                    	liaoningDialog.show();
						
						
                    	
						break;
					case 6:
						
						AlertDialog jilingDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.jiling,new DialogInterface.OnClickListener() {
							
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
						jilingDialog.show();
						
                    	
						break;
						
					case 7:
						
						AlertDialog heilongjiangDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.heilongjiang,new DialogInterface.OnClickListener() {
							
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
						heilongjiangDialog.show();
						
						break;
						
					case 8:
						
						AlertDialog shanghaiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.shanghai,new DialogInterface.OnClickListener() {
							
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
						shanghaiDialog.show();
						break;
						
					case 9:
						

						AlertDialog jiangsuDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.jiangsu,new DialogInterface.OnClickListener() {
							
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
						jiangsuDialog.show();
						break;
						
					case 10:
						
						AlertDialog zhejiangDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.zhejiang,new DialogInterface.OnClickListener() {
							
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
						zhejiangDialog.show();
						
						break;
						
					case 11:
						
						AlertDialog anhuiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.anhui,new DialogInterface.OnClickListener() {
							
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
						anhuiDialog.show();
						break;
						
					case 12:
						
						AlertDialog fujianDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.fujian,new DialogInterface.OnClickListener() {
							
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
						fujianDialog.show();
						break;
						
					case 13:
						

						AlertDialog jiangxiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.jiangxi,new DialogInterface.OnClickListener() {
							
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
						jiangxiDialog.show();
						break;
						
					case 14:
						
						AlertDialog shandongDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.shandong,new DialogInterface.OnClickListener() {
							
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
						shandongDialog.show();
						break;
						
					case 15:
						
						AlertDialog henanDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.henan,new DialogInterface.OnClickListener() {
							
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
						henanDialog.show();
						break;
						
					case 16:
						
						AlertDialog hubeiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.hubei,new DialogInterface.OnClickListener() {
							
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
						hubeiDialog.show();
						break;
						
					case 17:
						
						AlertDialog hunanDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.hunan,new DialogInterface.OnClickListener() {
							
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
						hunanDialog.show();
						break;
						
					case 18:
						
						AlertDialog guangdongDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.guangdong,new DialogInterface.OnClickListener() {
							
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
						guangdongDialog.show();
						break;
						
					case 19:
						
						AlertDialog guangxiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.guangxi,new DialogInterface.OnClickListener() {
							
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
						guangxiDialog.show();
						break;
						
					case 20:
						
						AlertDialog hainanDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.hainan,new DialogInterface.OnClickListener() {
							
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
						hainanDialog.show();
						break;
						
					case 21:
						
						AlertDialog chongqingDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.chongqing,new DialogInterface.OnClickListener() {
							
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
						chongqingDialog.show();
						break;
						
					case 22:
						
						AlertDialog sichuanDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.sichuan,new DialogInterface.OnClickListener() {
							
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
						sichuanDialog.show();
						break;
						
					case 23:
						
						AlertDialog guizhouDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.guizhou,new DialogInterface.OnClickListener() {
							
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
						guizhouDialog.show();
						break;
						
					case 24:
						
						AlertDialog yunanDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.yunan,new DialogInterface.OnClickListener() {
							
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
						yunanDialog.show();
						break;
						
					case 25:
						
						AlertDialog xizangDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.xizang,new DialogInterface.OnClickListener() {
							
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
						xizangDialog.show();
						break;
						
					case 26:
						
						AlertDialog shengxiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.shengxi,new DialogInterface.OnClickListener() {
							
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
						shengxiDialog.show();
						break;
						
					case 27:
						
						AlertDialog gansuDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.gansu,new DialogInterface.OnClickListener() {
							
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
						gansuDialog.show();
						break;
						
					case 28:
						
						AlertDialog qinghaiDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.qinghai,new DialogInterface.OnClickListener() {
							
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
						qinghaiDialog.show();
						break;
						
					case 29:
						
						AlertDialog ningxiaDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.ningxia,new DialogInterface.OnClickListener() {
							
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
						ningxiaDialog.show();
						break;
						
					case 30:
						
						AlertDialog xinjiangDialog=new AlertDialog.Builder(mActivity)
						.setItems(R.array.xinjiang,new DialogInterface.OnClickListener() {
							
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
						xinjiangDialog.show();
						break;
						

					default:
						break;
					}
					
				}
			} )
			.create();
        	areaDialog.show();
        	
        	
			break;

		default:
			break;
		}
		
	}
	
	private void loadingDataDialog() {

		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("正在加载数据，请稍后");
		mProgressDialog.show();

	}
	
   public void readAssetMySql() {
		
		InputStream in = null;
		BufferedReader r=null;
		
		try {
			
			in = getResources().getAssets().open("city.sql");
			r = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			read(r);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	private void read(BufferedReader r) {
		String line=null;
		
		try {
			while (null!=(line=r.readLine())) {
				line=line.trim();
				if (line.length()==0) {
					continue;
				}
				
				mDbHelper.insertAreaData(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String url=mList.get(position-1);
		Intent mIntent=new Intent();
		mIntent.putExtra("img_url", url);
		mIntent.setClass(mActivity, PersonImageShow.class);
		startActivity(mIntent);
		//Toast.makeText(mActivity, "you click position is "+position, Toast.LENGTH_SHORT).show();
		
	}


}
