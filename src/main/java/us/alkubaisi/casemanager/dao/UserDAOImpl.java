package us.alkubaisi.casemanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void disableEnableUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);;
	}

	@Override
	public User getUserByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userName);
		return user;
	}

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User u = user;
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		session.saveOrUpdate(u);
	}
	
	@Override
	public void updateUserRoles(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

}
