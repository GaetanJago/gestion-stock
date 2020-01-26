package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Leader;
import model.Manager;
import model.User;

/**
 * Class to control the login page
 *
 */
public class InterfaceController implements Initializable{

	// LOGIN
	@FXML
	private Button connect;

	@FXML private TextField login;
	@FXML private TextField password;

	@FXML public void enterPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			switchConnect(null);
	}
	
	/**
	 * Change panel for a specific connection
	 * @param event
	 */
	@FXML
	public void switchConnect(ActionEvent event) {
		System.out.println("connection");
		try {

			URL url;
			Parent root;

			// Look for a user in DB
			FXMLLoader fxmlLoader ;  
			Boolean match = false;
			UserDAO uD = new UserDAO(Main.em);
			ManagerDAO mD = new ManagerDAO(Main.em);
			LeaderDAO lD = new LeaderDAO(Main.em);
			List<User> userList = uD.getAll();
			
			for(User u : userList) {
				if(u.getLogin().equals(login.getText())) {
					if(u.getPassword().equals(password.getText())) {
						match = true;
						
						//Check role : if its an admin, load an admin page
						if(u.getRole() != null && u.getRole().getName().equalsIgnoreCase("admin")) {
							url = new File("src/view/AdminStock.fxml").toURI().toURL();
							fxmlLoader = new FXMLLoader(url);
							root = fxmlLoader.load();
							Leader l = lD.findById(u.getId());
							AdminStockController controller = fxmlLoader.getController();
							controller.setLeader(l);
						}
						else {
							url = new File("src/view/ManagerStock.fxml").toURI().toURL();
							Manager m = mD.findById(u.getId());
							fxmlLoader = new FXMLLoader(url);
							root = fxmlLoader.load();
							ManagerInterfaceController controller = fxmlLoader.getController();
							controller.setManager(m);
						}
						
						//Change the page
						Scene scene = new Scene(root);
						Stage stage = (Stage) connect.getScene().getWindow();
						stage.setScene(scene);

						stage.centerOnScreen();

						stage.show();
						break;
					}
				}
			}
			//Display error dialog
			if(match == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur de connexion");
				alert.setContentText("Login ou mot de passe incorect");
				Stage alStage = (Stage) alert.getDialogPane().getScene().getWindow();
				alStage.getIcons().add(new Image("file:images/icon.jpg"));
				alert.showAndWait();
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
