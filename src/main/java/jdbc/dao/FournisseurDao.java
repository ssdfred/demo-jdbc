package jdbc.dao;

import java.util.List;

import jdbc.entites.Fournisseur;

/**
 * permet de normaliser les méthodes de DAO
 */
public interface FournisseurDao {
	List<Fournisseur> extraire();
	void insert(Fournisseur fournisseur);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Fournisseur fournisseur);
}
