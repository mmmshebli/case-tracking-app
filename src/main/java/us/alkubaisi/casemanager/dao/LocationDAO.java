package us.alkubaisi.casemanager.dao;

import java.util.List;

import us.alkubaisi.casemanager.entity.Location;

public interface LocationDAO {

	public List<Location> licationList();
	
	public Location getLocation(int id);
	
	public void newLocation(Location location);
	
}
