package cn.infinate.treasure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import cn.infinate.treasure.R;

public class ImageService {
	
//	private RoundImageView mImageView;
	private ImageView mImageView;
	private String mImgUrl;
	private LruCache<String, Bitmap> mMemoryCache;

	
	public ImageService() 
	{
		
	   int mMaxMemory=(int) Runtime.getRuntime().maxMemory();
	   int mCacheSize=mMaxMemory/8;
	   mMemoryCache=new LruCache<String, Bitmap>(mCacheSize)
			   {
		         protected int sizeOf(String key, Bitmap value) 
		         {
			          return value.getRowBytes()*value.getHeight();
		         }
	           };
	}
	
	
	/**
	 * 
	 *  Function:
	 *  從緩存中取出圖片
	 *  @author jon  DateTime 2015-12-4 下午2:05:36
	 *  @param key
	 *  @return
	 */
	public Bitmap getBitmapFromCache(String key) {
		return mMemoryCache.get(key);
	}
	
	/**
	 *  將圖片添加到緩存
	 *  Function:
	 * 
	 *  @author jon  DateTime 2015-12-4 下午2:05:05
	 *  @param key
	 *  @param mBitmap
	 */
	public void addBitmapToCache(String key,Bitmap mBitmap) {
		if (mMemoryCache.get(key)==null&&mBitmap!=null) {
			mMemoryCache.put(key, mBitmap);
		}
	}
	
	private Handler mHandler=new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.MESSAGE_URL:
				Bitmap mBitmap=(Bitmap) msg.obj;
				if (mImageView.getTag().equals(mImgUrl)) {
					mImageView.setImageBitmap(mBitmap);
				}
				//mImageView.setImageBitmap(mBitmap);
				
				break;

			default:
				break;
			}
			
		};
	};
	
	/**
	 * 
	 *  Function:
	 *  通过线程获取网络中的图片，并通过handler将图片信息发给主线程进行UI更新
	 *  @author jon  DateTime 2015-12-3 下午3:23:33
	 *  @param mImageView
	 *  @param url
	 */
	public void setImageBitmap(ImageView mImageView,final String url,final Context context) {
		
		   this.mImageView=mImageView;
		   this.mImgUrl=url;
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Bitmap mBitmap=null;
					if (mMemoryCache.get(url)!=null) {
						mBitmap=getBitmapFromCache(url);
					}else
					{
						mBitmap=getBitmapFromUrl(url);
						addBitmapToCache(url, mBitmap);
					}
					
					if (null==mBitmap) {//set default bitmap
						mBitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
					}
					Message msg=new Message();
					msg.what=Constants.MESSAGE_URL;
					msg.obj=mBitmap;
					mHandler.sendMessage(msg);
				}
			}).start();
		
	}
	
	/**
	 * 
	 *  Function:
	 *  通过url从网络获取图片
	 *  @author jon  DateTime 2015-12-3 下午3:22:43
	 *  @param path
	 *  @return
	 */
	public static Bitmap getBitmapFromUrl(String path)
	{
		URL mUrl=null;
		HttpURLConnection mConn=null;
		InputStream mIs=null;
		Bitmap mBitmap=null;
		
		try {
			mUrl=new URL(path);
			mConn=(HttpURLConnection) mUrl.openConnection();
			mConn.setConnectTimeout(5000);
			mConn.setRequestMethod("GET");
			
			if (mConn.getResponseCode()==200) {
				mIs=mConn.getInputStream();
				mBitmap=BitmapFactory.decodeStream(mIs);
			}
			return mBitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if (null!=mConn) {
				mConn.disconnect();
			}
			
			if (null!=mIs) {
				try {
					mIs.close();
				} catch (IOException e) {
				}
			}
		}
		
		return null;
	}

}
