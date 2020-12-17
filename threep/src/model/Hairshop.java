package model;

public class  Hairshop{

	private String shopname;
	private String shoptel;
	private String shopaddress;
	private String img;


	public Hairshop(String shopname, String shoptel, String shopaddress, String img) {

		this.shopname = shopname;
		this.shoptel = shoptel;
		this.shopaddress = shopaddress;
		this.img = img;
	}

	public String getshopName() {
		return shopname;
	}
	public void setshopName(String shopname) {
		this.shopname = shopname;
	}
	public String getshopTel() {
		return shoptel;
	}
	public void setshopTel(String shoptel) {
		this.shoptel = shoptel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getshopAddress() {
		return shopaddress;
	}
	public void setshopAddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}


}
