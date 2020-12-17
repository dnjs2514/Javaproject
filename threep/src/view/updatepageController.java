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

public class updatepageController {

    @FXML
    private JFXTextField updateid;

    @FXML
    private JFXPasswordField updatepassword;

    @FXML
    private JFXButton btnupdateLogin;
    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void createupdateLogin(ActionEvent event) throws SQLException, IOException {
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=? AND shoppassword=? and type = 3";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, updateid.getText());
    	pstmt.setString(2, updatepassword.getText());
    	System.out.println("로그인 확인 ");
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것

//    	boolean isValid = false; //로그인 가능 초기값을 false
    	if (rs.next()) {
    		//msg.setMessage("로그인성공!");
    		//현재창을 닫는다.
    		btnupdateLogin.getScene().getWindow().hide();
    		//새로운 홈페이지 화면을 띄운다.
    	  	Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("update.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();


    	}
    	else {
    		msg.setMessage("로그인실패! 관리자만 로그인가능합니다.");
    	}


    }

}
