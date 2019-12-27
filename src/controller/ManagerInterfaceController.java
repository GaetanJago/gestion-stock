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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.RowStockManager;

public class ManagerInterfaceController implements Initializable {

	@FXML 
	private Button disconnect;
	@FXML ComboBox<String> filter;

	@FXML
	private TableView<RowStockManager> table;
	@FXML private TableColumn<RowStockManager, String> product;
	@FXML private TableColumn<RowStockManager, String> section;
	@FXML private TableColumn<RowStockManager, Integer> stock;
	@FXML private TableColumn<RowStockManager, Spinner<Integer>> modify;

	ObservableList<RowStockManager> listStockRowManager = FXCollections.observableArrayList();
	ObservableList<String> listFilter = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Samples
		listStockRowManager.add(new RowStockManager("lala", "lolo", 45, new Spinner<Integer>()));
		listStockRowManager.add(new RowStockManager("xperia", "€€", 96, new Spinner<Integer>()));
		listFilter.add("pantoufles");
		listFilter.add("peche");

		//table.getColumns().addAll(product,section,stock,modify);
		product.setCellValueFactory(new PropertyValueFactory<RowStockManager, String>("product"));
		section.setCellValueFactory(new PropertyValueFactory<RowStockManager, String>("section"));
		stock.setCellValueFactory(new PropertyValueFactory<RowStockManager, Integer>("stock"));
		modify.setCellValueFactory(new PropertyValueFactory<RowStockManager,  Spinner<Integer>>("modify"));
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
}
