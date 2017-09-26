package us.alkubaisi.casemanager.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.Location;

@Repository
public class LocationDAOImpl implements LocationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Location> licationList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Location order by name", Location.class);
		List<Location> locationList = query.getResultList();
		return locationList;
	}

	@Override
	public Location getLocation(int id) {
		Session session = sessionFactory.getCurrentSession();
		Location location = session.get(Location.class, id);
		return location;
	}

	@Override
	public void newLocation(Location location) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(location);
	}

}
