/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Nicolas Ploquin - ENI Service
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
//@DiscriminatorValue("Operation")
public abstract class Operation implements Serializable {
	
	private static final long serialVersionUID = -3090580097072024643L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long idOperation;
	protected Date date;
	protected String libelle;
	protected double montant;

//	Pour définir une relation bidirectionnelle entre Compte et Operation
//	@ManyToOne
//	@JoinColumn(name="idCompte")
//	private Compte compte;
	
	public Operation(){
		this.date = new Date();
		this.libelle = "";
		this.montant = 0.0;
	}

	public Operation(Date date, String libelle, double montant){
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
	}
	
	public long getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(long idOperation) {
		this.idOperation = idOperation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

//	public Compte getCompte() {
//		return compte;
//	}
//
//	public void setCompte(Compte compte) {
//		this.compte = compte;
//	}
	
	@Override
	public String toString() {
		return date + " : " + libelle + "\t" + montant + "\n";
	}

}
