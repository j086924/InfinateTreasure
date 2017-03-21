package cn.infinate.treasure.model;

public class UserBeanComplex {
	
	private int id;
	private String phoneNo;
	private String name;
	private String sex;
	private int age;
	private byte[] headImg;
	private String province;
	private String city;
	private String qq;
	private String email;
	private String profession;
	private String companyName;//公司名稱
	private String companyAds;//公司地址
	private String companyBusiness;//公司業務
	public UserBeanComplex(int id,String phoneNo, String name, String sex, int age,
			byte[] headImg, String province, String city, String qq,
			String email, String profession, String companyName,
			String companyAds, String companyBusiness) {
		super();
		this.id=id;
		this.phoneNo = phoneNo;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.headImg = headImg;
		this.province = province;
		this.city = city;
		this.qq = qq;
		this.email = email;
		this.profession = profession;
		this.companyName = companyName;
		this.companyAds = companyAds;
		this.companyBusiness = companyBusiness;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public byte[] getHeadImg() {
		return headImg;
	}
	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAds() {
		return companyAds;
	}
	public void setCompanyAds(String companyAds) {
		this.companyAds = companyAds;
	}
	public String getCompanyBusiness() {
		return companyBusiness;
	}
	public void setCompanyBusiness(String companyBusiness) {
		this.companyBusiness = companyBusiness;
	}

}
