package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Role {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "role",
			cascade = CascadeType.ALL)
	private List<User> user;

	public Role(String name) {
		super();
		this.name = name;
	}
	
	public Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
}
