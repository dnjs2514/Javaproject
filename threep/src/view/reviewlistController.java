package view;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBConnect;

import model.Review;

public class reviewlistController {

    @FXML
    private JFXTextField reviewfield;

    @FXML
    private JFXButton reviewbtn;

    @FXML
    private Button tryreview;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox reviewbox;
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    

    @FXML
    void btnreview(ActionEvent event) {
    	reviewfield.setText(reviewfield.getText());
    	reviewbox.getChildren().clear();
		 List<Review> list;
 	try {
			list = reviews();

			for(int i=0; i<list.size(); i++) {
			   	 FXMLLoader fxmlLoader = new FXMLLoader();
			   	 fxmlLoader.setLocation(getClass().getResource("review.fxml"));

			   	 try {
					AnchorPane anchorPane = fxmlLoader.load();
					reviewController cic = fxmlLoader.getController();
				    cic.setData(list.get(i));

				    reviewbox.getChildren().add(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		} catch (SQLException e) {

			e.printStackTrace();
		}

    }
    private List<Review> reviews() throws SQLException {
		 List<Review> ls = new ArrayList<>();

			String sql = "SELECT reviewid, score, reviewcomment FROM review where  designername = '"
			+reviewfield.getText()+"'";

		 	conn = connect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Searchfield.getText());
			rs = pstmt.executeQuery();

			
			Review review ;
			try {
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				System.out.println("여기까지 실행완료했습니다.");
				while(rs.next()) {
				
					review = new Review( rs.getString("reviewid"), rs.getString("score"), rs.getString("reviewcomment"));
					ls.add(review);
				}
			} catch (Exception e) {
				System.out.println("sql값을 저장할수 없습니다.");
			}
			return ls;
	 	}

	public void initialize(URL location, ResourceBundle resources) {
	


	
	}

    @FXML
    void btntryreview(ActionEvent event) throws IOException {
    	tryreview.getScene().getWindow().hide();// 현재페이지를 안보이게함
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("tryreview.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();

    	

    }

}
