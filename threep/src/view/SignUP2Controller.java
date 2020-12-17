package view;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DBConnect;

public class SignUP2Controller {



    @FXML
    private JFXButton btnre;

    @FXML
    private JFXTextField signid;

    @FXML
    private JFXTextField signname;

    @FXML
    private JFXTextField signtel;

    @FXML
    private JFXPasswordField signpassword;

    @FXML
    private JFXButton btnsignup;

    @FXML
    private JFXButton btnreset;

    @FXML
    private JFXCheckBox signcheck;

    @FXML
    private JFXTextField signaddress;
    @FXML
    private JFXButton btnrelogin;

    DBConnect connect = new DBConnect();
    Message msg =new Message();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void createrbtnsignup(ActionEvent event) throws IOException, SQLException{
    	if ((signid.getText().equals("")) || signpassword.getText().equals("") || signname.getText().equals("")
    			|| signtel.getText().equals("") || signaddress.getText().equals("")) {
			msg.setMessage("��ĭ�� ä���ּ���.");}
			else
			{
			
    	//DB�� �Է³����� ���� => Insert into
    	if(signcheck.isSelected()) {
    		//����������� => DB����
    		conn = connect.getConnection();
    		String sql = "INSERT INTO Hairshop(shopid,shoppassword,shopname,shoptel, shopaddress, type) Values(?,?,?,?,?,1)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, signid.getText());
    		pstmt.setString(2, signpassword.getText());
    		pstmt.setString(3, signname.getText());
    		pstmt.setString(4, signtel.getText());
    		pstmt.setString(5, signaddress.getText());

    		pstmt.executeUpdate(); //�غ�� �������� DB�� �����ؼ� �����Ѵ�.
    		// 12/ 08 ���� 8������ ũ��� varcher2(8)����ũ��!!!
    	//System.out.println("������������");
//    	  	String sql1 = "SELECT * FROM HAIRSHOP WHERE shopid=?";
//        	pstmt = conn.prepareStatement(sql1);
//        	pstmt.setString(1, signid.getText());
//        	
//        	
//        	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�
//        
////        	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
//        	if (rs.next()) {
//        		msg.setMessage("�ߺ��� ���̵�! ����ϽǼ������ϴ�.");
//
//        	}
//        	else {
//        		msg.setMessage("����ϽǼ� �ִ� ���̵��Դϴ�.");
//        	}	

    	btnsignup.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		msg.setMessage("������ �Ϸ�Ǿ����ϴ�.");}
		else {
    		msg.setMessage("�����ϼ���");
    		
    	}
			}
    }
    
    @FXML
    void createrbtnre(ActionEvent event) throws SQLException{
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, signid.getText());
    	
    
    	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�
    
//    	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
    	if (rs.next()) {
    		msg.setMessage("�ߺ��� ���̵�! ����ϽǼ������ϴ�.");

    	}
    	else if (signid.getText().equals("")) {
    		msg.setMessage("���̵� �Է����ּ���");
    	}
    	else {
    		msg.setMessage("����ϽǼ� �ִ� ���̵��Դϴ�.");
    	}
    }

    @FXML
    void createrbtnreset(ActionEvent event) {
    	signid.clear();
    	signpassword.clear();
    	signname.clear();
    	signtel.clear();
    	signaddress.clear();
    }
    @FXML
    void createrbtnrelogin(ActionEvent event) throws IOException{
    	btnsignup.getScene().getWindow().hide();
    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    	
  
		
   }

}
