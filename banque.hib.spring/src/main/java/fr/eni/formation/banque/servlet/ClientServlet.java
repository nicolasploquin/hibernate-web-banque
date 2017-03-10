package fr.eni.formation.banque.servlet;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.dao.DAOFactory;
import fr.eni.formation.banque.dao.IClientDAO;


/**
 * Servlet implementation class RechercherServlet
 */
@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = -877669945510806560L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		DAOFactory daoFactory = (DAOFactory) WebApplicationContextUtils
									.getWebApplicationContext(application).getBean("daoFactory");
		IClientDAO dao = daoFactory.getClientDAO();
			
//		IClientDAO dao = 
//				WebApplicationContextUtils.getWebApplicationContext(application)
//					.getBean("clientDAO");
		
		String action = request.getParameter("action");
		
		long idClient = 0L;
		String nom = "";
		String prenom = "";
		List<Client> clients = null;
		
		switch (action) {
		case "select-all":
//			dao = daoFactory.getClientDAO();
			clients = dao.readAll();
			request.setAttribute("clients", clients);
			application.getRequestDispatcher("/index.jsp").forward(request,response);
			
			break;
		case "select-critere":
			
			String critere = request.getParameter("critere");
			clients = dao.read(critere);
			request.setAttribute("clients", clients);
			application.getRequestDispatcher("/index.jsp").forward(request,response);
			
			break;
		case "select":
			
			idClient = Long.parseLong(request.getParameter("idClient"));
			
			Client client = dao.read(idClient);

			request.setAttribute("client", client);
			
			application.getRequestDispatcher("/client.jsp").forward(request,response);
			
			break;
		case "create":
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");

			dao.create(nom,prenom);
			
			application.getRequestDispatcher("/client?action=select-all").forward(request,response);
			
			break;
		case "update":
			idClient = Long.parseLong(request.getParameter("idClient"));
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");

			dao.update(idClient,nom,prenom);
			
			application.getRequestDispatcher("/client?action=select-all").forward(request,response);
			
			break;
		case "virement-form":
			idClient = Long.parseLong(request.getParameter("idClient"));

			request.setAttribute("client", dao.read(idClient));
			
			application.getRequestDispatcher("/virement-form.jsp").forward(request,response);

			break;
		case "virement":
			// Enregistrer le virement
			idClient = Long.parseLong(request.getParameter("idClient"));

			long idCompteDebit = Long.parseLong(request.getParameter("idCompteDebit"));
			String libelleDebit = request.getParameter("libelleDebit");
			long idCompteCredit = Long.parseLong(request.getParameter("idCompteCredit"));
			String libelleCredit = request.getParameter("libelleCredit");

			double montant = Double.parseDouble(request.getParameter("montant"));

//			IOperationDAO odao = DAOFactory.getOperationDAO();		
//			odao.create(idCompteDebit,new Date(),libelleDebit,-montant,"Debit");
//			odao.create(idCompteCredit,new Date(),libelleCredit,montant,"Credit");

			dao.virement(idCompteDebit,libelleDebit,idCompteCredit,libelleCredit,montant);
			
			application.getRequestDispatcher("/client?action=select&idClient="+idClient).forward(request,response);
			
			break;
		default:
			break;
		}
		

	}



}
