package us.alkubaisi.casemanager.dao;

import java.util.List;

import us.alkubaisi.casemanager.entity.Applicant;

public interface ApplicantDAO {
	
	public List<Applicant> applicantList();
	
	public Applicant getApplicant(int id);
	public List<Applicant> searchApplicant(String firstName, String lastName, int caseNumber);

}
