package us.alkubaisi.casemanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	@OneToOne(mappedBy = "user", cascade={CascadeType.ALL})
	private UserRole userRole;
	
	@OneToOne(mappedBy = "user", cascade={CascadeType.ALL})
	private Worker worker;
	
	public User(){
		
	}

	public User(String userName, String password, int enabled, UserRole userRole, Worker worker) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + ", userRole="
				+ userRole.getRole() + ", worker=" + worker.getFirstName() + "]";
	}

	

}
