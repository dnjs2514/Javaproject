package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DBConnect;
import model.Update;

public class updateController  implements Initializable{

    @FXML
    private TextField shopid;

    @FXML
    private TextField shoppassword;

    @FXML
    private TextField shopname;

    @FXML
    private TextField shoptel;

    @FXML
    private TextField shopaddress;

    @FXML
    private TextField img;

    @FXML
    private TextField type;

    @FXML
    private TableView<Update> tvupdate;

    @FXML
    private TableColumn<Update, String> tvshopid;

    @FXML
    private TableColumn<Update, String> tvshoppassword;

    @FXML
    private TableColumn<Update, String> tvshopname;

    @FXML
    private TableColumn<Update, String> tvshoptel;

    @FXML
    private TableColumn<Update, String> tvshopaddress;

    @FXML
    private TableColumn<Update, String> tvimg;
    
    @FXML
    private TableColumn<Update, Integer> tvtype;
    
    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    

    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

	// ��ư�� �������� ( 3�� �μ�Ʈ, ������Ʈ, ������Ʈ )
	@FXML
	public void handelButton(ActionEvent event) {
		//System.out.println("��ư�� ������!");
		//showBooks();
		if(event.getSource() == btnInsert) { //btnInsert ��ư�� ��������!!!
			insertRow();
		}
		else if(event.getSource() == btnUpdate) {
			updateRow();
		}
		else if(event.getSource() == btnDelete) {
			deleteRow();
		}

		showUpdate();		
	}
	
	
	
	// 2. books���̺��� ��� ������ �޾ƿ´�. �̶� ���̺�信 �Է��ϱ� ���ؼ� observablelist�� ���
	public ObservableList<Update> getUpdateList(){
		//fx���� ���̺�信 ǥ���ϱ� ���� ����Ʈ�� ObservableList ���
		ObservableList<Update> updateList = FXCollections.observableArrayList();
		//sql �ۼ�
		String sql = "SELECT * FROM hairshop ORDER BY shopid";
		conn = connect.getConnection(); //������ ���� DB���� �޼ҵ�
		Statement stmt;   //DB�� ���� ���� ��ü ����
		ResultSet rs;     //DB���� �޾ƿ��� �����ü ����
		
		try {
			stmt = conn.createStatement(); //���� ��ü ����
			rs = stmt.executeQuery(sql); // ���ӵ� DB���� ������ �����ϰ� ����� ����
			//����� �� �྿ �о bookList���� �Է�
			Update update; 
			while(rs.next()) {			
				update = new Update(rs.getString("shopid"), rs.getString("shoppassword"), rs.getString("shopname")
						, rs.getString("shoptel"), rs.getString("shopaddress"), rs.getString("img"),rs.getInt("type"));
				updateList.add(update); //�ϸ���Ʈ�� �ϳ��� book��ü�� �Է��Ѵ�.
			}
		} catch (Exception e) {
			System.out.println("DB���� sql���� ����Ұ�: "+e);
		}
		
		return updateList;	
	}
	
	public void showUpdate() {
		ObservableList<Update> list = getUpdateList();
		// ���̺�信 ����Ʈ�� �ְ�
		tvupdate.setItems(list);
		// ������ ���� �����͸� �ҷ����� �ڵ带 �ۼ�
		tvshopid.setCellValueFactory(new PropertyValueFactory<Update, String>("shopid"));
		tvshoppassword.setCellValueFactory(new PropertyValueFactory<Update, String>("shoppassword"));
		tvshopname.setCellValueFactory(new PropertyValueFactory<Update, String>("shopname"));
		tvshoptel.setCellValueFactory(new PropertyValueFactory<Update, String>("shoptel"));
		tvshopaddress.setCellValueFactory(new PropertyValueFactory<Update, String>("shopaddress"));
		tvimg.setCellValueFactory(new PropertyValueFactory<Update, String>("img"));
		tvtype.setCellValueFactory(new PropertyValueFactory<Update, Integer>("type"));
		
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// ���α׷� ����� �Ʒ� ������ �����
		showUpdate();
		// ���콺�� ���̺��� Ŭ���� �� ���� ������ �ٸ��� �̺�Ʈ�� �߻��Ѵ�.
		tvupdate.getSelectionModel().selectedItemProperty().addListener(
				//���̺���� ������ ���� �ϳ��� book��ü�̰� �ٸ� ���� ����(���콺Ŭ����)
				//���� ��� �ٸ���� �̺�Ʈ �߻��ϰ� showBookDetails�� �����Ѵ�.
				(obs,oldValue,newValue) -> showUpdateDetails(newValue)
				);	
	}
	
	private void showUpdateDetails(Update update) {
		if(update != null) {
			//�ΰ��� �ƴҶ�
			tvshopid.setText(update.getShopid());
			tvshoppassword.setText(update.getShoppassword());
			tvshopname.setText(update.getShopname());
			tvshoptel.setText(update.getShoptel());
			tvshopaddress.setText(update.getShopaddress());
			tvimg.setText(update.getImg());
			tvtype.setText(Integer.toString(update.getType()));
			
		} else {
			//�ΰ��϶� => ��� tf�� ������ �����.
			shopid.setText("");
			shoppassword.setText("");
			shopname.setText("");
			shoptel.setText("");
			shopaddress.setText("");
			img.setText("");
			type.setText("");
		}
		
	}

	// DB�� ���� �Է�
	private void insertRow() {
		String sql = "INSERT INTO hairshop VALUES(?,?,?,?,?,?,?)";
		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, shopid.getText()); 					//title
			pstmt.setString(2, shoppassword.getText()); 					//title
			pstmt.setString(3, shopname.getText()); 				//author
			pstmt.setString(4, shoptel.getText()); 				//author
			pstmt.setString(5, shopaddress.getText()); 				//author
			pstmt.setString(6, img.getText()); 				//author
			pstmt.setInt(7, Integer.parseInt(type.getText()) );   //year

			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => �μ�Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("�μ�Ʈ �����߻�!");
		}
		shopid.setText("");
		shoppassword.setText("");
		shopname.setText("");
		shoptel.setText("");
		shopaddress.setText("");
		img.setText("");
		type.setText("");
	}
	
	// DB�� ���� �����Ѵ�.
	private void updateRow() {
		String sql = "UPDATE hairshop SET shoppassword=?, shopname=?, shoptel=? , shopaddress =?, img=?, type=? WHERE shopid=?";

		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoppassword.getText()); 					//title
			pstmt.setString(2, shopname.getText()); 				//author
			pstmt.setString(3, shoptel.getText()); 				//author
			pstmt.setString(4, shopaddress.getText()); 				//author
			pstmt.setString(5, img.getText());
			pstmt.setInt(6, Integer.parseInt(type.getText()) );  //author
			pstmt.setString(7, shopid.getText()); 			
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ������Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������Ʈ�� �����߻�!");
		}
	}
	
	private void deleteRow() {
		String sql = "DELETE FROM hairshop WHERE shopid=?";
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shopid.getText());     //���̵�
			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ���� ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������ �����߻�!");
		}
	}
	

}



	
	
	
	
	

