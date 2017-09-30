package us.alkubaisi.casemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import us.alkubaisi.casemanager.entity.Location;
import us.alkubaisi.casemanager.entity.Role;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.CaseService;
import us.alkubaisi.casemanager.service.LocationService;
import us.alkubaisi.casemanager.service.RoleService;
import us.alkubaisi.casemanager.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String workersList(Model model){
		List<Worker> workers = workerService.getWorkers();
		
		List<Integer> casesCount = new ArrayList<>();
		for(Worker worker : workers){
			casesCount.add(worker.getCases().size());
		}
		model.addAttribute("casesCount", casesCount);
		model.addAttribute("workers", workers);
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		return "list-workers";
	}
	
	@RequestMapping(value="/getworker", method = RequestMethod.GET)
	public String getWorkerById(@RequestParam("id") int id, Model model){
		List<Worker> workers = new ArrayList<Worker>();
		workers.add(workerService.getWorker(id));
		model.addAttribute("workers", workers);
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		return "list-workers";
	}
	
	@RequestMapping(value="/searchworker", method = RequestMethod.GET)
	public String searchWorker(@RequestParam("firstName") String firstName, 
								@RequestParam("lastName") String lastName, 
								@RequestParam("workerNumber") String workerNumber, Model model){
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		try{
			int workerNumberInteger = Integer.parseInt(workerNumber);
			List<Worker> workers = workerService.searchWorker(firstName, lastName, workerNumberInteger);
			model.addAttribute("workers", workers);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			List<Worker> workers = workerService.searchWorker(firstName, lastName, 0);
			model.addAttribute("workers", workers);
			return "list-workers";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("workers", new ArrayList<Worker>());
			return "list-workers";
		}
		
		return "list-workers";
	}
	
	@RequestMapping(value="searchworkerylocation", method=RequestMethod.GET)
	public String searchworkerylocation(@RequestParam("location") int locationId, Model model){
		List<Worker> workers = workerService.searchWorkerByLocation(locationId);
		model.addAttribute("workers", workers);
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		return "list-workers";
	}
	
	@RequestMapping(value="addworker", method=RequestMethod.GET)
	public String addWorker(Model model){
		List<Role> roles = roleService.getRoles();
		int largestWorkerNumber = workerService.getLargestWorkerNumber();
		Worker worker = new Worker();
		worker.setWorkerNumber(largestWorkerNumber + 1);
		model.addAttribute("largestWorkerNumber", largestWorkerNumber);
		model.addAttribute("worker", worker);
		model.addAttribute("roles", roles);
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		return "add-worker-form";
	}
	
	@RequestMapping(value="addworker", method=RequestMethod.POST)
	public String addWorker(@Valid @ModelAttribute("worker") Worker worker, BindingResult bindingResult, Model model){
		//adding all locations to populate the search by location drop down list
		List<Location> locations = locationService.locationList();
		model.addAttribute("locations", locations);
		int largestWorkerNumber = workerService.getLargestWorkerNumber();
		if(worker.getWorkerNumber() <= largestWorkerNumber){
			worker.setWorkerNumber(largestWorkerNumber + 1);
			return "add-worker-form";
		}
		if(bindingResult.hasErrors()){
			return "add-worker-form";
		}else{
			workerService.saveWorker(worker);
			return "list-workers";
		}
	}	
	
	@RequestMapping(value="/workerswithnocases", method = RequestMethod.GET)
	public String workersWithNoCases(Model model){
		List<Worker> workers = new ArrayList<Worker>();
		List<Worker> workersNoCases = new ArrayList<Worker>();
		workers = workerService.getWorkers();
		for(Worker worker : workers){
			if(worker.getCases().size() == 0){
				workersNoCases.add(worker);
			}
		}
		model.addAttribute("workers", workersNoCases);
		
		return "delete-worker";
	}
	
	@RequestMapping(value="/deleteworker", method = RequestMethod.GET)
	public String deleteWorker(@RequestParam("workerId") int workerId, Model model){
		Worker worker = workerService.getWorker(workerId);
		workerService.deleteWorker(worker);
		return "redirect: workerswithnocases";
	}
	
}
