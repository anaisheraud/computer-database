package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.beans.Computer;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;

/**
 * Servlet implementation class ComputerListServlet
 */
@WebServlet("/ListComputers")
/**
 * La classe Servlet étend une classe HttpServlet
 * Une Servlet est une classe java qui hérite de HttpServlet
 */

public class ComputerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ServiceComputer serviceComputer;
	
	@Autowired
	private ServiceCompany serviceCompany;

	
	public void init(ServletConfig config) throws ServletException { 
		super.init(config); 
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
	}
	
	public int lenPage;
	public int page;
	public int maxPage;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		List<Computer> computers;
		
		int countComputers = serviceComputer.getAll();
			
		if(request.getParameter("lenPage") != null)
		{
			lenPage = Integer.parseInt(request.getParameter("lenPage"));
			System.out.println(lenPage);
		}
		else
		{
			lenPage = 10;
		}

		if(request.getParameter("orderBy") != null && !request.getParameter("orderBy").isEmpty()) {
			System.out.println("orderBy");
			computers = serviceComputer.orderBy();
		
		} else if (request.getParameter("search") == null || request.getParameter("search").isEmpty()) {
			System.out.println("lister");
			computers = serviceComputer.lister();
			
		request.getParameter("ListComputers");
		request.setAttribute("ListComputers", computers);
			
		} else {
			System.out.println("search");

			String search = new String("%");
			
			search += request.getParameter("search");
			
			/**
			 * % recherche contient, quelque soit les caractères avant et après il ressort le mot complet
			 */
			search += "%";
			request.setAttribute("search", search);

			computers = serviceComputer.getByName(search);
			
			request.getParameter("ListComputers");
			request.setAttribute("ListComputers", computers);

		}

			
		// en attendant... computers =  daofactory.getComputerDao().listerpage(page, page+lenPage, lenPage);
		
		request.getParameter("ListComputers");
		request.setAttribute("ListComputers", computers);
		request.setAttribute("countComputers", countComputers);
		request.setAttribute("page", page);
		request.setAttribute("lenPage", lenPage);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("selection") != null && !request.getParameter("selection").equals("")) {
			
			String ids = request.getParameter("selection");
			
			System.out.println("id :" + ids);
			
			List<Integer> ListId = new ArrayList<Integer>();
					
			/**
			 * Stocker les contenus sous forme de clé-valeur
			 */
			for(String id : ids.split(",")) {
				ListId.add(Integer.parseInt(id));
			}
			
			for(Integer Id : ListId) {
				serviceComputer.delete(serviceComputer.find(Id));	
			}
			
		}
		
		doGet(request, response);
	}

}
