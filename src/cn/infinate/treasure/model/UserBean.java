package cn.infinate.treasure.model;

public class UserBean {
	
	private int id;
	private String phoneNo;//電話號碼
	private String name;//姓名
	private String pwd;//密碼
	
	public UserBean(int id, String phoneNo, String name, String pwd) {
		super();
		this.id = id;
		this.phoneNo = phoneNo;
		this.name = name;
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
