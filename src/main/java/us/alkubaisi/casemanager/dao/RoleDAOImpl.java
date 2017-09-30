package us.alkubaisi.casemanager.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery("from Role", Role.class);
		List<Role> roles = query.getResultList();
		return roles;
	}

}
