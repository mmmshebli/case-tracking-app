package us.alkubaisi.casemanager.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.alkubaisi.casemanager.dao.CaseUpdateDAO;
import us.alkubaisi.casemanager.entity.CaseUpdate;

@Service
public class CaseUpdateServiceImpl implements CaseUpdateService {

	@Autowired
	private CaseUpdateDAO caseUpdateDAO;
	
	@Override
	@Transactional
	public void saveCaseUpdate(CaseUpdate caseUpdate) {
		caseUpdateDAO.saveCaseUpdate(caseUpdate);
	}

}
