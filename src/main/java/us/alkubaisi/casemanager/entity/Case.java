package us.alkubaisi.casemanager.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="casee")
public class Case {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="case_number")
	private int caseNumber;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_opened")
	@Temporal(TemporalType.DATE)
	private Date dateOpened;
	
	@Column(name="last_updated")
	@Temporal(TemporalType.DATE)
	private Date lastUpdated;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="worker_id")
	private Worker worker;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="location_id")
	private Location location;
	
	@OneToOne( cascade={CascadeType.ALL}) //cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="casee", cascade={CascadeType.ALL})
	private List<CaseUpdate> caseUpdates;
	
	public Case(){
		
	}

	public Case(int caseNumber, String description, String status, Date dateOpened, Date lastUpdated, Worker worker,
			Location location, Applicant applicant, List<CaseUpdate> caseUpdates) {
		super();
		this.caseNumber = caseNumber;
		this.description = description;
		this.status = status;
		this.dateOpened = dateOpened;
		this.lastUpdated = lastUpdated;
		this.worker = worker;
		this.location = location;
		this.applicant = applicant;
		this.caseUpdates = caseUpdates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	public List<CaseUpdate> getCaseUpdates() {
		return caseUpdates;
	}

	public void setCaseUpdates(List<CaseUpdate> caseUpdates) {
		this.caseUpdates = caseUpdates;
	}
	
	/*@Override
	public String toString() {
		return "Case [id=" + id + ", caseNumber=" + caseNumber + ", description=" + description + ", status=" + status
				+ "date opened=" + dateOpened + "last updated=" + lastUpdated + " worker=" + worker.getFirstName() + " " + worker.getLastName() + 
				" location=" + location.getName() + " applicant=" + applicant.getFirstName() + " " + applicant.getLastName() + "]";
	}*/

	@Override
	public String toString() {
		return "Case [id=" + id + ", caseNumber=" + caseNumber + ", description=" + description + ", status=" + status
				+ "date opened=" + dateOpened + "last updated=" + lastUpdated +  
				" location=" + location.getId() + " applicant=" + applicant.getFirstName() + " " + applicant.getLastName() + " CASE UPDATES: " + caseUpdates + "]";
	}
	
	
}
