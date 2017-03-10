package fr.eni.formation.banque.servlet;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.DAOFactory;


/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/compte")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();

		DAOFactory daoFactory = (DAOFactory) WebApplicationContextUtils.getWebApplicationContext(application).getBean("daoFactory");

		String action = request.getParameter("action");
		
		long idCompte = 0L;
		
		switch (action) {
		case "select":
			
			idCompte = Long.parseLong(request.getParameter("idCompte"));
			Compte compte = daoFactory.getCompteDAO().read(idCompte);
			request.setAttribute("compte", compte);
			application.getRequestDispatcher("/compte.jsp").forward(request,response);
			
			break;
		default:
			break;
		}		
		
		
		
	}

}
