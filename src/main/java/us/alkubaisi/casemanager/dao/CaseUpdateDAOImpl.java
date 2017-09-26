package us.alkubaisi.casemanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.CaseUpdate;

@Repository
public class CaseUpdateDAOImpl implements CaseUpdateDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveCaseUpdate(CaseUpdate caseUpdate) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(caseUpdate);
	}

}
