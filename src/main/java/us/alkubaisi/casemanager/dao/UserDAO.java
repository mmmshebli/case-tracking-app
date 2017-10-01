package us.alkubaisi.casemanager.dao;

import us.alkubaisi.casemanager.entity.User;

public interface UserDAO {

	public void disableEnableUser(User user);
	
	public User getUserByUserName(String userName);
	
	public void saveUser(User user);
	
	public void updateUserRoles(User user);
	
}
