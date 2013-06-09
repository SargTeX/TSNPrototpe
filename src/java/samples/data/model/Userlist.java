/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import samples.core.SqlDatabase;

/**
 *
 * @author SargTeX
 */
public class Userlist implements IDataModel {
	
	private int id;
	private String name;
	private boolean existing = false;
	
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public Userlist setName(String name) {this.name = name; return this;}

	@Override
	public boolean exists() {return this.existing;}

	@Override
	public Userlist create() throws SQLException {
		String query = "INSERT INTO userlist (name) VALUES (?)";
		id = SqlDatabase.getInstance().execute(query, name);
		return this;
	}

	@Override
	public Userlist remove() throws SQLException {
		String query = "DELETE FROM userlist WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
		return this;
	}

	@Override
	public Userlist read() throws SQLException {
		String query = "SELECT * FROM userlist WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			this.name = rs.getString("name");
		}
		return this;
	}

	@Override
	public Userlist update() throws SQLException {
		String query = "UPDATE userlist SET name = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, name, id);
		return this;
	}

	@Override
	public Userlist install() throws Exception {
		String query = "CREATE TABLE userlist ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NULL DEFAULT NULL)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public Userlist uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS userlist";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
}
