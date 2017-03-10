package fr.eni.formation.banque.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.eni.formation.banque.Client;

public interface IClientDAO {

	@Transactional(TxType.REQUIRED)
	public void create(String nom, String prenom);

	@Transactional(value=TxType.SUPPORTS)
	public List<Client> readAll();
	@Transactional(value=TxType.SUPPORTS)
	public Client read(long id);
	@Transactional(value=TxType.SUPPORTS)
	public List<Client> read(String critere);

	@Transactional(TxType.REQUIRED)
	public void update(long idClient, String nom, String prenom);

	@Transactional(TxType.REQUIRED)
	public void virement(long idCompteDebit, String libelleDebit, long idCompteCredit, String libelleCredit, double montant);
	
}
