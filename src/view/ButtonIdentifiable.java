package view;

import javafx.scene.control.Button;
import model.User;

/**
 * Button from java fx with an id
 *
 */
public class ButtonIdentifiable extends Button {
	private int idButton;
	private User user;

	public ButtonIdentifiable() {
		super();
	}

	public ButtonIdentifiable(String text,int id,User u) {
		super(text);
		this.idButton = id;
		this.user = u;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdButton() {
		return idButton;
	}

	public void setIdButton(int id) {
		this.idButton = id;
	}
	
	
}
