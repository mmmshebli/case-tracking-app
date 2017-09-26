package us.alkubaisi.casemanager.service;

import java.util.List;

import us.alkubaisi.casemanager.entity.Worker;

public interface WorkerService {

	public List<Worker> getWorkers();
	
	public Worker getWorker(int id);
	
	public Worker getWorkerByNumber(int number);
	
	public List<Worker> searchWorker(String firstName, String lastName, int workerNumber);
	
	public List<Worker> searchWorkerByLocation(int locationId);
	
	public void saveWorker(Worker worker);
	
	public int getLargestWorkerNumber();
	
	public int getWorkerIdByUsername(String username);
	
	public void deleteWorker(Worker worker);
	
	
}
