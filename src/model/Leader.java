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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}
