package us.alkubaisi.casemanager.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"username","roles", "tabs"})
public class WelcomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLoginPage(Model model, Principal principal){
		//model.addAttribute("username", "mustafa");
		List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Set<String> tabs = new HashSet<>();
		for(SimpleGrantedAuthority authority : authorities){
			if(authority.toString().equals("ROLE_ADMIN")){
				tabs.add("cases");
				tabs.add("workers");
				tabs.add("applicants");
				tabs.add("locations");
				tabs.add("users");
			}else if(authority.toString().equals("ROLE_SUPERVISOR")){
				tabs.add("cases");
				tabs.add("workers");
				tabs.add("applicants");
				tabs.add("locations");
			}else if(authority.toString().equals("ROLE_WORKER")){
				tabs.add("cases");
			}
		}
		model.addAttribute("username", principal.getName());
		model.addAttribute("tabs", tabs);
		return "redirect:/case/list";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("error", "true");
        return "login";
 
    }

	
}