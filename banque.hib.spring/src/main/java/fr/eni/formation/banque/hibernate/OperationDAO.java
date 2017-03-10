package fr.eni.formation.banque.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.dao.IOperationDAO;

public class OperationDAO implements IOperationDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Operation create(long idCompte, Date date, String libelle, double montant, String type) {
		Operation operation = null;
		if("Credit".equals(type)){
			operation = new Credit(date, libelle, montant);
		}else if("Debit".equals(type)){
			operation = new Debit(date, libelle, montant);			
		}
		Session hib = sessionFactory.getCurrentSession();
		try {
//			hib.beginTransaction();
						
			Compte compte = (Compte) hib.load(Compte.class,idCompte);
			compte.getOperations().add(operation);
			
			hib.persist(operation);
			
//			hib.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
//			hib.getTransaction().rollback();
		}
		return operation;
	}

}
