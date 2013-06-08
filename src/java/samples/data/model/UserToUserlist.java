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
public class UserToUserlist implements IDataModel {
	
	private int id;
	private int userId;
	private int userlistId;
	private boolean existing = false;

	public int getId() {return this.id;}
	public int getUserId() {return this.userId;}
	public int getUserlistId() {return this.userlistId;}
	public UserToUserlist setUserId(int userId) {this.userId = userId; return this;}
	public UserToUserlist setUserlistId(int userlistId) {this.userlistId = userlistId; return this;}
	
	@Override
	public boolean exists() {return this.existing;}

	@Override
	public void create() throws SQLException {
		String query = "INSERT INTO user_to_userlist (userId, userlistId) VALUES (?, ?)";
		id = SqlDatabase.getInstance().execute(query, userId, userlistId);
	}

	@Override
	public void remove() throws SQLException {
		String query = "DELETE FROM user_to_userlist WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
	}

	@Override
	public void read() throws SQLException {
		String query = "SELECT * FROM user_to_userlist WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			userId = rs.getInt("userId");
			userlistId = rs.getInt("userlistId");
		}
	}

	@Override
	public void update() throws SQLException {
		String query = "UPDATE user_to_userlist SET userId = ? AND userlistId = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, userId+"", userlistId, id);
	}

	@Override
	public void install() throws Exception {
		String query = "CREATE TABLE user_to_userlist ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "userId INT( 11 ) NOT NULL,"
				+ "userlistId INT( 11 ) NOT NULL)";
		SqlDatabase.getInstance().execute(query);
	}

	@Override
	public void uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS user_to_userlist";
		SqlDatabase.getInstance().execute(query);
	}
	
}
