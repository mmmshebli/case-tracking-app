package us.alkubaisi.casemanager.service;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.CaseDAO;
import us.alkubaisi.casemanager.entity.Case;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	private CaseDAO caseDAO;
	
	@Override
	@Transactional
	public List<Case> listCases() {
		List<Case> casesList = caseDAO.listCases();
		System.out.println(casesList);
		return casesList;
	}

	@Override
	@Transactional
	public Case getCase(int id) {
		Case casee = caseDAO.getCase(id);
		System.out.println(casee);
		return casee;
	}

	@Override
	@Transactional
	public void newCase(Case casee) {
		caseDAO.newCase(casee);
	}

	@Override
	@Transactional
	public List<Case> listCasesByWorkerId(int workerId) {
		List<Case> casesList = caseDAO.listCasesByWorkerId(workerId);
		return casesList;
	}

	@Override
	@Transactional
	public void updateCase(Case casee) {
		caseDAO.updateCase(casee);
	}

	@Override
	@Transactional
	public List<Case> listCasesByLocationId(int locationId) {
		List<Case> cases = caseDAO.listCasesByLocationId(locationId);
		return cases;
	}

	@Override
	@Transactional
	public List<Case> searchCase(String firstName, String lastName, int caseNumber, boolean filterForSecurity, int workerId) {
		List<Case> cases = caseDAO.searchCase(firstName, lastName, caseNumber, filterForSecurity, workerId);
		return cases;
	}

}
