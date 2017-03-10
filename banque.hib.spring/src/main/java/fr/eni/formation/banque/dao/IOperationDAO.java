package fr.eni.formation.banque.dao;


import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.eni.formation.banque.Operation;


public interface IOperationDAO {

	@Transactional(TxType.REQUIRED)
	public Operation create(long idCompte, Date date, String libelle, double montant, String type);

}
