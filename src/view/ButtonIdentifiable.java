package view;

import javafx.scene.control.Button;

public class ButtonIdentifiable extends Button {
	private int idButton;

	public ButtonIdentifiable() {
		super();
	}

	public ButtonIdentifiable(String text,int id) {
		super(text);
		this.idButton = id;
	}

	public int getIdButton() {
		return idButton;
	}

	public void setIdButton(int id) {
		this.idButton = id;
	}
	
	
}
