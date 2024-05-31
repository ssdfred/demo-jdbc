package jdbc;

import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import jdbc.dao.FournisseurDaoJdbc;
import jdbc.entites.Fournisseur;

public class TestFournisseurDaoJdbc1 {

	public static void main(String[] args) {
		
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		String user = config.getString("database.user");
		String password = config.getString("database.password");

	       
        // Création d'un objet Fournisseur
        Fournisseur fournisseur = new Fournisseur(5, "toutmatériaux");
        
        // Utilisation de l'instance de FournisseurDaoJdbc sans arguments
        FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
        
        // Insertion du fournisseur dans la base de données
        fournisseurDaoJdbc.insert(fournisseur);
        
        // Extraction de la liste des fournisseurs après l'insertion
        List<Fournisseur> listeFour = fournisseurDaoJdbc.extraire();
        // Affichage de la liste des fournisseurs
        for (Fournisseur f : listeFour) {
            System.out.println(f);
        }
        
        // Mise à jour du fournisseur
        fournisseurDaoJdbc.update("toutmatériaux", "nouveau_nom");
        
        // Extraction de la liste des fournisseurs après la mise à jour
        listeFour = fournisseurDaoJdbc.extraire();
        // Affichage de la liste des fournisseurs
        for (Fournisseur f : listeFour) {
            System.out.println(f);
        }
        
        // Suppression du fournisseur
        fournisseurDaoJdbc.delete(fournisseur);
        
        // Extraction de la liste des fournisseurs après la suppression
        listeFour = fournisseurDaoJdbc.extraire();
        // Affichage de la liste des fournisseurs
        for (Fournisseur f : listeFour) {
            System.out.println(f);
        }
        

    }
}
