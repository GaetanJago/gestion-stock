package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Role {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "role",
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users;

	public Role(String name) {
		super();
		this.name = name;
		this.users = new ArrayList<>();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> user) {
		this.users = user;
	}

	public void addUser(User user){
		if(!this.users.contains(user)){
			this.users.add(user);
			user.setRole(this);
		}
	}

	public void removeUser(User user){
		if(this.users.contains(user)){
			this.users.remove(user);
			user.setRole(null);
		}
	}
}
