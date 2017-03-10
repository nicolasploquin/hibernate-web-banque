package fr.eni.formation.banque.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.ICompteDAO;

public class CompteDAO implements ICompteDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Compte read(long idCompte) {
		Compte compte = null;
		try {
			Session hib = sessionFactory.getCurrentSession();			
			compte = (Compte) hib.load(Compte.class, new Long(idCompte));
		} catch (HibernateException e){
			e.printStackTrace();
		}
		return compte;
	}

}
