package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dao.CompanyDao;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappersDTO.ComputerMapperDTO;
import com.excilys.validator.Validators;

import jdk.internal.jline.internal.Log;
import services.ServiceComputer;

/**
 * Servlet implementation class ComputerAddServlet
 */
@WebServlet("/EditComputers")
public class ComputerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoFactory daofactory = DaoFactory.getInstance();
		
		request.getParameter("id");
		request.setAttribute("id", request.getParameter("id"));
		
		List<Company> companies = new ArrayList<Company>();
		
		companies =  daofactory.getCompanyDao().lister();
		
		request.getParameter("Company");
		request.setAttribute("Company", companies);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DaoFactory daofactory = DaoFactory.getInstance();
		
		ComputerDTO computerdto = new ComputerDTO();
		Computer computer = new Computer();
		
		String id = request.getParameter("id");
		
		Logger logger = LoggerFactory.getLogger(ComputerEditServlet.class);
		
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
			
			computerdto.setId(id);
			
			computer = ComputerMapperDTO.ComputerDtoToComputer(computerdto);
			
			//System.out.println(computer);
			
			daofactory.getComputerDao().update(computer);
			
			logger.info("tout est ok" + "id = " + computer.getId());
			
		}catch (Exception e) {
			request.getParameter("ErrorName");
			request.setAttribute("ErrorName", "ERROR !!! This field can't be empty !");
			e.printStackTrace();
			
			logger.error("erreur");
		}
	
		System.out.println(computer);
	
		doGet(request, response);
	}

}