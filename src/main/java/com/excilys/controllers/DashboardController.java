package com.excilys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.beans.Computer;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;

@Controller
public class DashboardController {

	@Autowired
	private ServiceComputer serviceComputer;
	List<Computer> computers;

	@Autowired
	private ServiceCompany serviceCompany;

	@GetMapping("/dashboard")
	public void get(String orderBy, String search) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("Dashboard");

		List<Computer> computers;

		int countComputers = serviceComputer.getAll();

		if (orderBy != null && !orderBy.isEmpty()) {
			computers = serviceComputer.orderBy();

		} else if (search == null || search.isEmpty()) {
			System.out.println("lister");

			computers = serviceComputer.lister();

			modelView.getModel().put("ListComputers", computers);

		} else {

			search += "%";

			search += search;

			/**
			 * % recherche contient, quelque soit les caractères avant et après il ressort
			 * le mot complet
			 */
			search += "%";
			request.setAttribute("search", search);

			computers = serviceComputer.getByName(search);

			request.getParameter("ListComputers");
			request.setAttribute("ListComputers", computers);

		}

		// en attendant... computers = daofactory.getComputerDao().listerpage(page,
		// page+lenPage, lenPage);

		request.getParameter("ListComputers");
		request.setAttribute("ListComputers", computers);
		request.setAttribute("countComputers", countComputers);
		request.setAttribute("page", page);
		request.setAttribute("lenPage", lenPage);

	}

}
