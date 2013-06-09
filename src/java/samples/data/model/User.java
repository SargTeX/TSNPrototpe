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
public class User implements IDataModel {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private int birthday;
	private String prename;
	private String surname;
	private boolean existing = false;
	
	public int getId() {return this.id;}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public String getEmail() {return this.email;}
	public int getBirthday() {return this.birthday;}
	public String getPrename() {return this.prename;}
	public String getSurname() {return this.surname;}
	public boolean exists() {return this.existing;}
	public int getUsergroupId() throws SQLException {
		String query = "SELECT usergroup.id FROM usergroup, userlist, user_to_userlist WHERE user_to_userlist.userId = ? AND userlist.id = user_to_userlist.userId AND userlist.objectType = 'usergroup' AND userlist.objectId = usergroup.id LIMIT 1";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			return rs.getInt("usergroup.id");
		}
		return 0;
	}
	
	public User setId(int id) {this.id = id; return this;}
	public User setUsername(String username) {this.username = username; return this;}
	public User setPassword(String password) {this.password = password; return this;}
	public User setEmail(String email) {this.email = email; return this;}
	public User setBirthday(int birthday) {this.birthday = birthday; return this;}
	public User setPrename(String prename) {this.prename = prename; return this;}
	public User setSurname(String surname) {this.surname = surname; return this;}
	
	@Override
	public User create() throws SQLException { // TODO throw exception or not?
		id = SqlDatabase.getInstance().execute("INSERT INTO user (username, password, email, birthday, prename, surname) VALUES (?, ?, ?, ?, ?, ?)", username, password, email, birthday, prename, surname);
		existing = true;
		return this;
	}
	
	@Override
	public User install() throws SQLException {
		SqlDatabase.getInstance().execute("CREATE TABLE user ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "username VARCHAR( 255 ) NOT NULL,"
				+ "password VARCHAR( 255 ) NOT NULL,"
				+ "email VARCHAR( 255 ) NOT NULL,"
				+ "birthday INT( 11 ) NOT NULL,"
				+ "prename VARCHAR( 255 ) NOT NULL,"
				+ "surname VARCHAR( 255 ) NOT NULL)");
		return this;
	}

	@Override
	public User uninstall() throws SQLException {
		SqlDatabase.getInstance().execute("DROP TABLE IF EXISTS user");
		return this;
	}

	@Override
	public User remove() throws SQLException {
		SqlDatabase.getInstance().execute("REMOVE FROM user WHERE id = ?", this.id);
		return this;
	}

	@Override
	public User read() throws SQLException {
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM user WHERE id = ?", id+"");
		
		if (rs.first()) {
			existing = true;
			this.username = rs.getString("username");
			this.password = rs.getString("password");
			this.email = rs.getString("email");
			this.birthday = rs.getInt("birthday");
			this.prename = rs.getString("prename");
			this.surname = rs.getString("surname");
		}
		return this;
	}

	@Override
	public User update() throws SQLException {
		SqlDatabase.getInstance().execute("UPDATE user SET username = ?, password = ?, email = ?, birthday = ?, prename = ?, surname = ? WHERE id = ?",
				username, password, email, birthday, prename, surname, id);
		return this;
	}
	
}
