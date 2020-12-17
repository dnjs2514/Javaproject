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

public class SignUP3Controller {

    @FXML
    private JFXButton btnre3;

    @FXML
    private JFXTextField signUP3id;

    @FXML
    private JFXTextField signUP3name;

    @FXML
    private JFXTextField signUP3tel;

    @FXML
    private JFXPasswordField signUP3password;

    @FXML
    private JFXButton btnsignup3;

    @FXML
    private JFXButton btnsignup3reset;

    @FXML
    private JFXCheckBox signup3check;
  
    @FXML
    private JFXButton btnrelogin2;

    DBConnect connect = new DBConnect();
    Message msg =new Message();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void createrbtnsignup3(ActionEvent event) throws IOException, SQLException{
    	if ((signUP3id.getText().equals("")) || signUP3password.getText().equals("") || signUP3name.getText().equals("")
    			|| signUP3tel.getText().equals("")) {
			msg.setMessage("��ĭ�� ä���ּ���.");}
    	//DB�� �Է³����� ���� => Insert into
    	if(signup3check.isSelected()) {
    		//����������� => DB����
    		conn = connect.getConnection();
    		String sql = "INSERT INTO Hairshop(shopid,shoppassword,shopname,shoptel,type) Values(?,?,?,?,2)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, signUP3id.getText());
    		pstmt.setString(2, signUP3password.getText());
    		pstmt.setString(3, signUP3name.getText());
    		pstmt.setString(4, signUP3tel.getText());


    		pstmt.executeUpdate(); //�غ�� �������� DB�� �����ؼ� �����Ѵ�.
    		// 12/ 08 ���� 8������ ũ��� varcher2(8)����ũ��!!!
    	//System.out.println("������������");

    	btnsignup3.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

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

    @FXML
    void createrbtnre3(ActionEvent event) throws SQLException{
    	
    	conn = connect.getConnection();
    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=?";
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	
    	pstmt.setString(1, signUP3id.getText());
    	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�
    
//    	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
    	if (rs.next()) {
    		msg.setMessage("�ߺ��� ���̵�! ����ϽǼ������ϴ�.");

    	}
    	else if (signUP3id.getText().equals("")) {
    		msg.setMessage("���̵� �Է����ּ���");
    	}
    	else {
    		msg.setMessage("����ϽǼ� �ִ� ���̵��Դϴ�.");
    	}
    }

    @FXML
    void createrbtnsignup3reset(ActionEvent event) {
    	signUP3id.clear();
    	signUP3password.clear();
    	signUP3name.clear();
    	signUP3tel.clear();
    }
    
    @FXML
    void createrbtnrelogin2(ActionEvent event) throws IOException{
    	btnrelogin2.getScene().getWindow().hide();
    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
   
   }
}


