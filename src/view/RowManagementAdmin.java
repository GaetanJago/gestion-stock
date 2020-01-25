package view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Section;
import model.User;

/**
 * Row to insert in admin management table
 *
 */
public class RowManagementAdmin {
	
	private User user;
	private TextField name;
	private TextField firstName;
	private TextField login;
	private TextField password;
	private String role;
	private ComboBox<Section> section;
	private ButtonIdentifiable action;
	
		
	public RowManagementAdmin(User user,TextField name, TextField firstName,TextField login,TextField password,  String role, ComboBox<Section> section, ButtonIdentifiable action	) {
		super();
		this.user = user;
		
		this.name = name;
		this.firstName = firstName;
		
		this.login = login;
		this.password = password;
		
		this.role = role;
		this.section = section;
		this.action = action;
		
		if(this.section != null)
			this.section.setPrefWidth(Double.MAX_VALUE);
	}
		
	
	
	public TextField getPassword() {
		return password;
	}



	public void setPassword(TextField password) {
		this.password = password;
	}



	public TextField getLogin() {
		return login;
	}


	public void setLogin(TextField login) {
		this.login = login;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ComboBox<Section> getSection() {
		return section;
	}
	public void setSection(ComboBox<Section> section) {
		this.section = section;
	}
	public ButtonIdentifiable getAction() {
		return action;
	}
	public void setAction(ButtonIdentifiable action) {
		this.action = action;
	}

	public TextField getName() {
		return name;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public TextField getFirstName() {
		return firstName;
	}

	public void setFirstName(TextField firstName) {
		this.firstName = firstName;
	}
	
	
	
}
