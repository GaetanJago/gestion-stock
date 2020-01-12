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
	@FXML private Button add;


	@FXML private ComboBox<Role> roleCB;
	ObservableList<Role> listRo = FXCollections.observableArrayList();

	@FXML private ComboBox<Section> sectionCB;
	ObservableList<Section> listSec = FXCollections.observableArrayList();

	@FXML private TextField passwordF;
	@FXML private TextField loginF;

	@FXML private Button disconnect;
	@FXML private Button save;
	@FXML private Label login;

	@FXML private TableView<RowManagementAdmin> table;
	@FXML private TableColumn<RowManagementAdmin, User> userName;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<String>> role;
	@FXML private TableColumn<RowManagementAdmin, ComboBox<Section>> section;
	@FXML private TableColumn<RowManagementAdmin, ButtonIdentifiable> action;

	ObservableList<RowManagementAdmin> listUserObs = FXCollections.observableArrayList();
	ObservableList<String> listSectionObs = FXCollections.observableArrayList();
	private List<Section> sectionList = new ArrayList<Section>();
	private List<String> roleList = new ArrayList<String>();

	private Leader leader;

	public void setLeader(Leader lead) {
		this.leader = lead;
		login.setText(lead.getLogin());
		
		displayUsers();
	}


	public void displayUsers() {
		listUserObs.clear();

		//DisplayAll items
		UserDAO uD = new UserDAO(Main.em);
		ManagerDAO mD = new ManagerDAO(Main.em);
		RoleDAO rD = new RoleDAO(Main.em);
		List<User> listArticle = uD.getAll();
		int idBut = 0;
		for(User u : listArticle) {

			ComboBox<Section> section = null;

			//If it's a manager
			if(u.getRole() != null && u.getRole().getName().equalsIgnoreCase("Manager")) {
				//Section
				section =  new ComboBox<Section>();
				Manager m = mD.findById(u.getId()).get();
				ObservableList<Section> listSection = FXCollections.observableArrayList();
				section.setItems(listSection);
				//listSection.add("");
				for(Section name : sectionList) {
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
								u.getRole().getName(),
								section, 
								butt));
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			idBut ++;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userName.setCellValueFactory(new PropertyValueFactory<RowManagementAdmin, User>("userName"));
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
			this.listSectionObs.add(s.getName());
			listSec.add(s);
		}

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
		ud.delete(u);
		
		//refresh table
		displayUsers();
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
		RoleDAO rd = new RoleDAO(Main.em);
		SectionDAO sd = new SectionDAO(Main.em);
		
		if(roleCB.getValue().getName().equalsIgnoreCase("admin")) {
			Leader l = new Leader(null, null, loginF.getText(), passwordF.getText());
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
	
	@FXML 
	public void saveModification(ActionEvent event) {
		for(RowManagementAdmin row : listUserObs) {
			User u = row.getUserName();
			if(!u.getRole().getName().equalsIgnoreCase("admin")) {
				ManagerDAO md = new ManagerDAO(Main.em);
				Manager m = md.findById(u.getId()).get();
				m.setSection(row.getSection().getValue());
				md.save();				
			}
		}
		displayUsers();
	}
}

