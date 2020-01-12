package view;

import javafx.scene.control.ComboBox;
import model.Section;
import model.User;

/**
 * Row to insert in admin management table
 *
 */
public class RowManagementAdmin {
	
	private User userName;
	private String role;
	private ComboBox<Section> section;
	private ButtonIdentifiable action;
		
	public RowManagementAdmin(User userName, String role, ComboBox<Section> section, ButtonIdentifiable action	) {
		super();
		this.userName = userName;
		this.role = role;
		this.section = section;
		this.action = action;
		
		if(this.section != null)
			this.section.setPrefWidth(Double.MAX_VALUE);
	}
	
	public User getUserName() {
		return userName;
	}
	public void setUserName(User userName) {
		this.userName = userName;
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
	
	
	
}
