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
	private int objectId;
	private String objectType;
	private boolean existing = false;
	
	public Userlist() {}
	public Userlist(ResultSet rs) throws SQLException {
		this.initialize(rs);
	}
	private void initialize(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		this.name = rs.getString("name");
		this.objectId = rs.getInt("objectId");
		objectType = rs.getString("objectType");
	}
	
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public int getObjectId() {return this.objectId;}
	public String getObjectType() {return this.objectType;}
	public Userlist setId(int id) {this.id = id; return this;}
	public Userlist setName(String name) {this.name = name; return this;}
	public Userlist setObjectId(int objectId) {this.objectId = objectId; return this;}
	public Userlist setObjectType(String objectType) {this.objectType = objectType; return this;}

	public static Userlist findUser(int userId, int objectId, String objectType) throws SQLException {
		String query = "SELECT * FROM userlist, user_to_userlist WHERE userlist.objectId = ? AND userlist.objectType = ? AND user_to_userlist.userId = ? AND user_to_userlist.userlistId = userlist.id";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, objectId+"", objectType, userId+"");
		if (rs.first()) {
			return new Userlist(rs);
		}
		return null;
	}
	
	@Override
	public boolean exists() {return this.existing;}

	@Override
	public Userlist create() throws SQLException {
		String query = "INSERT INTO userlist (name, objectId, objectType) VALUES (?, ?, ?)";
		id = SqlDatabase.getInstance().execute(query, name, objectId, objectType);
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
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public Userlist update() throws SQLException {
		String query = "UPDATE userlist SET name = ?, objectId = ?, objectType = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, name, objectId, objectType, id);
		return this;
	}

	@Override
	public Userlist install() throws Exception {
		String query = "CREATE TABLE userlist ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NULL DEFAULT NULL,"
				+ "objectId INT( 11 ) NOT NULL,"
				+ "objectType VARCHAR( 255 ) NOT NULL)";
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
