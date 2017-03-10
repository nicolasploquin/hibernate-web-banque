package fr.eni.formation.banque.servlet;



import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.eni.formation.banque.dao.DAOFactory;

/**
 * Servlet implementation class RechercherServlet
 */
@WebServlet("/operation")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = -877669945510806560L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		DAOFactory daoFactory = (DAOFactory) WebApplicationContextUtils.getWebApplicationContext(application).getBean("daoFactory");

		String action = request.getParameter("action");
		
		
		switch (action) {
		case "create":
			long idCompte = Long.parseLong(request.getParameter("idCompte"));
			Date date = null;
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				date = df.parse(request.getParameter("date"));
			} catch (ParseException e) {
				date = new Date();
			}
			String libelle = request.getParameter("libelle");
			double montant = Double.parseDouble(request.getParameter("montant"));
			String type = request.getParameter("type");

					
			daoFactory.getOperationDAO().create(idCompte,date,libelle,montant,type);
			
			application.getRequestDispatcher("/compte?action=select&idCompte="+idCompte).forward(request,response);
			
			break;
		default:
			break;
		}
		

	}



}
