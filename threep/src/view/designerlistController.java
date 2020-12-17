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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBConnect;
import model.Designer;


public class designerlistController {

    @FXML
    private JFXTextField designerfield;

    @FXML
    private JFXButton designerbtn;
    @FXML
    private Button dupdate;

    @FXML
    private ScrollPane dscroll;

    @FXML
    private VBox designerbox;

    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    
    
    @FXML
    void btndesigner(ActionEvent event) {
    	designerfield.setText(designerfield.getText());
    	designerbox.getChildren().clear();
		 List<Designer> list;
  	try {
			list = designers();

			for(int i=0; i<list.size(); i++) {
			   	 FXMLLoader fxmlLoader = new FXMLLoader();
			   	 fxmlLoader.setLocation(getClass().getResource("designer.fxml"));

			   	 try {
					HBox hbox = fxmlLoader.load();
					designerController cic = fxmlLoader.getController();
				    cic.setData(list.get(i));

				    designerbox.getChildren().add(hbox);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		} catch (SQLException e) {

			e.printStackTrace();
		}

  }
    private List<Designer> designers() throws SQLException {
		 List<Designer> ls = new ArrayList<>();

			String sql = "SELECT shopname, designername, closed, designerimg FROM designer where  shopname like '%"
			+designerfield.getText()+"%'";

		 	conn = connect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Searchfield.getText());
			rs = pstmt.executeQuery();

			
			Designer designer ;
			try {
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					designer = new Designer( rs.getString("shopname"), rs.getString("designername"), rs.getString("closed"), rs.getString("designerimg"));
					ls.add(designer);
				}
			} catch (Exception e) {
				System.out.println("sql값을 저장할수 없습니다.");
			}
			return ls;
	 	}

	public void initialize(URL location, ResourceBundle resources) {
	


	
	}
	   @FXML
	    void btndupdate(ActionEvent event) throws IOException {
		

	    	Stage signup = new Stage();

	    	Parent root = FXMLLoader.load(getClass().getResource("Dupdatepage.fxml"));

			Scene scene = new Scene(root);
			signup.setScene(scene);
			signup.show();

	    }


    }




