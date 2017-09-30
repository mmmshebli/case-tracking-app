package us.alkubaisi.casemanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(
		name="WORKER",
		uniqueConstraints = {@UniqueConstraint(columnNames="worker_number")}

)
public class Worker {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotNull
	@Size(min=1, message="is required")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="worker_number")
	private int workerNumber;
	
	@OneToMany(mappedBy="worker", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Case> cases;
	
	@ManyToOne
	private Location location;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="login_name")
	private User user;
	
	public Worker(){
		
	}

	public Worker(String firstName, String lastName, int workerNumber, List<Case> cases, Location location, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.workerNumber = workerNumber;
		this.cases = cases;
		this.location = location;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(int workerNumber) {
		this.workerNumber = workerNumber;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", workerNumber="
				+ workerNumber + ", cases= " + cases + ", locationName=" + location.getName() + " locationID=" + location.getId() + " UserName: " + user.getUserName() +"]";
	}
	
	
	
}
