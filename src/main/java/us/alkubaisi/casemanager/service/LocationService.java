package us.alkubaisi.casemanager.service;

import java.util.List;

import us.alkubaisi.casemanager.entity.Location;

public interface LocationService {
	
	public List<Location> locationList();
	
	public Location getLocation(int id);
	
	public void newLocation(Location location);

}
