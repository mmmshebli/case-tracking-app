package us.alkubaisi.casemanager.service;

import java.util.List;

import us.alkubaisi.casemanager.entity.Applicant;

public interface ApplicantService {

	public List<Applicant> applicantList();
	
	public Applicant getApplicant(int id);
	
	public List<Applicant> searchApplicant(String firstName, String lastName, int caseNumber);
	
}
