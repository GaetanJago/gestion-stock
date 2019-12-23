package model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Store {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String adress;
	
	@OneToMany(mappedBy = "store",
			cascade = CascadeType.ALL)
	private List<Section> sections;	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Leader leader;

	public Store(String adress) {
		super();
		this.adress = adress;
	}

	public Store() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}
	
}
