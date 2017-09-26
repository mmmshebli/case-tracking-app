package us.alkubaisi.casemanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.ApplicantDAO;
import us.alkubaisi.casemanager.entity.Applicant;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	private ApplicantDAO applicantDAO;
	
	@Override
	@Transactional
	public List<Applicant> applicantList() {
		List<Applicant> applicantList = applicantDAO.applicantList();
		System.out.println(applicantList);
		return applicantList;
	}

	@Override
	@Transactional
	public Applicant getApplicant(int id) {
		Applicant applicant = applicantDAO.getApplicant(id);
		System.out.println(applicant);
		return applicant;
	}

	@Override
	@Transactional
	public List<Applicant> searchApplicant(String firstName, String lastName, int caseNumber) {
		List<Applicant> applicants = applicantDAO.searchApplicant(firstName, lastName, caseNumber);
		return applicants;
	}

}
