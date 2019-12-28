package model;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
	
	@Id 
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String brand;
	private int price;
	private int qantity;
	
	@ManyToOne
	private Section section;
	
	public Article(String name, String brand, int price, int qantity) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.qantity = qantity;
	}

	public Article() {
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQantity() {
		return qantity;
	}

	public void setQantity(int qantity) {
		this.qantity = qantity;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		if(this.section != null){
			this.section.getArticles().remove(this);
		}
		this.section = section;
		if(section != null && !this.section.getArticles().contains(this)){
			this.section.getArticles().add(this);
		}
	}
	
	
}
