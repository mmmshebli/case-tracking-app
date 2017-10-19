package us.alkubaisi.casemanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CASE_UPDATE")
public class CaseUpdate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="update_date")
	@Temporal(TemporalType.DATE)
	private Date updateDate;
	
	@Column(name="internal_update_detail")
	private String internalUpdateDetail;
	
	@Column(name="applicant_facing_update_detail")
	private String applicantFacingUpdateDetail;
	
	@JsonIgnoreProperties({"applicant", "worker", "caseUpdates", "location", "description"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_id")
	private Case casee;
	
	public CaseUpdate(){
		
	}

	public CaseUpdate(Date updateDate, String internalUpdateDetail, String applicantFacingUpdateDetail, Case casee) {
		super();
		this.updateDate = updateDate;
		this.internalUpdateDetail = internalUpdateDetail;
		this.applicantFacingUpdateDetail = applicantFacingUpdateDetail;
		this.casee = casee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getInternalUpdateDetail() {
		return internalUpdateDetail;
	}

	public void setInternalUpdateDetail(String internalUpdateDetail) {
		this.internalUpdateDetail = internalUpdateDetail;
	}

	public String getApplicantFacingUpdateDetail() {
		return applicantFacingUpdateDetail;
	}

	public void setApplicantFacingUpdateDetail(String applicantFacingUpdateDetail) {
		this.applicantFacingUpdateDetail = applicantFacingUpdateDetail;
	}

	public Case getCasee() {
		return casee;
	}

	public void setCasee(Case casee) {
		this.casee = casee;
	}

	@Override
	public String toString() {
		return "CaseUpdate [id=" + id + ", updateDate=" + updateDate + ", internalUpdateDetail=" + internalUpdateDetail
				+ ", applicantFacingUpdateDetail=" + applicantFacingUpdateDetail + ", caseeNumber=" + casee.getCaseNumber() + "]";
	}

	

}
