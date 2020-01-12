package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Article;
import model.Leader;
import model.Manager;
import model.Section;
import model.User;
import view.RowManagementAdmin;
import view.RowStockAdmin;
import view.RowStockManager;

/**
 * Class to control stock as admin role
 *
 */
public class AdminStockController implements Initializable{
	@FXML private Button disconnect;
	@FXML private ComboBox<String> filter;
	@FXML private Label login;

	@FXML private TableView<RowStockAdmin> table;
	@FXML private TableColumn<RowStockAdmin, TextField> product;
	@FXML private TableColumn<RowStockAdmin, ComboBox<Section>> section;
	@FXML private TableColumn<RowStockAdmin, Integer> stock;
	@FXML private TableColumn<RowStockAdmin, Spinner<Integer>> modify;


	private ObservableList<RowStockAdmin> listStockRowAdmin = FXCollections.observableArrayList();
	private ObservableList<String> listFilter = FXCollections.observableArrayList();
	private List<Section> sectionList = new ArrayList<Section>();

	private Leader leader;

	public void setLeader(Leader lead) {
		this.leader = lead;
		login.setText(lead.getLogin());


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//table.getColumns().addAll(product,section,stock,modify);
		product.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, TextField>("product"));
		section.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, ComboBox<Section>>("section"));
		stock.setCellValueFactory(new PropertyValueFactory<RowStockAdmin, Integer>("stock"));
		modify.setCellValueFactory(new PropertyValueFactory<RowStockAdmin,  Spinner<Integer>>("modify"));
		table.setItems(listStockRowAdmin);
		filter.setItems(listFilter);
		listFilter.add("Tous");

		//Add all section available
		SectionDAO sD = new SectionDAO(Main.em);
		List<Section> listSection = sD.getAll();
		for(Section s : listSection) {
			sectionList.add(s);
			listFilter.add(s.getName());
		}

		dispStock("Tous");
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
			FXMLLoader fxmlLoader = new FXMLLoader(url);
			Parent root = fxmlLoader.load();
			AdminManagementController controller = fxmlLoader.getController();
			controller.setLeader(this.leader);

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

	@FXML
	public void changeFilter(ActionEvent event) {
		dispStock(filter.getValue());

	}

	public void dispStock(String sectionName) {
		listStockRowAdmin.clear();
		//DisplayAll items
		ArticleDAO aD = new ArticleDAO(Main.em);
		List<Article> listArticle = aD.getAll();
		for(Article a : listArticle) {
			if(sectionName == null || 
					sectionName.equalsIgnoreCase("Tous") ||
					(a.getSection() != null 
					&& sectionName.equals(a.getSection().getName()))) {
				ComboBox<Section> section = new ComboBox<Section>();
				ObservableList<Section> listSection = FXCollections.observableArrayList();
				section.setItems(listSection);
				//listSection.add("");
				for(Section name : sectionList) {
					listSection.add(name);
				}

				if(a.getSection() != null)
					section.setValue(a.getSection());
				/*
				else
					section.setValue("");*/
				
				listStockRowAdmin.add(
						new RowStockAdmin(
								new TextField(a.getName()),
								section, 
								a.getQuantity(), 
								new Spinner<Integer>(),
								a));
			}
		}
	}
	
	@FXML 
	public void saveModification(ActionEvent event) {
		for(RowStockAdmin row : listStockRowAdmin) {
			ArticleDAO ad = new ArticleDAO(Main.em);
			Article a = row .getArt();
			SectionDAO sd = new SectionDAO(Main.em);
			Section s = row.getSection().getValue();
			a.getSection().getArticles().remove(a);
			s.getArticles().add(a);
			a.setSection(row.getSection().getValue());
			a.setName(row.getProduct().getText());
			
			row.getModify().increment();
			row.getModify().decrement();
			a.setQuantity(a.getQuantity() + row.getModify().getValue());
			
			ad.save();
			sd.save();
		}
		dispStock(filter.getValue());
	}
}
