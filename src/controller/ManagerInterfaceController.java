package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Article;
import model.Manager;
import model.Section;
import view.RowStockAdmin;
import view.RowStockManager;

/*
 * Class to control manager page
 */
public class ManagerInterfaceController implements Initializable {
/*
	//You may need this also if you're getting null
	@FXML private void initialize() {

		Platform.runLater(() -> {

			//do stuff

		});

	}*/

	@FXML
	private Label login;

	@FXML private Button disconnect;
	@FXML private Button save;
	@FXML private ComboBox<String> filter;

	@FXML
	private TableView<RowStockManager> table;
	@FXML private TableColumn<RowStockManager, String> product;
	@FXML private TableColumn<RowStockManager, String> section;
	@FXML private TableColumn<RowStockManager, Integer> stock;
	@FXML private TableColumn<RowStockManager, Spinner<Integer>> modify;

	ObservableList<RowStockManager> listStockRowManager = FXCollections.observableArrayList();
	ObservableList<String> listFilter = FXCollections.observableArrayList();

	private Manager man;

	/**
	 * Set manager's page
	 * @param man
	 */
	public void setManager(Manager man) {
		this.man = man;
		login.setText(man.getLogin());

		//Add all section available
		SectionDAO sD = new SectionDAO(Main.em);
		List<Section> listSection = sD.getAll();
		for(Section s : listSection) {
			listFilter.add(s.getName());
		}

		//DisplayAll items
		ArticleDAO aD = new ArticleDAO(Main.em);
		List<Article> listArticle = aD.getAll();
		for(Article a : listArticle) {
			if(a.getSection() != null) {
				if(man.getRole() != null && man.getSection() == a.getSection()) {
					listStockRowManager.add(new RowStockManager(a.getName(), 
							a.getSection().getName(), a.getQuantity(), new Spinner<Integer>(),a));
				}
				else {
					listStockRowManager.add(new RowStockManager(a.getName(), 
							a.getSection().getName(), a.getQuantity(), null,a));
				}
			}
			else {
				listStockRowManager.add(new RowStockManager(a.getName(), 
						null, a.getQuantity(), null,a));
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Samples
		/*
		listStockRowManager.add(new RowStockManager("lala", "lolo", 45, new Spinner<Integer>()));
		listStockRowManager.add(new RowStockManager("xperia", "", 96, new Spinner<Integer>()));
		 */
		listFilter.add("Tous");

		//table.getColumns().addAll(product,section,stock,modify);
		product.setCellValueFactory(new PropertyValueFactory<RowStockManager, String>("product"));
		section.setCellValueFactory(new PropertyValueFactory<RowStockManager, String>("section"));
		stock.setCellValueFactory(new PropertyValueFactory<RowStockManager, Integer>("stock"));
		modify.setCellValueFactory(new PropertyValueFactory<RowStockManager,  Spinner<Integer>>("modify"));
		table.setItems(listStockRowManager);
		filter.setItems(listFilter);
	}

	/**
	 * Update table using section filter
	 * @param event
	 */
	@FXML
	public void changeFilter(ActionEvent event) {
		listStockRowManager.clear();

		//DisplayAll items
		ArticleDAO aD = new ArticleDAO(Main.em);
		List<Article> listArticle = aD.getAll();
		for(Article a : listArticle) {
			if(filter.getValue() == null ||
					filter.getValue().equalsIgnoreCase("Tous") ||
					(a.getSection() != null && a.getSection().getName().equals(filter.getValue()))) {
				if(a.getSection() != null) {
					if(man.getRole() != null && man.getSection() == a.getSection()) {
						listStockRowManager.add(new RowStockManager(a.getName(), 
								a.getSection().getName(), a.getQuantity(), new Spinner<Integer>(),a));
					}
					else {
						listStockRowManager.add(new RowStockManager(a.getName(), 
								a.getSection().getName(), a.getQuantity(), null,a));
					}
				}
				else {
					listStockRowManager.add(new RowStockManager(a.getName(), 
							null, a.getQuantity(), null,a));
				}
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
	 * Save modifications done in the table
	 * @param event
	 */
	@FXML 
	public void saveModification(ActionEvent event) {
		for(RowStockManager row : listStockRowManager) {
			ArticleDAO ad = new ArticleDAO(Main.em);
			Article a = row.getArticle();
			
			if(row.getModify() != null) {
				row.getModify().increment();
				row.getModify().decrement();
				a.setQuantity(a.getQuantity() + row.getModify().getValue());
			}
			
			
			ad.save();
		}
		changeFilter(null);
	}

	/**
	 * Cancel modifications done in the table
	 * @param event
	 */
	@FXML
	public void cancelAction(ActionEvent event) {
		changeFilter(null);
	}

}
