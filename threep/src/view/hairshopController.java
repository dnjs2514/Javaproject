package view;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import model.Hairshop;

public class hairshopController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img;

    @FXML
    private Label shopname;

    @FXML
    private Label shoptel;

    @FXML
    private Label shopaddress;

    @FXML
    void createrhairbox(MouseEvent event) throws IOException {
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("designerlist.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    }

	 public void setData(Hairshop hairshop) {

		 	Image shopImage = new Image(getClass().getResourceAsStream(hairshop.getImg()));

	        img.setImage(shopImage);
	        shopname.setText(hairshop.getshopName());
	        shoptel.setText(hairshop.getshopTel());
	        shopaddress.setText(hairshop.getshopAddress());
	  
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}
