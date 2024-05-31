package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc2 {

	private final String url;
	private final String user;
	private final String password;

	public FournisseurDaoJdbc2(String url, String user, String password) {

		this.url = "";
		this.user = "";
		this.password = "";
		ResourceBundle config = ResourceBundle.getBundle("config");
		url = config.getString("database.url");
		user = config.getString("database.user");
		password = config.getString("database.password");
	}

	public void insert(Fournisseur fournisseur) {
		Connection maConnection = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FOURNISSEUR(id, nom) VALUES(?, ?)";
		try {

			maConnection = DriverManager.getConnection(url, user, password);
			pstmt = maConnection.prepareStatement(sql);
			pstmt.setInt(1, fournisseur.getId());
			pstmt.setString(2, fournisseur.getNom());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (maConnection != null) {
				try {
					maConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Fournisseur> extraire() {
		List<Fournisseur> listeFour = new ArrayList<>();
		String sql = "SELECT * FROM FOURNISSEUR";
		try (Connection maConnection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = maConnection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				listeFour.add(new Fournisseur(id, nom));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeFour;
	}

	public void update(int id, String nouveauNom) {
		String sql = "UPDATE FOURNISSEUR SET nom=? WHERE id=?";
		try (Connection maConnection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = maConnection.prepareStatement(sql)) {
			pstmt.setString(1, nouveauNom);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Fournisseur fournisseur) {
		String sql = "DELETE FROM FOURNISSEUR WHERE id=?";
		try (Connection maConnection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = maConnection.prepareStatement(sql)) {
			pstmt.setInt(1, fournisseur.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
