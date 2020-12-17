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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBConnect;

public class tryreviewController {

    @FXML
    private JFXTextField reviewid;

    @FXML
    private JFXButton btnrereview;

    @FXML
    private JFXPasswordField reviewpassword;

    @FXML
    private JFXTextField designername;

    @FXML
    private TextField score;

    @FXML
    private TextArea review;

    @FXML
    private JFXCheckBox reviewcheck;

    @FXML
    private JFXButton btnreview;
    
    DBConnect connect = new DBConnect();
    Message msg =new Message();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    
    

    @FXML
    void createrbtnrereview(ActionEvent event) throws SQLException {
    	
    	conn = connect.getConnection();
    	String sql = "SELECT * FROM review WHERE reviewid=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, reviewid.getText());
    	
    
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것
    
//    	boolean isValid = false; //로그인 가능 초기값을 false
    	if (rs.next()) {
    		msg.setMessage("중복된 아이디! 사용하실수없습니다.");

    	}
    	else if (review.getText().equals("")) {
    		msg.setMessage("아이디를 입력해주세요");
    	}
    	else {
    		msg.setMessage("사용하실수 있는 아이디입니다.");
    	}
    	
    	

    }

    @FXML
    void createrbtnreview(ActionEvent event) throws SQLException, IOException {
    	if ((reviewid.getText().equals("")) || reviewpassword.getText().equals("") || designername.getText().equals("")
    			|| score.getText().equals("")) {
			msg.setMessage("빈칸을 채워주세요.");}
			else
			{
			
    	if(reviewcheck.isSelected()) {
    		//동의했을경우 => DB저장
    		if (Integer.parseInt(score.getText()) <= 10 && Integer.parseInt(score.getText()) >= 0 ) {
    			
    		conn = connect.getConnection();
    		String sql = "INSERT INTO review(reviewid,reviewpassword,designername,score, reviewcomment) Values(?,?,?,?,?)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);

    		pstmt.setString(1, reviewid.getText());
    		pstmt.setString(2, reviewpassword.getText());
    		pstmt.setString(3, designername.getText());
    		pstmt.setInt(4, Integer.parseInt(score.getText()));
    		pstmt.setString(5, review.getText());
    		
    		pstmt.executeUpdate();
    		btnreview.getScene().getWindow().hide();// 현재페이지를 안보이게함

        	Stage signup = new Stage();

        	Parent root = FXMLLoader.load(getClass().getResource("reviewlist.fxml"));

    		Scene scene = new Scene(root);
    		signup.setScene(scene);
    		signup.show();
    		msg.setMessage("리뷰가 등록되었습니다.");
    		
    		}
    		else if(Integer.parseInt(score.getText()) > 10 && Integer.parseInt(score.getText()) < 0){
        		msg.setMessage("리뷰점수는 10점과 0점 사이여야됩니다.");
        		}
    		
    	
    	}
				
    		
    	else {
        		msg.setMessage("체크란을 확인하세요.");
        		
        		}
			}
    }
}

    
    


