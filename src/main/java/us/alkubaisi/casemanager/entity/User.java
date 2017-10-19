package us.alkubaisi.casemanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@Column(name="username")
	private String userName;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="USER_ROLES",
			joinColumns=@JoinColumn(name="username"),
			inverseJoinColumns=@JoinColumn(name="role_id")
			)
	private List<Role> roles;
	
	@JsonIgnoreProperties({"cases", "location", "user"})
	@OneToOne(mappedBy = "user", cascade={CascadeType.ALL})
	private Worker worker;
	
	public User(){
		
	}

	public User(String userName, String password, int enabled, List<Role> roles, Worker worker) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
		this.worker = worker;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + ", roles="
				+ roles + ", worker=" + worker.getFirstName() + "]";
	}

	

}
