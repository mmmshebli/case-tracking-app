package us.alkubaisi.casemanager.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.alkubaisi.casemanager.entity.Location;
import us.alkubaisi.casemanager.entity.Role;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.CaseService;
import us.alkubaisi.casemanager.service.LocationService;
import us.alkubaisi.casemanager.service.RoleService;
import us.alkubaisi.casemanager.service.WorkerService;

@RestController
@RequestMapping("/rest-worker")
public class WorkerRestController {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public List<Worker> workersList(Model model){
		List<Worker> workers = workerService.getWorkers();
		model.addAttribute("workers", workers);
		return workers;
	}
	
}
