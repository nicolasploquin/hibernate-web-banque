/**
 * 
 */
package fr.eni.formation.banque;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Nicolas Ploquin - ENI Service
 *
 */
@Entity
@Table(name="Client")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 5226166059366060409L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idClient;
	
	@Column(nullable=false)
	private String nom;
	
	// par défaut @Basic (optionnel), @Transient si non persitent
	@Basic()
	@Column(name="prenom")
	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Set<Compte> comptes = new HashSet<Compte>();
	
//  Pour transformer la relation Client - Compte en * *	
//	@ManyToMany
//	@JoinTable(
//			name="client_compte",
//			joinColumns={ @JoinColumn(name="idClient") },
//			inverseJoinColumns={ @JoinColumn(name="idCompte") }
//	)
//	private Set<Compte> comptes = new HashSet<>();
	
	public Client(){
		nom = "";
		prenom = "";
	}
	
	/**
	 * @param nom
	 * @param prenom
	 */
	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString(){
		String result = prenom + " " + nom + " :\n";
		return result;
	}
	
}


