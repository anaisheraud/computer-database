package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappersDTO.ComputerMapperDTO;

import services.ServiceComputer;

/**
 * Servlet implementation class ComputerAddServlet
 */
@WebServlet("/ComputerAddServlet")
public class ComputerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		DaoFactory daofactory = DaoFactory.getInstance();
		List<Company> companies = new ArrayList<Company>();
		
		companies =  daofactory.getCompanyDao().lister();
		
		request.getParameter("ComputerAddServlet");
		request.setAttribute("ComputerAddServlet", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DaoFactory daofactory = DaoFactory.getInstance();
		
		ComputerDTO computerdto = new ComputerDTO();
		Computer computer = new Computer();
		
		ComputerDao computerdao = daofactory.getComputerDao();
		
		computerdto.setName(request.getParameter("computerName"));
		if(request.getParameter("introduced") != "") {
			computerdto.setIntroduced(request.getParameter("introduced"));
		}
		if(request.getParameter("discontinued") != "") {
			computerdto.setDiscontinued(request.getParameter("discontinued"));
		}
		computerdto.setCompany_id(request.getParameter("companyId"));
		
		computer = ComputerMapperDTO.ComputerDtoToComputer(computerdto);
		
		System.out.println(ComputerDaoImpl.lister().get().lastIndexOf(computer));
		doGet(request, response);
	}

}
