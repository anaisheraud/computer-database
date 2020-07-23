package com.excilys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dto.ComputerDTO;
import com.excilys.dto.EditDTO;
import com.excilys.mappersDTO.ComputerMapperDTO;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;
import com.excilys.validator.Validators;

@Controller
public class EditController {

	@Autowired
	private ServiceComputer serviceComputer;
	
	@Autowired
	private ServiceCompany serviceCompany;
	
	@GetMapping("/editComputer")
	public ModelAndView get(EditDTO editDTO) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("editComputer");
		
		editDTO.getId();
		modelView.getModel().put("id", editDTO.getId());
		
		List<Company> companies = new ArrayList<Company>();
		
		companies =  serviceCompany.lister();
		
		editDTO.getCompany();
		modelView.getModel().put("Company", companies);
			
		return modelView;
		
	}
	
	@PostMapping("/editComputer")
	public ModelAndView doPost(ComputerDTO computerdto, EditDTO editDTO) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:listComputers");
		
		Computer computer = new Computer();
		
		Logger logger = LoggerFactory.getLogger(EditController.class);
		
		try {
			Validators.validatorName(computerdto.getName());
			
			computer = ComputerMapperDTO.ComputerDtoToComputer(computerdto);
			
			serviceComputer.update(computer);
			
			logger.info("tout est ok" + " id = " + computer.getId());
			
		}catch (Exception e) {
			editDTO.getErrorName(); 
			modelView.getModel().put("ErrorName", "ERROR !!! This field can't be empty !");
			e.printStackTrace();
			
			logger.error("erreur");
		}
	
		return modelView;
		
	}
	
}
