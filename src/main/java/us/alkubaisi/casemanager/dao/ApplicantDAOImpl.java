package us.alkubaisi.casemanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.Applicant;
import us.alkubaisi.casemanager.entity.Worker;

@Repository
public class ApplicantDAOImpl implements ApplicantDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Applicant> applicantList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Applicant order by firstName", Applicant.class);
		List<Applicant> applicantList = query.getResultList();
		return applicantList;
	}

	@Override
	public Applicant getApplicant(int id) {
		Session session = sessionFactory.getCurrentSession();
		Applicant applicant = session.get(Applicant.class, id);
		return applicant;
	}

	@Override
	public List<Applicant> searchApplicant(String firstName, String lastName, int caseNumber) {
		if(firstName.length() <= 0 && lastName.length() <= 0 && caseNumber <= 0){
			ArrayList<Applicant> applicants = new ArrayList<Applicant>();
			return applicants;
		}
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Applicant applicant where ";
		String q = "";
		if(firstName.length() > 0){
			q = q + "and firstName=" + "'" + firstName + "'";
			
		}
		if(lastName.length() > 0){
			q = q + " and lastName=" + "'" + lastName + "'";
		}
		if(caseNumber > 0){
			q = q + " and applicant.casee.caseNumber=" + caseNumber;
		}
		q = q.substring(4);
		queryString = queryString + q;
		System.out.println("Query String: " + queryString);
		Query query = session.createQuery(queryString);
		List<Applicant> applicants = query.getResultList();
		return applicants;
	}

}
