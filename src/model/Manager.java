package model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Manager extends User{
	
	@OneToOne(mappedBy = "manager")
	private Section section;

	public Manager() {
		super();
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
}
