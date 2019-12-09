package com.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.formation.model.Client;

public class Dao implements InterfaceDao {

	@Override
	public void save(Client client) {
		PreparedStatement st = null;
		Connection cn = null;

		try {
			// Connection à la bdd
			cn = seConnecter();

			// ecrire une requete
			String sql = "INSERT INTO client (nom,prenom, age) VALUES (?,?,?)";
			// creation du statement
			st = cn.prepareStatement(sql);
			st.setString(1, client.getNom());
			st.setString(2, client.getPrenom());
			st.setInt(3, client.getAge());
			// execution de la requete
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Client findById(int id) {
		Client client = new Client();
		PreparedStatement st = null;
		Connection cn = null;
		ResultSet rs = null;

		try {
			cn = seConnecter();
			
			String sql = "SELECT * FROM client WHERE id=?";
			st = cn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			while(rs.next()) {
						
			client.setAge(rs.getInt("age"));
			client.setNom(rs.getString("nom"));
			client.setPrenom(rs.getString("prenom"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return client;

	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection seConnecter() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/vente";
		String login = "root";
		String pwd = "";
		Connection cn = null;

		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection(url, login, pwd);
		return cn;
	}

	public static void main(String[] args) {
		Client client = new Client("bob", "prenom", 20);
		//System.out.println(client);

		Dao dao = new Dao();
		//dao.save(client);
		System.out.println(dao.findById(1));

	}
}
