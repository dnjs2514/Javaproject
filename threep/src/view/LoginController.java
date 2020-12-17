package view;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
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

public class LoginController {



    @FXML
    private JFXTextField id;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnLogin;

    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;



    @FXML
    void createLogin(ActionEvent event) throws SQLException, IOException {

//    	if(tfFname.getText().equals("")) {
//    		mgs.setMessage("�̸� ���Է�");
//    	}
    	//DB�� �̸��ϰ� ��й�ȣ�� Ȯ���Ͽ� �α��� ���� �Ǵ� �Ұ���
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=? AND shoppassword=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, id.getText());
    	pstmt.setString(2, password.getText());
    	System.out.println("�α��� Ȯ�� ");
    	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�

//    	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
    	if (rs.next()) {
    		//msg.setMessage("�α��μ���!");
    		//����â�� �ݴ´�.
    		btnLogin.getScene().getWindow().hide();
    		//���ο� Ȩ������ ȭ���� ����.
    	  	Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("Searchpage.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();


    	}
    	else {
    		msg.setMessage("�α��ν���!");
    	}

    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
    	//System.out.println("������������");

    	btnRegister.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("SignUP1.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();


    }

}


