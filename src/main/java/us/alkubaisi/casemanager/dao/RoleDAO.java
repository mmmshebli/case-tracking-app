package us.alkubaisi.casemanager.dao;

import java.util.List;

import us.alkubaisi.casemanager.entity.Role;

public interface RoleDAO {

	public List<Role> getRoles();
	
	public Role getRoleById(int roleId);
	
}
