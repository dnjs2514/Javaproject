package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

public class SignUP1Controller {
	@FXML
	private JFXButton btnD;
	@FXML
	private JFXButton btnC;




    @FXML
    void createrbtnD(ActionEvent event) throws IOException {
    	System.out.println("������������");

    	btnD.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("SignUP2.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();


    }

    @FXML
    void createrbtnC(ActionEvent event) throws IOException  {
    	//System.out.println("������������");

    	btnC.getScene().getWindow().hide();// ������������ �Ⱥ��̰���

    	Stage signup = new Stage();

    	Parent root = FXMLLoader.load(getClass().getResource("SignUP3.fxml"));

		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();


    }


}
