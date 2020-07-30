package com.excilys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.AddDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappersDTO.ComputerMapperDTO;
import com.excilys.models.Company;
import com.excilys.models.Computer;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;
import com.excilys.validator.Validators;

@Controller
public class AddController {

	@Autowired
	private ServiceComputer serviceComputer;
	
	@Autowired
	private ServiceCompany serviceCompany;
	
	@GetMapping("/addComputer")
	public ModelAndView get(AddDTO addDTO) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("addComputer");
		
		List<Company> companies;
		
		companies = serviceCompany.lister();
		
		addDTO.getCompany();
		modelView.getModel().put("Company", companies);
		
		return modelView;
		
	}
	
	@PostMapping("/addComputer")
	public ModelAndView doPost(ComputerDTO computerdto, AddDTO addDTO) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:listComputers");
		
		Computer computer;
		
		try {

			Validators.validatorName(computerdto.getName());
			
			computer = ComputerMapperDTO.ComputerDtoToComputer(computerdto);
			
			serviceComputer.create(computer);
			
		}catch (Exception e) {
			addDTO.getErrorName(); 
			modelView.getModel().put("ErrorName", "ERROR !!! This field can't be empty !");
			e.printStackTrace();
		}
		
		return modelView;
		
	}
	
}
