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
public class Usergroup implements IDataModel {
	
	private int id;
	private String name;
	private String description;
	private int userlistId;
	private boolean existing = false;
	
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public int getUserlistId() {return this.userlistId;}
	public Usergroup setName(String name) {this.name = name; return this;}
	public Usergroup setDescription(String description) {this.description = description; return this;}
	public Usergroup setUserlistId(int userlistId) {this.userlistId = userlistId; return this;}

	@Override
	public boolean exists() {return this.existing;}

	@Override
	public void create() throws SQLException {
		String query = "INSERT INTO usergroup (name, description, userlistId) VALUES (?, ?, ?)";
		id = SqlDatabase.getInstance().execute(query, name, description, userlistId);
	}

	@Override
	public void remove() throws SQLException {
		String query = "DELETE FROM usergroup WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id);
	}

	@Override
	public void read() throws SQLException {
		String query = "SELECT * FROM usergroup WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			name = rs.getString("name");
			description = rs.getString("description");
			userlistId = rs.getInt("userlistId");
		}
	}

	@Override
	public void update() throws SQLException {
		String query = "UPDATE usergroup SET name = ?, description = ?, userlistId = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, name, description, userlistId, id);
	}

	@Override
	public void install() throws Exception {
		String query = "CREATE TABLE usergroup ("
				+ "id INT ( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NOT NULL,"
				+ "description TEXT,"
				+ "userlistId INT( 11 ) NOT NULL DEFAULT 0)";
		SqlDatabase.getInstance().execute(query);
	}

	@Override
	public void uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS usergroup";
		SqlDatabase.getInstance().execute(query);
	}
	
}
