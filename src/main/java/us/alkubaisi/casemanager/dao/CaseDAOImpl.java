package us.alkubaisi.casemanager.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.Case;

@Repository
public class CaseDAOImpl implements CaseDAO {

	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public List<Case> listCases() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Case order by caseNumber", Case.class);
		List<Case> casesList = query.getResultList();
		return casesList;
	}
	
	@Override
	public List<Case> listCasesByWorkerId(int workerId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Case casee where casee.worker.id = :workerId order by caseNumber", Case.class);
		query.setParameter("workerId", workerId);
		List<Case> casesList = query.getResultList();
		return casesList;
	}
	
	@Override
	public Case getCase(int id){
		Session session = sessionFactory.getCurrentSession();
		Case casee = session.get(Case.class, id);
		return casee;
	}

	@Override
	public void newCase(Case casee) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(casee);
	}

	@Override
	public void updateCase(Case casee) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(casee);
	}

	@Override
	public List<Case> listCasesByLocationId(int locationId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Case casee where casee.location.id = :locationId", Case.class);
		query.setParameter("locationId", locationId);
		List<Case> cases = query.getResultList();
		return cases;
	}

	@Override
	public List<Case> searchCase(String firstName, String lastName, int caseNumber, boolean filterForSecurity, int workerId) {
		if(firstName.length() <= 0 && lastName.length() <= 0 && caseNumber <= 0){
			ArrayList<Case> cases = new ArrayList<Case>();
			return cases;
		}
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Case where ";
		String q = "";
		if(firstName.length() > 0){
			q = q + "and applicant.firstName=" + "'" + firstName + "'";
			
		}
		if(lastName.length() > 0){
			q = q + " and applicant.lastName=" + "'" + lastName + "'";
		}
		if(caseNumber > 0){
			q = q + " and caseNumber=" + caseNumber;
		}
		q = q.substring(4);
		queryString = queryString + q;
		System.out.println("Query String: " + queryString);
		Query query = session.createQuery(queryString);
		List<Case> cases = query.getResultList();
		
		if(filterForSecurity){
			List<Case> filteredList = new ArrayList<>();
			for(Case casee : cases){
				if(casee.getWorker().getId() == workerId){
					filteredList.add(casee);
				}
			}
			return filteredList;
		}
		
		return cases;
	}
	

}
