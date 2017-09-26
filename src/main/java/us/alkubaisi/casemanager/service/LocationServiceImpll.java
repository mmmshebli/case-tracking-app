package us.alkubaisi.casemanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.LocationDAO;
import us.alkubaisi.casemanager.entity.Location;

@Service
public class LocationServiceImpll implements LocationService {
	
	@Autowired 
	private LocationDAO locationDAO;

	@Override
	@Transactional
	public List<Location> locationList() {
		List<Location> locationList = locationDAO.licationList();
		System.out.println(locationList);
		return locationList;
	}

	@Override
	@Transactional
	public Location getLocation(int id) {
		Location location = locationDAO.getLocation(id);
		System.out.println(location);
		return location;
	}

	@Override
	@Transactional
	public void newLocation(Location location) {
		locationDAO.newLocation(location);
		
	}

}
