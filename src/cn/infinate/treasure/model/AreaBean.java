package cn.infinate.treasure.model;

import java.io.Serializable;

public class AreaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int mAreaId;
	private String mType;
	private String mName;
	private String mParentId;
	
	public int getmAreaId() {
		return mAreaId;
	}
	public void setmAreaId(int mAreaId) {
		this.mAreaId = mAreaId;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmParentId() {
		return mParentId;
	}
	public void setmParentId(String mParentId) {
		this.mParentId = mParentId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
