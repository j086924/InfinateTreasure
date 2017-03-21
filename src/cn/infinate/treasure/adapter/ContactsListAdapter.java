package cn.infinate.treasure.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.infinate.treasure.R;
import cn.infinate.treasure.utils.ImageService;
import cn.infinate.treasure.view.RoundImageView;


public class ContactsListAdapter extends BaseAdapter {//人脉列表适配器
	
	private LayoutInflater mInflater;
	private List<String>  mList;
	private Context mContext;
	
	public ContactsListAdapter(Context context,List<String> mList) {
		mInflater=LayoutInflater.from(context);
		this.mList=mList;
		mContext=context;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
		ViewHolder viewHolder;
		if (null==view) {
			viewHolder=new ViewHolder();
			view=mInflater.inflate(R.layout.list_item,null);
			viewHolder.mImageView=(RoundImageView) view.findViewById(R.id.list_img);
			viewHolder.mTextView=(TextView) view.findViewById(R.id.list_text);
			view.setTag(viewHolder);
		}else
		{
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.mImageView.setTag(mList.get(position));
		new ImageService().setImageBitmap(viewHolder.mImageView, mList.get(position),mContext);
		viewHolder.mTextView.setText("My position is "+position);
		
		return view;
	}

}


class ViewHolder
{
	RoundImageView mImageView;//人脉图像
	TextView mTextView;//人脉描述
}
