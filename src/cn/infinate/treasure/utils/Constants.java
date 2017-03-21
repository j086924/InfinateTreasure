package cn.infinate.treasure.utils;

import android.content.res.Configuration;
import cn.infinate.treasure.R;

public class Constants {
	
	public static final String APPKEY = "14db5ada14328";
	// 填写从短信SDK应用后台注册得到的APPSECRET
	public static final String APPSECRET = "7eae3b6fe4439a3e2cbd3813b7fb61df";
	
	public static final int[] GUIDE_PICS={R.drawable.infinate_guide1,R.drawable.infinate_gudie2,R.drawable.infinate_guide3}; 
    public static final String TREA_PREFERENCES="trea_preferences";
	
	public static String getClassName(Class<?> name) {
		return name.getSimpleName();
	}
	
	
	public static final int MESSAGE_URL=0x1156;
	public static final String[] IMG_URLS={
		"http://game.conf.hinavi.net/ximi_doudiz.jpg",
		"http://r1.ykimg.com/0516000051C3B1E8670C4A550B0C5AC0",
		"http://img.pad.xunlei.com/ipad_converted/60055.jpg",
		"http://r2.ykimg.com/051600005195F7996758394E36090903",
		"http://img.pad.xunlei.com/ipad_converted/60055.jpg",
		"http://www.qiyipic.com/thumb/20101220/a16541_220_124.jpg",
		"http://pic4.qiyipic.com/thumb/20130609/a460234_220_124.jpg",
		"http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/m/m2dsamemibxpq2m.jpg",
		"http://pic6.qiyipic.com/thumb/20130626/a494534_180_236.jpg",
		"http://pic6.qiyipic.com/thumb/20130626/a494534_220_124.jpg",
		"http://img24.pplive.cn/2013/07/24/12103112092_230X306.jpg",
		"http://r3.ykimg.com/0516000051B6E9B467583902CD0AB7F0",
		"http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/l/l8irh70t36z2u8f.jpg",
		"http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/b/buadsh6rjeed1ak.jpg",
		"http://photocdn.sohu.com/20141013/vrsb1428012.jpg"
	};
	
	public static int mScreenOrientation = Configuration.ORIENTATION_PORTRAIT;
	
	public static final String SERVER_URL = "https://webapi.sms.mob.com/sms/verify";
	
	
}
