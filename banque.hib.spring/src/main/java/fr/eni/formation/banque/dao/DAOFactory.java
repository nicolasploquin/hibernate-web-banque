package fr.eni.formation.banque.dao;

public class DAOFactory {
	
	private IClientDAO clientDAO = null;
	private ICompteDAO compteDAO = null;
	private IOperationDAO operationDAO = null;
	
	public IClientDAO getClientDAO() {
		return clientDAO;
	}
	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	public ICompteDAO getCompteDAO() {
		return compteDAO;
	}
	public void setCompteDAO(ICompteDAO compteDAO) {
		this.compteDAO = compteDAO;
	}
	
	public IOperationDAO getOperationDAO() {
		return operationDAO;
	}
	public void setOperationDAO(IOperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}
}
