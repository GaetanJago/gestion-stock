package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Main;

/**
 * Load JAVAFX application window
 *
 */
public class Interface extends Application{
	
	/**
	 * Launch the application window
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Execute when exit application
	 */
	@Override
	public void stop(){
	    System.out.println("Stage is closing");  
	    
	    // close entity manager
	    Main.em.close();
	    Main.emf.close();
	}
}
