package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import com.mysql.cj.result.Row;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Main;
import model.Article;
import model.Leader;
import model.Manager;
import model.Role;
import model.Section;
import model.User;
import view.ButtonIdentifiable;
import view.RowManagementAdmin;
import view.RowStockAdmin;

/**
 * Class to control user administration
 *
 */
public class AdminManagementController implements Initializable{
	//FOR ADD ===============================
	
	@FXML private Button add;
	@FXML private ComboBox<Role> roleCB;
	ObservableList<Role> listRo = FXCollections.observableArrayList();
	/**
	 * Contain free/available sections
	 */
	@FXML private ComboBox<Section> sectionCB;
	/**
	 * Contain free/available sections
	 */
	ObservableList<Section> listSec = FXCollections.observableArrayList();
	@FXML private TextField passwordF;
	@FXML private TextField loginF;
	@FXML private TextField firstNameF;
	@FXML private TextField nameF;

	// For other ================================
	
	@FXML private Button disconnect;
	@FXML private Button save;
	@FXML private Label lab_login;

	@FXML private TableView<RowManagementAdmin> table;
	@FXML private TableColumn<RowManagementAdmin, TextField> name;
	@FXML private TableColumn<RowManagementAdmin, TextField> firstName;
	@FXML private TableColumn<RowManagementAdmin, TextField> login;
	@FXML private TableColumn<RowManagementAdmin, TextField> password;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<String>> role;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<Section>> section;
	@FXML private TableColumn<RowManagementAdmin, ButtonIdentifiable> action;

	ObservableList<RowManagementAdmin> listUserObs = FXCollections.observableArrayList();

	/**
	 * contain all existant section
	 */
	private List<Section> sectionList = new ArrayList<Section>();

	private Leader leader;

	public void setLeader(Leader lead) {
		this.leader = lead;
		lab_login.setText(lead.getLogin());

		displayUsers();
	}

	/**
	 * Update combo box too choose section when adding an employee
	 */
	public void updateSectionBox() {
		listSec.clear();
		listSec.add(new Section("Aucun"));

		for(Section name : sectionList) {
			if(name.getManager() == null)
				listSec.add(name);
		}
	}

