package model;

public class Dupdate {
	private String designername;
	private String closed;
	private String shopname;
	private String designerimg;
	public Dupdate(String designername, String closed, String shopname, String designerimg) {
		
		this.designername = designername;
		this.closed = closed;
		this.shopname = shopname;
		this.designerimg = designerimg;
	}
	
	public String getDesignername() {
		return designername;
	}
	public void setDesignername(String designername) {
		this.designername = designername;
	}
	public String getClosed() {
		return closed;
	}
	public void setClosed(String closed) {
		this.closed = closed;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getDesignerimg() {
		return designerimg;
	}
	public void setDesignerimg(String designerimg) {
		this.designerimg = designerimg;
	}
	
	

}
