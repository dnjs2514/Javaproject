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
			msg.setMessage("빈칸을 채워주세요.");}
    	//DB로 입력내용을 저장 => Insert into
    	if(signup3check.isSelected()) {
    		//동의했을경우 => DB저장
    		conn = connect.getConnection();
    		String sql = "INSERT INTO Hairshop(shopid,shoppassword,shopname,shoptel,type) Values(?,?,?,?,2)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, signUP3id.getText());
    		pstmt.setString(2, signUP3password.getText());
    		pstmt.setString(3, signUP3name.getText());
    		pstmt.setString(4, signUP3tel.getText());


    		pstmt.executeUpdate(); //준비된 쿼리문을 DB에 접속해서 실행한다.
    		// 12/ 08 오류 8글자의 크기는 varcher2(8)보다크다!!!
    	//System.out.println("가입페이지로");

    	btnsignup3.getScene().getWindow().hide();// 현재페이지를 안보이게함

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		msg.setMessage("가입이 완료되었습니다.");}
		else {
    		msg.setMessage("동의하세요");

    	}
    }

    @FXML
    void createrbtnre3(ActionEvent event) throws SQLException{
    	
    	conn = connect.getConnection();
    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=?";
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	
    	pstmt.setString(1, signUP3id.getText());
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것
    
//    	boolean isValid = false; //로그인 가능 초기값을 false
    	if (rs.next()) {
    		msg.setMessage("중복된 아이디! 사용하실수없습니다.");

    	}
    	else if (signUP3id.getText().equals("")) {
    		msg.setMessage("아이디를 입력해주세요");
    	}
    	else {
    		msg.setMessage("사용하실수 있는 아이디입니다.");
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


