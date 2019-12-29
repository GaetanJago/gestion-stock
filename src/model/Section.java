package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Section {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@ManyToOne
	private Store store;
	
	@OneToMany(mappedBy = "section",
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articles;
	
	@OneToOne
	private Manager manager;

	public Section(String name) {
		super();
		this.name = name;
		this.articles = new ArrayList<>();
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
		if(this.store != null){
			this.store.getSections().remove(this);
		}
		this.store = store;
		if(store != null && !this.store.getSections().contains(this)){
			this.store.getSections().add(this);
		}

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
		if(this.manager != null){
			this.manager.setSection(null);
		}
		this.manager = manager;
	}

	public void addArticle(Article article){
		if(!this.articles.contains(article)){
			this.articles.add(article);
			article.setSection(this);
		}
	}

	public void removeArticle(Article article){
		if(this.articles.contains(article)){
			this.articles.remove(article);
			article.setSection(null);
		}
	}
	
	
}
