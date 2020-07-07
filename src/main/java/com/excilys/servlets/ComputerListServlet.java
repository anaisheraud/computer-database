package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Computer;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;

import services.ServiceComputer;

/**
 * Servlet implementation class ComputerListServlet
 */
@WebServlet("/ListComputers")
/* La classe Servlet étend une classe HttpServlet 
 * Une Servlet est une classe java qui hérite de HttpServlet*/
public class ComputerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public int lenPage;
	public int page;
	public int maxPage;
	
	//Elle possède un constructeur
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Elle possède des méthodes dont elle irite 
    //Deux principales méthodes ici :
    //Avec Http y a plusieurs modes de communication 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //la requête http nous arrive sous la forme d'un objet request et nous devons renvoyer un objet response
    //l'objet request indique les paramètres qu'a pu envoyé l'utilisateur ou encore le nom du navigateur
    //l'objet response lui est l'objet construit afin de renvoyer une page web/html
    
    //Quand le visiteur va lire une page, il cherche à récupérer une page web, donc une requête get
    //charger la page 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//méthode setContentType pour indiquer qu'on renvoie du HTML et utilisation de l'encodage UTF-8
		//WEB-INF masque aux visiteurs tout ce qui est à l'intérieur
		
		//indique ou la jsp se trouve
		//transmet l'objet requête et l'objet réponse à la jsp
	
		DaoFactory daofactory = DaoFactory.getInstance();
		List<Computer> computers = new ArrayList<Computer>();
		
		int countComputers = ComputerDaoImpl.getAll();
		
		///computers =  daofactory.getComputerDao().lister();
		
		if(request.getParameter("lenPage") != null)
		{
			lenPage = Integer.parseInt(request.getParameter("lenPage"));
			System.out.println(lenPage);
		}
		else
		{
			lenPage = 10;
		}
		
		System.out.println("test : " + request.getParameter("orderBy"));
		
		//Pas de paramètre dans l'orderBy alors il va chercher à savoir si y a une recherche
		if(request.getParameter("orderBy") != null && !request.getParameter("orderBy").isEmpty()) {
			System.out.println("orderBy");
			computers = DaoFactory.getInstance().getComputerDao().orderBy();
		
		//Si, y a pas de recherche alors on passe au else
		} else if (request.getParameter("search") == null || request.getParameter("search").isEmpty()) {
			System.out.println("lister");
			computers = DaoFactory.getInstance().getComputerDao().lister();
			
		request.getParameter("ListComputers");
		request.setAttribute("ListComputers", computers);
			
		} else {
			System.out.println("search");

			String search = new String("%");
			
			search += request.getParameter("search");
			//recherche contient, quelque soit les caractères avant et après il ressort le mot complet
			search += "%";
			request.setAttribute("search", search);

			computers = DaoFactory.getInstance().getComputerDao().getbyName(search);
			
			request.getParameter("ListComputers");
			request.setAttribute("ListComputers", computers);

		}

			
		// en attendant... computers =  daofactory.getComputerDao().listerpage(page, page+lenPage, lenPage);
		
		System.out.println(lenPage);
		
		request.getParameter("ListComputers");
		request.setAttribute("ListComputers", computers);
		request.setAttribute("countComputers", countComputers);
		request.setAttribute("page", page);
		request.setAttribute("lenPage", lenPage);
		//request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		
	}
	
	//Quand il envoie des données de formulaire, il souhaite faire un post
	//poster des informations
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("selection") != null && !request.getParameter("selection").equals("")) {
			
			String ids = request.getParameter("selection");
			
			System.out.println("id :" + ids);
			
			List<Integer> ListId = new ArrayList<Integer>();
					
			//stocker les contenus sous forme de clé-valeur
			
			for(String id : ids.split(",")) {
				ListId.add(Integer.parseInt(id));
			}
			
			for(Integer Id : ListId) {
				DaoFactory.getInstance().getComputerDao().delete(ComputerDaoImpl.find(Id));	
			}
		}
		
		doGet(request, response);
	}

}
