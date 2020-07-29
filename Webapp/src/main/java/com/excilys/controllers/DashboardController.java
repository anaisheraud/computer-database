package com.excilys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.beans.Computer;
import com.excilys.dto.DashboardDTO;
import com.excilys.services.ServiceComputer;

@Controller
public class DashboardController {

	@Autowired
	private ServiceComputer serviceComputer;

	int lowid;
	int page = 1;
	int lenPage = 10;
	int limit = lenPage;
			
	public int setLowid(int compbypage){
		return lowid = compbypage * page - compbypage;
	}
	
	@GetMapping({"/listComputers","/"})
	public ModelAndView get(DashboardDTO dashboardDTO) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("dashboard");
		
		List<Computer> computers;

		int countComputers = serviceComputer.getAll();
		
		if(dashboardDTO.getLenPage() != null && dashboardDTO.getLenPage() != "") {
			lenPage = Integer.parseInt(dashboardDTO.getLenPage());
		}
		
		if (dashboardDTO.getOrderBy() != null && !dashboardDTO.getOrderBy().isEmpty()) {
			computers = serviceComputer.orderBy();

		} else if (dashboardDTO.getSearch() == null || dashboardDTO.getSearch().isEmpty()) {

			limit = lenPage;
			
			computers = serviceComputer.listerpage(setLowid(lenPage), limit);

			modelView.getModel().put("ListComputers", computers);

		} else {

			String search = new String("%");
			
			search += dashboardDTO.getSearch();
			
			search += "%";

			modelView.getModel().put("search", search);

			computers = serviceComputer.getByName(search);

			dashboardDTO.getListComputers();
			modelView.getModel().put("ListComputers", computers);
			
		}
		
		dashboardDTO.getListComputers();
		modelView.getModel().put("ListComputers", computers);
		modelView.getModel().put("countComputers", countComputers);
		modelView.getModel().put("lenPage", lenPage);
		modelView.getModel().put("page", page);
		
		return modelView;
		
	}
	
	@PostMapping("/listComputers")
	public ModelAndView doPost(DashboardDTO dashboardDTO) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:listComputers");
		
		if(dashboardDTO.getSelection() != null && !dashboardDTO.getSelection().equals("")) {

			String ids = dashboardDTO.getSelection();
			
			List<Integer> ListId = new ArrayList<Integer>();
			
			for(String id : ids.split(",")) {
				ListId.add(Integer.parseInt(id));
			}
			
			for(Integer Id : ListId) {
				serviceComputer.delete(serviceComputer.find(Id));	
			}
			
		}
		
		return modelView;
	
	}

}
