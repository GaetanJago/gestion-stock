package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class InterfaceController implements Initializable{

	// LOGIN
	@FXML
	private Button connect;
	
	@FXML private TextField email;
	@FXML private TextField password;
	
	/**
	 * Change panel for a specific connection
	 * @param event
	 */
	@FXML
	public void switchConnect(ActionEvent event) {
		System.out.println("connection");
		try {
			URL url;
			if(email.getText().equalsIgnoreCase("admin") && 
					password.getText().equalsIgnoreCase("admin"))
				url = new File("src/view/AdminStock.fxml").toURI().toURL();
			else {
				url = new File("src/view/ManagerStock.fxml").toURI().toURL();
			}
			Parent root;
			root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			Stage stage = (Stage) connect.getScene().getWindow();
			stage.setScene(scene);
			
			stage.centerOnScreen();
			
			stage.show();
			//stage.setMaximized(true);		
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
