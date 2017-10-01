package us.alkubaisi.casemanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.RoleDAO;
import us.alkubaisi.casemanager.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	@Transactional
	public List<Role> getRoles() {
		List<Role> roles = roleDAO.getRoles();
		return roles;
	}

	@Override
	@Transactional
	public Role getRoleById(int roleId) {
		Role role = roleDAO.getRoleById(roleId);
		return role;
	}

}
