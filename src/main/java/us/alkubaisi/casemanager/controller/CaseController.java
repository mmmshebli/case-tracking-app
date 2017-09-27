package us.alkubaisi.casemanager.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import us.alkubaisi.casemanager.entity.Case;
import us.alkubaisi.casemanager.entity.CaseUpdate;
import us.alkubaisi.casemanager.entity.Location;
import us.alkubaisi.casemanager.entity.User;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.CaseService;
import us.alkubaisi.casemanager.service.CaseUpdateService;
import us.alkubaisi.casemanager.service.LocationService;
import us.alkubaisi.casemanager.service.UserService;
import us.alkubaisi.casemanager.service.WorkerService;

@Controller
@RequestMapping(value="/case")
public class CaseController {

	@Autowired
	private CaseService caseService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private CaseUpdateService caseUpdateService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listCases(Model model, Principal principal){
		List<String> roles = getCurrentPrincipalRoles();
		List<Case> cases = new ArrayList<>();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			cases = caseService.listCases();
		}else{
			cases = caseService.listCasesByWorkerId(workerService.getWorkerIdByUsername(principal.getName()));
		}
		model.addAttribute("cases", cases);
		if(cases.size() > 0){
			model.addAttribute("casee", cases.get(0));
		}else{
			model.addAttribute("casee", new Case());
		}
		return "list-cases";
	}
	
	@RequestMapping(value="/listWithSelectedDetails", method = RequestMethod.GET)
	public String listCasesWithSelectedDetails(@RequestParam("caseId") int caseId, Model model, Principal principal){
		List<String> roles = getCurrentPrincipalRoles();
		List<Case> cases = new ArrayList<>();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			cases = caseService.listCases();
		}else{
			cases = caseService.listCasesByWorkerId(workerService.getWorkerIdByUsername(principal.getName()));
		}	
		Case selectedCase = caseService.getCase(caseId);
		model.addAttribute("cases", cases);
		model.addAttribute("casee", selectedCase);
		return "list-cases";
	}
	
	@RequestMapping(value="/getcase", method = RequestMethod.GET)
	public String getCase(@RequestParam("id") int id){
		caseService.getCase(id);
		return null;
	}
	
	@RequestMapping(value="/newcase", method = RequestMethod.GET)
	public String processNewCase(Model model){
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		model.addAttribute("casee", new Case());
		return "new-case-form";
	}
	
	@RequestMapping(value="/newcase", method = RequestMethod.POST)
	public String newCase(@ModelAttribute("casee") Case casee, Model model){
		casee.setStatus("New");
		casee.setDateOpened(new Date());
		casee.setLastUpdated(new Date());
		System.out.println(casee);
		caseService.newCase(casee);
		List<Case> cases = caseService.listCases();
		model.addAttribute("cases", cases);
		return "list-cases";
	}
	
	@RequestMapping(value="/updatecase", method = RequestMethod.POST)
	public String updateCase(@RequestParam("newstatus") String newStatus, 
							@RequestParam("caseId") int caseId, Model model, Principal principal){
		Case selectedCase = caseService.getCase(caseId);
		selectedCase.setStatus(newStatus);
		caseService.updateCase(selectedCase);
		List<String> roles = getCurrentPrincipalRoles();
		List<Case> cases = new ArrayList<>();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			cases = caseService.listCases();
		}else{
			cases = caseService.listCasesByWorkerId(workerService.getWorkerIdByUsername(principal.getName()));
		}	
		model.addAttribute("cases", cases);
		model.addAttribute("casee", selectedCase);
		return "list-cases";
	}
	
	@RequestMapping(value="assigntoworker", method=RequestMethod.GET)
	public String assignCaseToWorker(@RequestParam("caseId") int caseId,
									 @RequestParam("locationId") int locationId, Model model){
		List<Worker> workers = workerService.searchWorkerByLocation(locationId);
		model.addAttribute("workers", workers);
		model.addAttribute("location", locationService.getLocation(locationId).getName());
		model.addAttribute("caseId", caseId);
		return "assign-case-to-worker";
	}
	
	@RequestMapping(value = "processassigntoworker", method = RequestMethod.GET)
	public String processassigntoworker(@RequestParam("caseId") int caseId,
										@RequestParam("workerId") int workerId){
		Worker worker = workerService.getWorker(workerId);
		Case casee = caseService.getCase(caseId);
		casee.setWorker(worker);
		//just to save not to create new
		caseService.newCase(casee);
		return "redirect:/case/list";
	}
	
	@RequestMapping(value="createcseupdate", method=RequestMethod.POST)
	public String createcseupdate(@RequestParam("caseId") int caseId,
								  @RequestParam("internalUpdateDetail") String internalUpdateDetail,
								  @RequestParam("applicantFacingUpdateDetail") String applicantFacingUpdateDetail, Model model){
		Case casee = caseService.getCase(caseId);
		CaseUpdate caseUpdate = new CaseUpdate(new Date(), internalUpdateDetail, applicantFacingUpdateDetail, casee); 
		caseUpdateService.saveCaseUpdate(caseUpdate);
		model.addAttribute("caseId", caseId);
		return "redirect:/case/listWithSelectedDetails";
	}
	
	public List<String> getCurrentPrincipalRoles(){
		List<String> roles = new ArrayList<>();	
		List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(SimpleGrantedAuthority authority : authorities){
			roles.add(authority.toString());
		}
		return roles;
	}
	
	@RequestMapping(value="/listcasesbyworker", method = RequestMethod.GET)
	public String listCasesByWorker(@RequestParam("workerId") int workerId, Model model, Principal principal){
		List<String> roles = getCurrentPrincipalRoles();
		List<Case> cases = new ArrayList<>();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			cases = caseService.listCasesByWorkerId(workerId);
		}else{
			cases = caseService.listCasesByWorkerId(workerService.getWorkerIdByUsername(principal.getName()));
		}
		model.addAttribute("cases", cases);
		if(cases.size() > 0){
			model.addAttribute("casee", cases.get(0));
		}else{
			model.addAttribute("casee", new Case());
		}
		return "list-cases";
	}
	
	@RequestMapping(value="/listcasesbylocation", method = RequestMethod.GET)
	public String listCasesByLocation(@RequestParam("locationId") int locationId, Model model, Principal principal){
		List<String> roles = getCurrentPrincipalRoles();
		List<Case> cases = new ArrayList<>();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			cases = caseService.listCasesByLocationId(locationId);
		}else{
			cases = caseService.listCasesByWorkerId(workerService.getWorkerIdByUsername(principal.getName()));
		}
		model.addAttribute("cases", cases);
		if(cases.size() > 0){
			model.addAttribute("casee", cases.get(0));
		}else{
			model.addAttribute("casee", new Case());
		}
		return "list-cases";
	}
	
	@RequestMapping(value="/searchcase", method = RequestMethod.GET)
	public String searchWorker(@RequestParam("firstName") String firstName, 
								@RequestParam("lastName") String lastName, 
								@RequestParam("caseNumber") String caseNumber, Model model, Principal principal){
		String userName = principal.getName();
		int workerId = workerService.getWorkerIdByUsername(userName);
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		
		//if filterForSecurity false then bring all results for mixed users.. ie dont filter results for only the requester worker
		boolean filterForSecurity = true;
		List<String> roles = getCurrentPrincipalRoles();
		if(roles.contains("ROLE_ADMIN") || roles.contains("ROLE_SUPERVISOR")){
			filterForSecurity =false;
		}

		try{
			int caseNumberInteger = Integer.parseInt(caseNumber);
			List<Case> cases = caseService.searchCase(firstName, lastName, caseNumberInteger, filterForSecurity, workerId);
			model.addAttribute("cases", cases);
			if(cases.size() > 0){
				model.addAttribute("casee", cases.get(0));
			}
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			List<Case> cases = caseService.searchCase(firstName, lastName, 0, filterForSecurity, workerId);
			model.addAttribute("cases", cases);
			return "list-cases";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("cases", new ArrayList<Case>());
			return "list-cases";
		}
		
		return "list-cases";
	}
	
}
