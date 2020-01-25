package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String lastName;
	private String firstName;
	private String login;
	private String password;


	@ManyToOne
	private Role role;

	public User(String lastName, String firstName, String login, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.login = login;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if(this.role != null){
			this.role.getUsers().remove(this);
		}

		this.role = role;

		if(role != null && !this.role.getUsers().contains(this)){
			this.role.getUsers().add(this);
		}
	}

	public String getFullName(){
		return this.firstName + " " + this.lastName;
	}


	@Override
	public String toString() {
		return getLogin();
	}
}