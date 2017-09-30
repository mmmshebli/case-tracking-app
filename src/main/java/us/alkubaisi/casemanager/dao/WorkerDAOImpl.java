package us.alkubaisi.casemanager.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import us.alkubaisi.casemanager.entity.Worker;

@Repository
public class WorkerDAOImpl implements WorkerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Worker> getWorkers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Worker> query = session.createQuery("from Worker order by lastName", Worker.class);
		List<Worker> workers = query.getResultList();
		return workers;
	}

	@Override
	public Worker getWorker(int id) {
		Session session = sessionFactory.getCurrentSession();
		Worker worker = session.get(Worker.class, id);
		return worker;
	}

	@Override
	public Worker getWorkerByNumber(int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Worker> query = session.createQuery("from Worker where workerNumber=:workerNumber");
		query.setParameter("workerNumber", number);
		Worker worker = query.getSingleResult();
		return worker;
	}

	@Override
	public List<Worker> searchWorker(String firstName, String lastName, int workerNumber) {
		if(firstName.length() <= 0 && lastName.length() <= 0 && workerNumber <= 0){
			ArrayList<Worker> workers = new ArrayList<Worker>();
			return workers;
		}
		
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Worker where ";
		String q = "";
		if(firstName.length() > 0){
			q = q + "and firstName=" + "'" + firstName + "'";
			
		}
		if(lastName.length() > 0){
			q = q + " and lastName=" + "'" + lastName + "'";
		}
		if(workerNumber > 0){
			q = q + " and workerNumber=" + workerNumber;
		}
		
		q = q.substring(4);
		queryString = queryString + q;
		System.out.println("Query String: " + queryString);
		Query query = session.createQuery(queryString);
		List<Worker> workers = query.getResultList();
		return workers;
	}

	@Override
	public List<Worker> searchWorkerByLocation(int locationId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Worker worker where worker.location.id =:locationId");
		query.setParameter("locationId", locationId);
		List<Worker> workers = query.getResultList();
		return workers;
	}

	@Override
	public void saveWorker(Worker worker) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Session session = sessionFactory.getCurrentSession();
		String encodedPassword = passwordEncoder.encode(worker.getUser().getPassword());
		worker.getUser().setPassword(encodedPassword);
		//worker.getUser().getUserRole().setUser(worker.getUser());
		session.saveOrUpdate(worker);
	}

	@Override
	public int getLargestWorkerNumber() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select max(worker.workerNumber) from Worker worker");
		int largestWorkerNumber = (Integer) query.getSingleResult();
		return largestWorkerNumber;
	}

	@Override
	public int getWorkerIdByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select worker.id from Worker worker where worker.user.userName = :username");
		query.setParameter("username", username);
		//Worker worker = (Worker) query.getSingleResult();
		int workerId = (Integer) query.uniqueResult();
		return workerId;
	}

	@Override
	public void deleteWorker(Worker worker) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(worker);
	}

	
}
