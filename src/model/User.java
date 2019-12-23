package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String firstName;
	private String login;
	private String password;
	

	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;

	public User(String name, String firstName, String login, String password) {
		super();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.role = role;
	}	
}
