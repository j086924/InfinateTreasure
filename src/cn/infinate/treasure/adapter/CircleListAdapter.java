package cn.infinate.treasure.adapter;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.infinate.treasure.R;
import cn.infinate.treasure.utils.ImageService;
import cn.infinate.treasure.view.RectImageView;


public class CircleListAdapter extends BaseAdapter {//圈子列表适配器
	
	private LayoutInflater mInflater;
	private List<String>  mList;
	private Context mContext;
	
	public CircleListAdapter(Context context,List<String> mList) {
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
		CircleViewHolder  viewHolder;
		if (null==view) {
			viewHolder=new CircleViewHolder();
			view=mInflater.inflate(R.layout.circle_list_item,null);
			viewHolder.mImageView=(RectImageView) view.findViewById(R.id.circle_list_img);
			viewHolder.mTextView=(TextView) view.findViewById(R.id.circle_list_text);
			viewHolder.mPersonCount=(TextView) view.findViewById(R.id.circle_person_txt);
			viewHolder.mCommentCount=(TextView) view.findViewById(R.id.circle_comment_text);
			view.setTag(viewHolder);
		}else
		{
			viewHolder=(CircleViewHolder) view.getTag();
		}
		viewHolder.mImageView.setTag(mList.get(position));
		new ImageService().setImageBitmap(viewHolder.mImageView, mList.get(position),mContext);
		viewHolder.mTextView.setText("Tell me about your career");
		viewHolder.mPersonCount.setText(position+(new Random().nextInt(9))*100+"");
		viewHolder.mCommentCount.setText(position+(new Random().nextInt(9))*1000+"");
		
		return view;
	}

}


class CircleViewHolder
{
	RectImageView mImageView;//圈子头像
	TextView mTextView;//圈子描述
	TextView mPersonCount;//关注圈子的人数
	TextView mCommentCount;//圈子评论数
}
