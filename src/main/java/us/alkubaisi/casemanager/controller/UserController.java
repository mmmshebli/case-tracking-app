package us.alkubaisi.casemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import us.alkubaisi.casemanager.entity.Role;
import us.alkubaisi.casemanager.entity.User;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.RoleService;
import us.alkubaisi.casemanager.service.UserService;
import us.alkubaisi.casemanager.service.WorkerService;

@Controller
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/listusers", method=RequestMethod.GET)
	public String listUsers(Model model){
		List<Worker> workers = workerService.getWorkers();
		model.addAttribute("workers", workers);
		return "list-users";
	}
	
	@RequestMapping(value="/disableenableuser", method=RequestMethod.GET)
	public String disableenableuser(@RequestParam("userName") String userName, Model model){
		
		User user = userService.getUserByUserName(userName);
		if(user.getEnabled() == 1){
			user.setEnabled(0);
		}else{
			user.setEnabled(1);
		}
		userService.disableEnableUser(user);
		List<Worker> workers = workerService.getWorkers();
		model.addAttribute("workers", workers);
		return "list-users";
	} 
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public String resetPassword(@RequestParam("newpassword") String newPassword,
								@RequestParam("username") String userName, Model model){
		
		User user = userService.getUserByUserName(userName);
		user.setPassword(newPassword);
		userService.saveUser(user);
		List<Worker> workers = workerService.getWorkers();
		model.addAttribute("workers", workers);
		return "list-users";
	}
	
	@RequestMapping(value="/updateroles", method=RequestMethod.POST)
	public String updateRoles(@RequestParam(name= "userName") String userName,
								@RequestParam(name="roles", required=false) int[] rolesIds){
		if(rolesIds == null){
			return "redirect:/users/listusers";
		}
		User user = userService.getUserByUserName(userName);
		List<Role> roles = new ArrayList<>();
		for(int roleId : rolesIds){
			roles.add(roleService.getRoleById(roleId));
		}
		user.setRoles(roles);
		userService.updateUserRoles(user);
		return "redirect:/users/listusers";
	}
	
	
}
