package fr.eni.formation.banque.hibernate;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import fr.eni.formation.banque.Adresse;
import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.dao.IClientDAO;

public class ClientDAO implements IClientDAO {
	
//	@Bean(name="")
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(String nom, String prenom) {
		Session hib = null;

		try {
			hib = sessionFactory.getCurrentSession();
//			hib.beginTransaction();
			
			Client client = new Client(nom,prenom);
			Adresse adr = new Adresse("12 quai du port", "29000", "Quimper");
			client.setAdresse(adr);
			
			hib.persist(client);

//			hib.getTransaction().commit();
			
		} catch (HibernateException e){
			e.printStackTrace();
//			if(hib != null) hib.getTransaction().rollback();
		}

	}

	
	@Override
	public List<Client> readAll() {
		
		List<Client> clients = new LinkedList<Client>();
		Session hib = null;

		try {
			hib = sessionFactory.getCurrentSession();
			
			clients = hib.createQuery("from Client").list(); // List<Client>
//			clients = hib.createQuery("select idClient, nom, prenom from Client").list(); // List<Object[]>
//			clients = hib.createQuery("select new list(idClient, nom, prenom) from Client").list(); // List<List<Object>>
//			clients = hib.createQuery("select new map(idClient as idClient, nom as nom, prenom as prenom) from Client").list();  // List<Map<String,Object>> 
			
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return clients;
	}

	@Override
	public List<Client> read(String critere) {
		
		List<Client> clients = new LinkedList<Client>();
		Session hib = null;
		
		try {
			hib = sessionFactory.getCurrentSession();
			
//			Query query = hib.createQuery("from Client where upper(nom) like :critere or upper(prenom) like :critere");
//			query.setString("critere", "%"+critere.toUpperCase()+"%");
//			clients = query.list();

			Criteria criteria = hib.createCriteria(Client.class);
			criteria.add( Restrictions.or(
	    		Restrictions.ilike("nom", critere, MatchMode.ANYWHERE),
	    		Restrictions.ilike("prenom", critere, MatchMode.ANYWHERE)
			));
		    clients = criteria.list();
			
			
			
		} catch (HibernateException e){
			e.printStackTrace();
		}
		
		return clients;
	}
	
	@Override
	public Client read(long id) {
		
		Client client = null;
		Session hib = null;
		
		try {
			hib = sessionFactory.getCurrentSession();
			
			client = (Client) hib.load(Client.class, new Long(id));
			
		} catch (HibernateException e){
			e.printStackTrace();
		}
		
		return client;
	}


	@Override
	public void update(long idClient, String nom, String prenom) {
		
		
	}


	@Override
	public void virement(long idCompteDebit, String libelleDebit,
			long idCompteCredit, String libelleCredit, double montant) {
		Debit debit = new Debit(new Date(), libelleDebit, -montant);			
		Credit credit = new Credit(new Date(), libelleCredit, montant);

		Session hib = sessionFactory.getCurrentSession();
		try {
//			hib.beginTransaction();
						
			Compte compteDebit = (Compte) hib.load(Compte.class,idCompteDebit);
			Compte compteCredit = (Compte) hib.load(Compte.class,idCompteCredit);
			
			compteDebit.getOperations().add(debit);
			compteCredit.getOperations().add(credit);
			
			hib.persist(debit);
			hib.persist(credit);
			
//			hib.getTransaction().commit();
			
			// Forcer le rechargement des compte pour mettre à jour les soldes
			hib.evict(compteDebit);
			hib.evict(compteCredit);
//			hib.clear();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			hib.getTransaction().rollback();
		}
	}

}
