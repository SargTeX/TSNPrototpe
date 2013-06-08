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

	@Override
	public boolean exists() {return this.existing;}

	@Override
	public void create() throws SQLException {
		String query = "INSERT INTO userlist (name) VALUES (?)";
		id = SqlDatabase.getInstance().execute(query, name);
	}

	@Override
	public void remove() throws SQLException {
		String query = "DELETE FROM userlist WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
	}

	@Override
	public void read() throws SQLException {
		String query = "SELECT * FROM userlist WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			this.name = rs.getString("name");
		}
	}

	@Override
	public void update() throws SQLException {
		String query = "UPDATE userlist SET name = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, name, id);
	}

	@Override
	public void install() throws Exception {
		String query = "CREATE TABLE userlist ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NULL DEFAULT NULL)";
		SqlDatabase.getInstance().execute(query);
	}

	@Override
	public void uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS userlist";
		SqlDatabase.getInstance().execute(query);
	}
	
}
