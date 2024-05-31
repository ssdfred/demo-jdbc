package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	private String url;
	private String user;
	private String password;
	


	@Override
	public List<Fournisseur> extraire() {
		try {
			ResourceBundle config = ResourceBundle.getBundle("config");
			 url = config.getString("database.url");
			 user = config.getString("database.user");
			 password = config.getString("database.password");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnection = DriverManager.getConnection(url, user, password);
			Statement monStatement = maConnection.createStatement();
			ResultSet curseur = monStatement.executeQuery("select * from FOURNISSEUR");
			List<Fournisseur> listeFourni = new ArrayList<>();
			while (curseur.next()) {
				Integer id = curseur.getInt("id");
				String nom = curseur.getString("nom");
				listeFourni.add(new Fournisseur(id, nom));
			}
			curseur.close();
			monStatement.close();
			maConnection.close();
			return listeFourni;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Fournisseur fourni) {
	    try (Connection maConnection = DriverManager.getConnection(url, user, password)) {
	        String sql = "INSERT INTO FOURNISSEUR(id, nom) VALUES(?,?)";
	        try (PreparedStatement pstmt = maConnection.prepareStatement(sql)) {
	            pstmt.setInt(1, fourni.getId());
	            pstmt.setString(2, fourni.getNom());
	            int nb = pstmt.executeUpdate();
	            System.out.println(nb > 0? "Insertion réussie" : "Echec de l'insertion");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'insertion dans la base de données: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnection = DriverManager.getConnection(url, user, password);
			Statement monStatement = maConnection.createStatement();
			int nb = monStatement
					.executeUpdate("update FOURNISSEUR set nom='" + nouveauNom + "' where nom='" + ancienNom + "'");
			monStatement.close();
			maConnection.close();
			return nb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection maConnection = DriverManager.getConnection(url, user, password);
			Statement monStatement = maConnection.createStatement();
			int nb = monStatement.executeUpdate("delete from FOURNISSEUR where id=" + fournisseur.getId());
			if (nb != 0) {
				return true;
			}
			monStatement.close();
			maConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}