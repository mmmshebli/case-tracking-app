package us.alkubaisi.casemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import us.alkubaisi.casemanager.entity.Case;
import us.alkubaisi.casemanager.entity.Location;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.LocationService;

@Controller
@RequestMapping(value="/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String locationList(Model model){
		List<Location> locations = locationService.locationList();
		List<Integer> workersCount = new ArrayList<>();
		List<Integer> casesCount = new ArrayList<>();
		for(Location location : locations){
			casesCount.add(location.getCases().size());
			workersCount.add(location.getWorkers().size());
		}
		model.addAttribute("casesCount", casesCount);
		model.addAttribute("workersCount", workersCount);
		model.addAttribute("locations", locations);
		return "list-locations";
	}
	
	@RequestMapping(value="getlocation", method = RequestMethod.GET)
	public String getLocation(int id){
		locationService.getLocation(id);
		return null;
	}
	
	@RequestMapping(value="newlocation", method = RequestMethod.POST)
	public String newLocation(@RequestParam("locationname") String locationName){
		Location location = new Location(locationName, new ArrayList<Worker>(), new ArrayList<Case>());
		locationService.newLocation(location);
		return "redirect:/location/list";
	}

}
