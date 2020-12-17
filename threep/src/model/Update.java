package model;

public class Update {

	private String shopid;
	private String shoppassword;
	private String shopname;
	private String shoptel;
	private String shopaddress;
	private String img;
	private int type;
	public Update(String shopid, String shoppassword, String shopname, String shoptel, String shopaddress, String img,
			int type) {

		this.shopid = shopid;
		this.shoppassword = shoppassword;
		this.shopname = shopname;
		this.shoptel = shoptel;
		this.shopaddress = shopaddress;
		this.img = img;
		this.type = type;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getShoppassword() {
		return shoppassword;
	}
	public void setShoppassword(String shoppassword) {
		this.shoppassword = shoppassword;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShoptel() {
		return shoptel;
	}
	public void setShoptel(String shoptel) {
		this.shoptel = shoptel;
	}
	public String getShopaddress() {
		return shopaddress;
	}
	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}



}
