package com.excilys.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.ComputerDaoImpl;

/**
 * Servlet implementation class ComputerListServlet
 */
@WebServlet("/")
/* La classe Servlet étend une classe HttpServlet 
 * Une Servlet est une classe java qui hérite de HttpServlet*/
public class ComputerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		
		request.setAttribute("ListComputer", ComputerDaoImpl.lister());
		request.getRequestDispatcher("").forward(request, response);
		
	}

	//Quand il envoie des données de formulaire, il souhaite faire un post
	//poster des informations
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
