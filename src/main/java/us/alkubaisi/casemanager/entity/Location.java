package us.alkubaisi.casemanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "location")
	private List<Worker> workers;
	
	@OneToMany(mappedBy = "location")
	private List<Case> cases;
	
	public Location(){
		
	}
	
	public Location(String name){
		this.name = name;
	}
	
	public Location(int id){
		this.name = name;
	}
	
	public Location(int id, String name){
		this.name = name;
	}

	public Location(String name, List<Worker> workers, List<Case> cases) {
		super();
		this.name = name;
		this.workers = workers;
		this.cases = cases;
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

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + "workers=" + workers + " Cases=" + cases + "]";
	}
	

}
