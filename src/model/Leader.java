package model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Leader extends User{
	@OneToOne(mappedBy = "leader")
	private Store store;

	public Leader() {
		super();
	}

	public Leader(String lastName, String firstName, String login, String password) {
		super(lastName, firstName, login, password);
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}
