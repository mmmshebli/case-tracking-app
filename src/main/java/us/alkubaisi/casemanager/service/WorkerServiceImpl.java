package us.alkubaisi.casemanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.WorkerDAO;
import us.alkubaisi.casemanager.entity.Worker;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDAO workerDAO;
	
	@Override
	@Transactional
	public List<Worker> getWorkers() {
		List<Worker> workersList = workerDAO.getWorkers();
		System.out.println(workersList);
		return workersList;
	}

	@Override
	@Transactional
	public Worker getWorker(int id) {
		Worker worker = workerDAO.getWorker(id);
		System.out.println(worker);
		return worker;
	}

	@Override
	@Transactional
	public Worker getWorkerByNumber(int number) {
		Worker worker = workerDAO.getWorkerByNumber(number);
		return worker;
	}

	@Override
	@Transactional
	public List<Worker> searchWorker(String firstName, String lastName, int workerNumber) {
		List<Worker> workers = workerDAO.searchWorker(firstName, lastName, workerNumber);
		return workers;
	}

	@Override
	@Transactional
	public List<Worker> searchWorkerByLocation(int locationId) {
		List<Worker> workers = workerDAO.searchWorkerByLocation(locationId);
		return workers;
	}

	@Override
	@Transactional
	public void saveWorker(Worker worker) {
		workerDAO.saveWorker(worker);
	}

	@Override
	@Transactional
	public int getLargestWorkerNumber() {
		int largestWorkerNumber = workerDAO.getLargestWorkerNumber();
		return largestWorkerNumber;
	}

	@Override
	@Transactional
	public int getWorkerIdByUsername(String username) {
		int workerId = workerDAO.getWorkerIdByUsername(username);
		return workerId;
	}

	@Override
	@Transactional
	public void deleteWorker(Worker worker) {
		workerDAO.deleteWorker(worker);
	}


}
