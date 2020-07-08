package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.validation.Validator;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dao.CompanyDao;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappersDTO.ComputerMapperDTO;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;
import com.excilys.validator.Validators;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class ComputerAddServlet
 */
@WebServlet("/ComputerAddServlet")

@Controller
public class ComputerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceComputer serviceComputer;
	
	@Autowired
	private ServiceCompany serviceCompany;
	
	public void init(ServletConfig config) throws ServletException { 
		super.init(config); 
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
	} 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DaoFactory daofactory = DaoFactory.getInstance();
		
		List<Company> companies;
		
		companies =  serviceCompany.lister();
		
		request.getParameter("Company");
		request.setAttribute("Company", companies);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//DaoFactory daofactory = DaoFactory.getInstance();
		
		ComputerDTO computerdto = new ComputerDTO();
		Computer computer;
				
		try {
			Validators.validatorName(request.getParameter("computerName"));
			computerdto.setName(request.getParameter("computerName"));
			
			if(request.getParameter("introduced") != "") {
				computerdto.setIntroduced(request.getParameter("introduced"));
			}
			if(request.getParameter("discontinued") != "") {
				computerdto.setDiscontinued(request.getParameter("discontinued"));
			}
			computerdto.setCompany_id(request.getParameter("companyId"));
			
			computer = ComputerMapperDTO.ComputerDtoToComputer(computerdto);
			
			serviceComputer.create(computer);
			
		}catch (Exception e) {
			request.getParameter("ErrorName");
			request.setAttribute("ErrorName", "ERROR !!! This field can't be empty !");
			e.printStackTrace();
		}
	
		//System.out.println(computer);
	
		doGet(request, response);
	}

}
