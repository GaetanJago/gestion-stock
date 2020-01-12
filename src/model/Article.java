package model;

import javax.persistence.*;

@Entity
@Table(name = "Article")
public class Article {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String brand;
	private int price;
	private int quantity;
	
	@ManyToOne
	private Section section;
	
	public Article(String name, String brand, int price, int quantity) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
