package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Designer;

public class designerController implements Initializable{

    @FXML
    private HBox designerbox;

    @FXML
    private ImageView designerimg;

    @FXML
    private Label shopname;

    @FXML
    private Label designername;

    @FXML
    private Label designerclose;

    @FXML
    private Label reviewscore;

    @FXML
    void createrdesignerbox(MouseEvent event) throws IOException {

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("reviewlist.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
	
	}
    
    public void setData(Designer designer) {

	 	
		Image designerImage = new Image(getClass().getResourceAsStream(designer.getDesignerimg()));
		
	 	designerimg.setImage(designerImage);
        shopname.setText(designer.getShopname());
        designername.setText(designer.getDesignername());
        designerclose.setText(designer.getClosed());
  
 }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


}

}

