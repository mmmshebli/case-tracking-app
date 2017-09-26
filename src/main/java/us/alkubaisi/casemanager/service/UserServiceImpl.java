package us.alkubaisi.casemanager.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.UserDAO;
import us.alkubaisi.casemanager.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void disableEnableUser(User user) {
		userDAO.disableEnableUser(user);
	}

	@Override
	@Transactional
	public User getUserByUserName(String userName) {
		User user = userDAO.getUserByUserName(userName);
		return user;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

}
