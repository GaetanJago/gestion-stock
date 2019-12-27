package view;

import javafx.scene.control.ComboBox;

public class RowManagementAdmin {
	
	private String userName;
	private ComboBox<String> role;
	private ComboBox<String> section;
	private ButtonIdentifiable action;
		
	public RowManagementAdmin(String userName, ComboBox<String> role, ComboBox<String> section, ButtonIdentifiable action) {
		super();
		this.userName = userName;
		this.role = role;
		this.section = section;
		this.action = action;
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ComboBox<String> getRole() {
		return role;
	}
	public void setRole(ComboBox<String> role) {
		this.role = role;
	}
	public ComboBox<String> getSection() {
		return section;
	}
	public void setSection(ComboBox<String> section) {
		this.section = section;
	}
	public ButtonIdentifiable getAction() {
		return action;
	}
	public void setAction(ButtonIdentifiable action) {
		this.action = action;
	}
	
	
	
}
