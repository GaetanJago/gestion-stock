package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.RowStockAdmin;

public class AdminStockController implements Initializable{
	@FXML 
	private Button disconnect;
	@FXML ComboBox<String> filter;

	@FXML
	private TableView<RowStockAdmin> table;
	@FXML private TableColumn<RowStockAdmin, TextField> product;
	@FXML private TableColumn<RowStockAdmin, TextField> section;
	@FXML private TableColumn<RowStockAdmin, Integer> stock;
	@FXML private TableColumn<RowStockAdmin, Spinner<Integer>> modify;

	ObservableList<RowStockAdmin> listStockRowManager = FXCollections.observableArrayList();
	ObservableList<String> listFilter = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Samples
		listStockRowManager.add(new RowStockAdmin(new TextField("macroudz"), new TextField("craquotot"), 45, new Spinner<Integer>()));
		listStockRowManager.add(new RowStockAdmin(new TextField("zaricot"), new TextField("magique"), 45, new Spinner<Integer>()));
		listFilter.add("pantoufles");
		listFilter.add("peche");

		//table.getColumns().addAll(product,section,stock,modify);
		product.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, TextField>("product"));
		section.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, TextField>("section"));
		stock.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, Integer>("stock"));
		modify.setCellValueFactory(new PropertyValueFactory<RowStockAdmin,  Spinner<Integer>>("modify"));
		table.setItems(listStockRowManager);
		filter.setItems(listFilter);
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
	 * Go to Management page
	 * @param event
	 */
	@FXML
	public void switchManagement(ActionEvent event) {
		System.out.println("disconnect");
		try {
			URL url = new File("src/view/AdminManagement.fxml").toURI().toURL();
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
