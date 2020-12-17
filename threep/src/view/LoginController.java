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
//    		mgs.setMessage("이름 미입력");
//    	}
    	//DB에 이메일과 비밀번호를 확인하여 로그인 가능 또는 불가능
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=? AND shoppassword=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, id.getText());
    	pstmt.setString(2, password.getText());
    	System.out.println("로그인 확인 ");
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것

//    	boolean isValid = false; //로그인 가능 초기값을 false
    	if (rs.next()) {
    		//msg.setMessage("로그인성공!");
    		//현재창을 닫는다.
    		btnLogin.getScene().getWindow().hide();
    		//새로운 홈페이지 화면을 띄운다.
    	  	Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("Searchpage.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();


    	}
    	else {
    		msg.setMessage("로그인실패!");
    	}

    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
    	//System.out.println("가입페이지로");

    	btnRegister.getScene().getWindow().hide();// 현재페이지를 안보이게함

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("SignUP1.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();


    }

}


