package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Section {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Store store;
	
	@OneToMany(mappedBy = "section",
			cascade = CascadeType.ALL)
	private List<Article> articles;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Manager manager;

	public Section(String name) {
		super();
		this.name = name;
	}

	public Section() {
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
}
