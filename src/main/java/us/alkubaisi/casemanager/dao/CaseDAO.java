package us.alkubaisi.casemanager.dao;

import java.security.Principal;
import java.util.List;

import us.alkubaisi.casemanager.entity.Case;

public interface CaseDAO {

	public List<Case> listCases();
	
	public List<Case> listCasesByWorkerId(int workerId);
	
	public Case getCase(int id);
	
	public void newCase(Case casee);
	
	public void updateCase(Case casee);
	
	public List<Case> listCasesByLocationId(int locationId);
	
	public List<Case> searchCase(String firstName, String lastName, int caseNumber, boolean filterForSecurity, int workerId);
	
}
