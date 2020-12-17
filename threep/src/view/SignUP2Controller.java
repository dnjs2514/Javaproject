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
			msg.setMessage("빈칸을 채워주세요.");}
			else
			{
			
    	//DB로 입력내용을 저장 => Insert into
    	if(signcheck.isSelected()) {
    		//동의했을경우 => DB저장
    		conn = connect.getConnection();
    		String sql = "INSERT INTO Hairshop(shopid,shoppassword,shopname,shoptel, shopaddress, type) Values(?,?,?,?,?,1)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, signid.getText());
    		pstmt.setString(2, signpassword.getText());
    		pstmt.setString(3, signname.getText());
    		pstmt.setString(4, signtel.getText());
    		pstmt.setString(5, signaddress.getText());

    		pstmt.executeUpdate(); //준비된 쿼리문을 DB에 접속해서 실행한다.
    		// 12/ 08 오류 8글자의 크기는 varcher2(8)보다크다!!!
    	//System.out.println("가입페이지로");
//    	  	String sql1 = "SELECT * FROM HAIRSHOP WHERE shopid=?";
//        	pstmt = conn.prepareStatement(sql1);
//        	pstmt.setString(1, signid.getText());
//        	
//        	
//        	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것
//        
////        	boolean isValid = false; //로그인 가능 초기값을 false
//        	if (rs.next()) {
//        		msg.setMessage("중복된 아이디! 사용하실수없습니다.");
//
//        	}
//        	else {
//        		msg.setMessage("사용하실수 있는 아이디입니다.");
//        	}	

    	btnsignup.getScene().getWindow().hide();// 현재페이지를 안보이게함

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
    }
    
    @FXML
    void createrbtnre(ActionEvent event) throws SQLException{
    	conn = connect.getConnection();

    	String sql = "SELECT * FROM HAIRSHOP WHERE shopid=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, signid.getText());
    	
    
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것
    
//    	boolean isValid = false; //로그인 가능 초기값을 false
    	if (rs.next()) {
    		msg.setMessage("중복된 아이디! 사용하실수없습니다.");

    	}
    	else if (signid.getText().equals("")) {
    		msg.setMessage("아이디를 입력해주세요");
    	}
    	else {
    		msg.setMessage("사용하실수 있는 아이디입니다.");
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
