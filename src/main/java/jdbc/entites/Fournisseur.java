package jdbc.entites;

import java.sql.ResultSet;

/**
 * Fournisseur
 */
public class Fournisseur {
	protected int id;
	protected String nom;
	/** Constructeur
	 * 
	 */
	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	/** Getter pour id
	 * @return the id 
	*/
	public int getId() {
		return id;
	}
	/** Setter pour id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** Getter pour nom
	 * @return the nom 
	*/
	public String getNom() {
		return nom;
	}
	/** Setter pour nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return id+"-"+nom;
	}

	
	

}
