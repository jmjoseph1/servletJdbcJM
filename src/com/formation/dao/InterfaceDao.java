package com.formation.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.formation.model.Client;

public interface InterfaceDao {
	
	void save (Client client);
	Client findById(int id);
	void update(Client client);
	void delete(Client client);
	Connection seConnecter() throws ClassNotFoundException, SQLException;
}
