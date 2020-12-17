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
import model.Dupdate;

public class DupdateController implements Initializable {

    @FXML
    private TextField designername;

    @FXML
    private TextField closed;

    @FXML
    private TextField shopname;

    @FXML
    private TextField designerimg;

    @FXML
    private TableView<Dupdate> tvdupdate;

    @FXML
    private TableColumn<Dupdate, String> tvdesignername;

    @FXML
    private TableColumn<Dupdate, String> tvclosed;

    @FXML
    private TableColumn<Dupdate, String> tvshopname;

    @FXML
    private TableColumn<Dupdate, String> tvdesignerimg;

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

    @FXML
    void handelButton(ActionEvent event) {
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

    			showDupdate();

    }
    public ObservableList<Dupdate> getUpdateList(){
		//fx���� ���̺�信 ǥ���ϱ� ���� ����Ʈ�� ObservableList ���
		ObservableList<Dupdate> dupdateList = FXCollections.observableArrayList();
		//sql �ۼ�
		String sql = "SELECT * FROM designer ORDER BY designername";
		conn = connect.getConnection(); //������ ���� DB���� �޼ҵ�
		Statement stmt;   //DB�� ���� ���� ��ü ����
		ResultSet rs;     //DB���� �޾ƿ��� �����ü ����
		
		try {
			stmt = conn.createStatement(); //���� ��ü ����
			rs = stmt.executeQuery(sql); // ���ӵ� DB���� ������ �����ϰ� ����� ����
			//����� �� �྿ �о bookList���� �Է�
			Dupdate dupdate; 
			while(rs.next()) {			
				dupdate = new Dupdate(rs.getString("designername"), rs.getString("closed"), rs.getString("shopname"), rs.getString("designerimg"));
				dupdateList.add(dupdate); //�ϸ���Ʈ�� �ϳ��� book��ü�� �Է��Ѵ�.
			}
		} catch (Exception e) {
			System.out.println("DB���� sql���� ����Ұ�: "+e);
		}
		
		return dupdateList;	
	}
	
	public void showDupdate() {
		ObservableList<Dupdate> list = getUpdateList();
		// ���̺�信 ����Ʈ�� �ְ�
		tvdupdate.setItems(list);
		// ������ ���� �����͸� �ҷ����� �ڵ带 �ۼ�
		tvdesignername.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("designername"));
		tvclosed.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("closed"));
		tvshopname.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("shopname"));
		tvdesignerimg.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("designerimg"));
		
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// ���α׷� ����� �Ʒ� ������ �����
		showDupdate();
		// ���콺�� ���̺��� Ŭ���� �� ���� ������ �ٸ��� �̺�Ʈ�� �߻��Ѵ�.
		tvdupdate.getSelectionModel().selectedItemProperty().addListener(
				//���̺���� ������ ���� �ϳ��� book��ü�̰� �ٸ� ���� ����(���콺Ŭ����)
				//���� ��� �ٸ���� �̺�Ʈ �߻��ϰ� showBookDetails�� �����Ѵ�.
				(obs,oldValue,newValue) -> showDupdateDetails(newValue)
				);	
	}
	
	private void showDupdateDetails(Dupdate dupdate) {
		if(dupdate != null) {
			//�ΰ��� �ƴҶ�
			tvdesignername.setText(dupdate.getDesignername());
			tvclosed.setText(dupdate.getClosed());
			tvshopname.setText(dupdate.getShopname());
			tvdesignerimg.setText(dupdate.getDesignerimg());

			
		} else {
			//�ΰ��϶� => ��� tf�� ������ �����.
			designername.setText("");
			closed.setText("");
			shopname.setText("");
			designerimg.setText("");
	
		}
		
	}

	// DB�� ���� �Է�
	private void insertRow() {
		String sql = "INSERT INTO designer VALUES(?,?,?,?)";
		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, designername.getText()); 					
			pstmt.setString(2, closed.getText()); 			
			pstmt.setString(3, shopname.getText()); 			
			pstmt.setString(4, designerimg.getText()); 				
		   //year

			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => �μ�Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("�μ�Ʈ �����߻�!");
		}
		designername.setText("");
		closed.setText("");
		shopname.setText("");
		designerimg.setText("");
	
	}
	
	// DB�� ���� �����Ѵ�.
	private void updateRow() {
		String sql = "UPDATE designer SET closed=?, shopname=?, designerimg=? WHERE designername=?";

		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, closed.getText()); 				
			pstmt.setString(2, shopname.getText()); 			
			pstmt.setString(3, designerimg.getText()); 				
			pstmt.setString(4, designername.getText()); 		
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ������Ʈ ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������Ʈ�� �����߻�!");
		}
	}
	
	private void deleteRow() {
		String sql = "DELETE FROM designer WHERE designername=?";
		conn = connect.getConnection();
		PreparedStatement pstmt;    //���� ��ü ����(pstmt�� ?��밡��)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, designername.getText());     //���̵�
			//�Է� �غ��
			pstmt.executeUpdate();  //���ϰ��� ������� ������Ʈ => ���� ����
			conn.commit();         //���� �Է��ϰ� commit�Ѵ�.
		} catch (Exception e) {
			System.out.println("������ �����߻�!");
		}
	}
	

}



