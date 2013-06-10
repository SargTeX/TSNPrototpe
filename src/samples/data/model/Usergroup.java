/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	private int userCount = -1;
	private boolean existing = false;
	
	public Usergroup() {}
	public Usergroup(ResultSet rs) throws SQLException {
		this.initialize(rs);
	}
	
	private void initialize(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		name = rs.getString("name");
		description = rs.getString("description");
		userlistId = rs.getInt("userlistId");
	}
	
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public int getUserlistId() {return this.userlistId;}
	public Usergroup setId(int id) {this.id = id; return this;}
	public Usergroup setName(String name) {this.name = name; return this;}
	public Usergroup setDescription(String description) {this.description = description; return this;}
	public Usergroup setUserlistId(int userlistId) {this.userlistId = userlistId; return this;}

	public int getUserCount() throws SQLException {
		if (userCount < 0) {
			String query = "SELECT COUNT(*) AS count FROM user_to_userlist WHERE userlistId = ?";
			ResultSet rs = SqlDatabase.getInstance().fetch(query, userlistId+"");
			if (rs.first()) {
				this.userCount = rs.getInt("count");
			}
		}
		
		return userCount;
	}
	
	@Override
	public boolean exists() {return this.existing;}

	@Override
	public Usergroup create() throws SQLException {
		String query = "INSERT INTO usergroup (name, description, userlistId) VALUES (?, ?, ?)";
		id = SqlDatabase.getInstance().execute(query, name, description, userlistId);
		return this;
	}

	@Override
	public Usergroup remove() throws SQLException {
		String query = "DELETE FROM usergroup WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
		return this;
	}

	@Override
	public Usergroup read() throws SQLException {
		String query = "SELECT * FROM usergroup WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public Usergroup update() throws SQLException {
		String query = "UPDATE usergroup SET name = ?, description = ?, userlistId = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, name, description, userlistId, id);
		return this;
	}

	@Override
	public Usergroup install() throws Exception {
		String query = "CREATE TABLE usergroup ("
				+ "id INT ( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NOT NULL,"
				+ "description TEXT,"
				+ "userlistId INT( 11 ) NOT NULL DEFAULT 0)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public Usergroup uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS usergroup";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
	public static Usergroup[] getAll() throws SQLException {
		List<Usergroup> usergroups = new ArrayList<Usergroup>();
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM usergroup");
		while (rs.next()) {
			usergroups.add(new Usergroup(rs));
		}
		return usergroups.toArray(new Usergroup[]{});
	}
	
}
