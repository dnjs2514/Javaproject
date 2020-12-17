package model;

public class  Designer{

	private String shopname;
	private String designername;
	private String closed;
	private String designerimg;
	public Designer(String shopname, String designername, String closed, String designerimg) {
		
		this.shopname = shopname;
		this.designername = designername;
		this.closed = closed;
		this.designerimg = designerimg;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
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
	public String getDesignerimg() {
		return designerimg;
	}
	public void setDesignerimg(String designerimg) {
		this.designerimg = designerimg;
	}
	




}


