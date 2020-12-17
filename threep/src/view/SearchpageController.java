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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBConnect;
import model.Hairshop;


public class SearchpageController implements Initializable {

    @FXML
    private JFXTextField Searchfield;

    @FXML
    private JFXButton searchbtn;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox hairbox;
    @FXML
    private Button updateshop;


    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void btnsearch(ActionEvent event) throws IOException {
    	
    	hairbox.getChildren().clear();
    	
    	Searchfield.setText(Searchfield.getText());
		 List<Hairshop> list;
   	try {
			list = hairshops();

			for(int i=0; i<list.size(); i++) {
			   	 FXMLLoader fxmlLoader = new FXMLLoader();
			   	 fxmlLoader.setLocation(getClass().getResource("hairshop.fxml"));

			   	 try {
					HBox hbox = fxmlLoader.load();
					hairshopController cic = fxmlLoader.getController();
				    cic.setData(list.get(i));

				    hairbox.getChildren().add(hbox);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		} catch (SQLException e) {

			e.printStackTrace();
		}

   }





	private List<Hairshop> hairshops() throws SQLException{
		 List<Hairshop> ls = new ArrayList<>();

			String sql = "SELECT shopname, shoptel, shopaddress, img FROM Hairshop where type = 1 AND shopaddress like '%"
			+Searchfield.getText()+"%'";

		 	conn = connect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Searchfield.getText());
			rs = pstmt.executeQuery();

			
			Hairshop shop ;
			try {
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					shop = new Hairshop( rs.getString("shopname"), rs.getString("shoptel"), rs.getString("shopaddress"), rs.getString("img"));
					ls.add(shop);
				}
			} catch (Exception e) {
				System.out.println("sql값을 저장할수 없습니다.");
			}
			return ls;
	 	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	


	
	}
	 @FXML
	    void btnupdateshop(ActionEvent event) throws IOException {
	    	

	    	Stage signup = new Stage();

	    	Parent root = FXMLLoader.load(getClass().getResource("updatepage.fxml"));

			Scene scene = new Scene(root);
			signup.setScene(scene);
			signup.show();


	    }

	


}
