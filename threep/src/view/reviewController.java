package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Message;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.AnchorPane;

import model.DBConnect;

import model.Review;

public class reviewController implements Initializable {
	@FXML
	private Label reviewid;
	@FXML
	private Label reviewcomment;
	@FXML
	private Label score;
	@FXML
	private Button reviewdelete;
    @FXML
    private PasswordField reviewpassword;
    @FXML
    private AnchorPane reviewbox;
	
    DBConnect connect = new DBConnect();
    Message msg =new Message();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
	
	@FXML
	public void btnreviewdelete(ActionEvent event) throws SQLException {
		conn = connect.getConnection();

    	String sql = "SELECT * FROM REVIEW WHERE reviewid =? and reviewpassword=?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, reviewid.getText());
    	pstmt.setString(2, reviewpassword.getText());
    	rs = pstmt.executeQuery();   			//Query는 결과값이 있는것, Update는 결과값이 없는것
    
    	if (rs.next()) {
    		conn = connect.getConnection();
        	String sql2 = "DELETE REVIEW WHERE reviewid = ?";
        	pstmt = conn.prepareStatement(sql2);
        	pstmt.setString(1, reviewid.getText());
        	rs = pstmt.executeQuery();
    		msg.setMessage("리뷰를 삭제 하였습니다.");

    	}
    	else {
    		msg.setMessage("비밀번호가 틀리셨습니다.");
    	}
	}
    
    public void setData(Review review) {

    	reviewid.setText(review.getReviewid());
		score.setText(review.getScore());
		reviewcomment.setText(review.getReviewcomment());
    	  
    	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
		
}

