package cn.infinate.treasure.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.infinate.treasure.model.AreaBean;

public class DBHelper extends SQLiteOpenHelper{
	
	private static DBHelper mDbHelper;//数据库辅助类

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	private DBHelper(Context context, String name)
	{
		this(context, name, null, 1);
	}
	
	public static synchronized DBHelper getInstance(Context context)
	{
		if (null==mDbHelper) {
			mDbHelper=new  DBHelper(context, DBUtils.DB_NAME);
		}
		return mDbHelper;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//創建所在地區表
		 db.execSQL("create table area" 
	     +"(id integer primary key, type text,name text,parentid text)");
		 
		 //創建用戶表
		 db.execSQL("create table user"
				 +"(id integer primary key,phoneNo text,name text,pwd text)");
		 
	}
	
	public void insertAreaData(String sql) throws SQLException {
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL(sql);
	}
	
	public AreaBean[] getAreaList(String parentid)throws SQLException {
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=null;
		try {
		 cursor=db.rawQuery("select * from area where parentid=?", new String[] { parentid });
			
			if (null==cursor) {
				return null;
			}
			ArrayList<AreaBean> list=new ArrayList<AreaBean>(); 
			
			while (cursor.moveToNext()) {
				
				AreaBean areaBean=new AreaBean();
				areaBean.setmAreaId(cursor.getInt(cursor.getColumnIndex("id")));
				areaBean.setmType(cursor.getString(cursor.getColumnIndex("type")));
				areaBean.setmName(cursor.getString(cursor.getColumnIndex("name")));
				areaBean.setmParentId(cursor.getString(cursor.getColumnIndex("parentid")));
				
				list.add(areaBean);
			}//while
				
			return list.isEmpty()?null:list.toArray(new AreaBean[list.size()]);

			
		} finally
		{
			if (null!=cursor) {
				cursor.close();
			}
			
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS area");
	     onCreate(db);
	}


}
