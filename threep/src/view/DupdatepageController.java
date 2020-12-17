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

public class DupdatepageController {

    @FXML
    private JFXTextField Dupdateid;

    @FXML
    private JFXPasswordField Dupdatepassword;

    @FXML
    private JFXButton btnDupdateLogin;

    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    @FXML
    void createDupdateLogin(ActionEvent event) throws SQLException, IOException {
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=? AND shoppassword=? and (type = 3 or TYPE = 1) ";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, Dupdateid.getText());
    	pstmt.setString(2, Dupdatepassword.getText());
    	System.out.println("�α��� Ȯ�� ");
    	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�

//    	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
    	if (rs.next()) {
    		//msg.setMessage("�α��μ���!");
    		//����â�� �ݴ´�.
    		btnDupdateLogin.getScene().getWindow().hide();
    		//���ο� Ȩ������ ȭ���� ����.
    	  	Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("Dupdate.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();


    	}
    	else {
    		msg.setMessage("�α��ν���! �����ڸ� �α��ΰ����մϴ�.");
    	}


    }


    }


