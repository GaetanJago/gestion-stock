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

	public Manager(String lastName, String firstName, String login, String password) {
		super(lastName, firstName, login, password);
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
		if(section != null){
			if(this.section.getManager() == null || !this.section.getManager().equals(this)){
				this.section.setManager(this);
			}
		}
	}

	public void removeSection(){
		if(this.section != null && this.section.getManager() != null){
			this.section.setManager(null);
		}
		this.section = null;
	}

}