package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.result.Row;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.ButtonIdentifiable;
import view.RowManagementAdmin;

public class AdminManagementController implements Initializable{
	@FXML 
	private Button disconnect;

	@FXML
	private TableView<RowManagementAdmin> table;
	@FXML private TableColumn<RowManagementAdmin, String> userName;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<String>> role;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<String>> section;
	@FXML private TableColumn<RowManagementAdmin, ButtonIdentifiable> action;

	ObservableList<RowManagementAdmin> listUser = FXCollections.observableArrayList();
	ObservableList<String> listSection = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Samples
		ComboBox<String> allSections = new ComboBox<String>();	
		listSection.add("pantoufles");
		listSection.add("peche");
		//allSections.setValue("pantoufles");
		allSections.setItems(listSection);
		ButtonIdentifiable b = new ButtonIdentifiable("Supprimer",0);
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
		    	deleteRow(((ButtonIdentifiable)e.getSource()).getIdButton());
		    }
		});
		ButtonIdentifiable a = new ButtonIdentifiable("Supprimer",1);
		a.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
		    	table.getItems().remove(((ButtonIdentifiable)e.getSource()).getIdButton());
		    }
		});
		listUser.add(new RowManagementAdmin("popole", null,  null,b));
		listUser.add(new RowManagementAdmin("wakfu", new ComboBox<String>(),  allSections,a));
		

		userName.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, String>("userName"));
		role.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, ComboBox<String>>("role"));
		section.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, ComboBox<String>>("section"));
		action.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin,  ButtonIdentifiable>("action"));
		table.setItems(listUser);
	}

	public void deleteRow(int index) {
		table.getItems().remove(index);
		for(RowManagementAdmin row : listUser) {
			if(row.getAction().getIdButton() > index) {
				row.getAction().setIdButton(row.getAction().getIdButton() - 1);
			}
		}
	}

	/**
	 * Go back to login page
	 * @param event
	 */
	@FXML
	public void switchDisconnect(ActionEvent event) {
		System.out.println("disconnect");
		try {
			URL url = new File("src/view/Login.fxml").toURI().toURL();
			Parent root;
			root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			Stage stage = (Stage) disconnect.getScene().getWindow();

			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
			//stage.setMaximized(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Go to stock page
	 * @param event
	 */
	@FXML
	public void switchStock(ActionEvent event) {
		System.out.println("disconnect");
		try {
			URL url = new File("src/view/AdminStock.fxml").toURI().toURL();
			Parent root;
			root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			Stage stage = (Stage) disconnect.getScene().getWindow();

			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
			//stage.setMaximized(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

