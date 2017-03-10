package fr.eni.formation.banque.dao;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.eni.formation.banque.Compte;


public interface ICompteDAO {
	
	@Transactional(value=TxType.SUPPORTS)
	public Compte read(long idCompte);

}
