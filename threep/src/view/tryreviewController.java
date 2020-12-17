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
    	
    
    	rs = pstmt.executeQuery();   			//Query�� ������� �ִ°�, Update�� ������� ���°�
    
//    	boolean isValid = false; //�α��� ���� �ʱⰪ�� false
    	if (rs.next()) {
    		msg.setMessage("�ߺ��� ���̵�! ����ϽǼ������ϴ�.");

    	}
    	else if (review.getText().equals("")) {
    		msg.setMessage("���̵� �Է����ּ���");
    	}
    	else {
    		msg.setMessage("����ϽǼ� �ִ� ���̵��Դϴ�.");
    	}
    	
    	

    }

    @FXML
    void createrbtnreview(ActionEvent event) throws SQLException, IOException {
    	if ((reviewid.getText().equals("")) || reviewpassword.getText().equals("") || designername.getText().equals("")
    			|| score.getText().equals("")) {
			msg.setMessage("��ĭ�� ä���ּ���.");}
			else
			{
			
    	if(reviewcheck.isSelected()) {
    		//����������� => DB����
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
    		btnreview.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

        	Stage signup = new Stage();

        	Parent root = FXMLLoader.load(getClass().getResource("reviewlist.fxml"));

    		Scene scene = new Scene(root);
    		signup.setScene(scene);
    		signup.show();
    		msg.setMessage("���䰡 ��ϵǾ����ϴ�.");
    		
    		}
    		else if(Integer.parseInt(score.getText()) > 10 && Integer.parseInt(score.getText()) < 0){
        		msg.setMessage("���������� 10���� 0�� ���̿��ߵ˴ϴ�.");
        		}
    		
    	
    	}
				
    		
    	else {
        		msg.setMessage("üũ���� Ȯ���ϼ���.");
        		
        		}
			}
    }
}

    
    


