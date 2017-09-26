package us.alkubaisi.casemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import us.alkubaisi.casemanager.entity.Applicant;
import us.alkubaisi.casemanager.entity.Worker;
import us.alkubaisi.casemanager.service.ApplicantService;

@Controller
@RequestMapping(value="/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET) 
	public String applicantList(Model model){
		List<Applicant> applicants = applicantService.applicantList();
		model.addAttribute("applicants", applicants);
		applicantService.applicantList();
		return "list-applicants";
	}
	
	@RequestMapping(value="/getapplicant", method = RequestMethod.GET) 
	public String getApplicant(int id){
		applicantService.getApplicant(id);
		return null;
	}
	
	@RequestMapping(value="/searchapplicant", method = RequestMethod.GET) 
	public String searchApplicant(@RequestParam("firstName") String firstName,
								  @RequestParam("lastName") String lastName,
								  @RequestParam("caseNumber") String caseNumber, Model model){
		try{
			int caseNumberInt = Integer.parseInt(caseNumber);
			List<Applicant> applicants = applicantService.searchApplicant(firstName, lastName, caseNumberInt);
			model.addAttribute("applicants", applicants);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			List<Applicant> applicants = applicantService.searchApplicant(firstName, lastName, 0);
			model.addAttribute("applicants", applicants);
			return "list-applicants";
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("applicants", new ArrayList<Applicant>());
			return "list-workers";
		}
		
		return "list-applicants";	
	}
	
	
}
