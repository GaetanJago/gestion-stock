package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Main;

/**
 * Load JAVAFX application window
 *
 */
public class DBError extends Application{

	/**
	 * Launch the error application window
	 */
	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Stock Management");
			primaryStage.setScene(scene);	
			primaryStage.setMinWidth(450);
			primaryStage.setMinHeight(300);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("file:images/icon.jpg"));
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Base de données introuvable");
			alert.setContentText("Veuillez vérifier les accès à la base de données");
			Stage alStage = (Stage) alert.getDialogPane().getScene().getWindow();
			alStage.getIcons().add(new Image("file:images/icon.jpg"));
			alert.showAndWait();
			
			Platform.exit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
