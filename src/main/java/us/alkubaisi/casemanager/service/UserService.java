package us.alkubaisi.casemanager.service;

import us.alkubaisi.casemanager.entity.User;

public interface UserService {

	public void disableEnableUser(User user);
	
	public User getUserByUserName(String userName);
	
	public void saveUser(User user);
	
	public void updateUserRoles(User user);
	
}