	/**
	 * Refresh the table display
	 */
	public void displayUsers() {
		listUserObs.clear();

		//DisplayAll items
		UserDAO uD = new UserDAO(Main.em);
		ManagerDAO mD = new ManagerDAO(Main.em);
		List<User> listArticle = uD.getAll();
		int idBut = 0;
		for(User u : listArticle) {

			ComboBox<Section> section = null;

			//If it's a manager
			if(u.getRole() != null && u.getRole().getName().equalsIgnoreCase("Manager")) {
				//Section
				section =  new ComboBox<Section>();
				Manager m = mD.findById(u.getId());
				ObservableList<Section> listSection = FXCollections.observableArrayList();
				section.setItems(listSection);

				listSection.add(new Section("Aucun"));
				if(m.getSection() != null)
					listSection.add(m.getSection());

				for(Section name : sectionList) {
					if(name.getManager() == null)
						listSection.add(name);
				}
				section.setValue(m.getSection());
			}

			//Action Button delete
			ButtonIdentifiable butt = new ButtonIdentifiable("Supprimer",idBut,u);
			butt.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					deleteRow(((ButtonIdentifiable)e.getSource()).getUser());
				}
			});
			//not deletable
			if(u.getId() == leader.getId()) {
				butt.setDisable(true);
			}

			try {
				listUserObs.add(
						new RowManagementAdmin(
								u,
								new TextField(u.getLastName()),
								new TextField(u.getFirstName()),
								new TextField(u.getLogin()),
								new TextField(u.getPassword()),
								u.getRole().getName(),
								section, 
								butt));
			}catch (Exception e) {
				e.printStackTrace();
			}

			idBut ++;
		}
	}

	/**
	 * Initialise controller
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, TextField>("name"));
		firstName.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, TextField>("firstName"));
		login.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, TextField>("login"));
		password.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, TextField>("password"));
		role.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, ComboBox<String>>("role"));
		section.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, ComboBox<Section>>("section"));
		action.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin,  ButtonIdentifiable>("action"));
		table.setItems(listUserObs);
		sectionCB.setItems(listSec);
		//Add all section available
		SectionDAO sD = new SectionDAO(Main.em);
		List<Section> listSection = sD.getAll();
		for(Section s : listSection) {
			sectionList.add(s);
		}
		updateSectionBox();

		//Add all roles available
		RoleDAO rd = new RoleDAO(Main.em);
		List<Role> liste = rd.getAll();
		roleCB.setItems(listRo);
		for(Role r : liste) {
			listRo.add(r);
		}
	}

	public void deleteRow(User u) {
		UserDAO ud = new UserDAO(Main.em);

		if(!u.getRole().getName().equalsIgnoreCase("admin")) {
			SectionDAO sd = new SectionDAO(Main.em);
			ManagerDAO md = new ManagerDAO(Main.em);
			Manager m = md.findById(u.getId());
			if(m.getSection() != null)
				m.getSection().removeManager();
			//m.setSection(null);
			sd.save();
		}
		ud.delete(u);

		//refresh table
		displayUsers();
		updateSectionBox();
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
			FXMLLoader fxmlLoader = new FXMLLoader(url);
			Parent root = fxmlLoader.load();
			AdminStockController controller = fxmlLoader.getController();
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
	public void addUser(ActionEvent event) {
		ManagerDAO md = new ManagerDAO(Main.em);
		LeaderDAO ld = new LeaderDAO(Main.em);
		UserDAO ud = new UserDAO(Main.em);
			
		//Check empty fields
		if(nameF.getText().isEmpty() || 
				firstNameF.getText().isEmpty() || 
				loginF.getText().isEmpty() ||
				passwordF.getText().isEmpty() ||
				roleCB.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur d'ajout");
			alert.setContentText("Des champs sont vides");
			Stage alStage = (Stage) alert.getDialogPane().getScene().getWindow();
			alStage.getIcons().add(new Image("file:images/icon.jpg"));
			alert.showAndWait();
			return;
		}
		
		//Check login already used?
		List<User> listUser = ud.getAll();
		for(User u : listUser) {
			if(u.getLogin().equalsIgnoreCase(loginF.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Erreur d'ajout");
				alert.setContentText("Le login est d�j� utilis�.");
				Stage alStage = (Stage) alert.getDialogPane().getScene().getWindow();
				alStage.getIcons().add(new Image("file:images/icon.jpg"));
				alert.showAndWait();
				return;
			}
		}
		
		if(roleCB.getValue().getName().equalsIgnoreCase("admin")) {
			Leader l = new Leader(nameF.getText(), firstNameF.getText(), loginF.getText(), passwordF.getText());
			l.setRole(roleCB.getValue());
			ld.create(l);
		}
		else {
			Manager m = new Manager(null, null, loginF.getText(), passwordF.getText());
			m.setRole(roleCB.getValue());
			m.setSection(sectionCB.getValue());
			md.create(m);
		}
		displayUsers();
	}	

	/**
	 * if admin role is selected then section cb is disable
	 * @param event
	 */
	@FXML
	public void changeRole(ActionEvent event) {
		if(roleCB.getValue().getName().equalsIgnoreCase("admin")) {
			sectionCB.setDisable(true);
		}
		else {
			sectionCB.setDisable(false);
		}
	}

	/**
	 * Save modification done in the table, by clicking save button
	 * @param event
	 */
	@FXML 
	public void saveModification(ActionEvent event) {
		if(checkSimilarities()) {
			for(RowManagementAdmin row : listUserObs) {
				User u = row.getUser();
				if(!u.getRole().getName().equalsIgnoreCase("admin")) {

					ManagerDAO md = new ManagerDAO(Main.em);
					SectionDAO sd = new SectionDAO(Main.em);
					Manager m = md.findById(u.getId());

					if(row.getSection().getValue() != null &&
							row.getSection().getValue() != m.getSection()) {
						if(!row.getSection().getValue().getName().equalsIgnoreCase("Aucun")){
							if(m.getSection() != null)
								m.getSection().setManager(null);
							row.getSection().getValue().setManager(m);
							m.setSection(row.getSection().getValue());
						}
						else {
							if(m.getSection() != null) {
								m.getSection().setManager(null);
							}
							m.setSection(null);
						}
					}
					//Save manager et sections modifications
					md.save();
					sd.save();
				}
				u.setFirstName(row.getFirstName().getText());
				u.setLastName(row.getName().getText());
				u.setLogin(row.getLogin().getText());
				u.setPassword(row.getPassword().getText());
				
				//Save user modifications
				UserDAO ud = new UserDAO(Main.em);
				ud.save();
			}
			displayUsers();
			updateSectionBox();
			
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur de sauvegarde");
			alert.setContentText("Le login et rayon attribu�s pour chaque utlisateurs doivent �tre uniques.");
			Stage alStage = (Stage) alert.getDialogPane().getScene().getWindow();
			alStage.getIcons().add(new Image("file:images/icon.jpg"));
			alert.showAndWait();
		}
	}

	@FXML
	public void cancelAction(ActionEvent event) {
		displayUsers();
	}

	/**
	 * Check if there are similarities in the data table
	 * @return
	 */
	public boolean checkSimilarities() {
		for(RowManagementAdmin row : listUserObs) {
			boolean comparable = false;
			for(RowManagementAdmin row_compare : listUserObs) {

				if(comparable == true) {
					if(row.getLogin().getText().equalsIgnoreCase(row_compare.getLogin().getText()) == true) {						
						return false;
					}

					if(row.getSection() != null && row_compare.getSection() != null) {
						if(row.getSection().getValue() == null ||  row_compare.getSection().getValue() == null ||
								row.getSection().getValue().getName().equalsIgnoreCase("Aucun")){
							continue;
						}
						else if(row.getSection().getValue().getName().equalsIgnoreCase(row_compare.getSection().getValue().getName()))
							return false;
					}

				}
				if(comparable == false && row_compare.equals(row)) {
					comparable = true;
				}

			}
		}
		return true;
	}
}

