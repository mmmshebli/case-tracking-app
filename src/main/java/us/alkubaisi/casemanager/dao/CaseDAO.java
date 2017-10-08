package us.alkubaisi.casemanager.dao;

import java.security.Principal;
import java.util.List;

import us.alkubaisi.casemanager.entity.Case;

public interface CaseDAO {

	public List<Case> listCases();
	
	public List<Case> listCasesByPage(int pageNumber);
	
	public List<Case> listCasesByWorkerId(int workerId);
	
	public List<Case> listCasesByWorkerIdPaged(int workerId, int pageNumber);
	
	public List<Case> listCasesByLocationIdPaged(int locationId, int pageNumber);
	
	public Case getCase(int id);
	
	public void newCase(Case casee);
	
	public void updateCase(Case casee);
	
	public List<Case> listCasesByLocationId(int locationId);
	
	public List<Case> searchCase(String firstName, String lastName, int caseNumber, boolean filterForSecurity, int workerId);
	
	public int getCasesCount();
	
	public int getCasesCountByWorker(int workerId);
	
	public int getCasesCountByLocation(int locationId);
	
	
}
