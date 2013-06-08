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
	
	public User setUsername(String username) {this.username = username; return this;}
	public User setPassword(String password) {this.password = password; return this;}
	public User setEmail(String email) {this.email = email; return this;}
	public User setBirthday(int birthday) {this.birthday = birthday; return this;}
	public User setPrename(String prename) {this.prename = prename; return this;}
	public User setSurname(String surname) {this.surname = surname; return this;}
	
	@Override
	public void create() throws SQLException { // TODO throw exception or not?
		id = SqlDatabase.getInstance().execute("INSERT INTO user (username, password, email, birthday, prename, surname) VALUES (?, ?, ?, ?, ?, ?)", username, password, email, birthday, prename, surname);
		existing = true;
	}
	
	@Override
	public void install() throws SQLException {
		SqlDatabase.getInstance().execute("CREATE TABLE user ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "username VARCHAR( 255 ) NOT NULL,"
				+ "password VARCHAR( 255 ) NOT NULL,"
				+ "email VARCHAR( 255 ) NOT NULL,"
				+ "birthday INT( 11 ) NOT NULL,"
				+ "prename VARCHAR( 255 ) NOT NULL,"
				+ "surname VARCHAR( 255 ) NOT NULL)");
	}

	@Override
	public void uninstall() throws SQLException {
		SqlDatabase.getInstance().execute("DROP TABLE IF EXISTS user");
	}

	@Override
	public void remove() throws SQLException {
		SqlDatabase.getInstance().execute("REMOVE FROM user WHERE id = ?", this.id);
	}

	@Override
	public void read() throws SQLException {
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM user WHERE id = ?", id+"");
		
		if (rs.first()) {
			existing = true;
			
		}
	}

	@Override
	public void update() throws SQLException {
		SqlDatabase.getInstance().execute("UPDATE user SET username = ?, password = ?, email = ?, birthday = ?, prename = ?, surname = ? WHERE id = ?",
				username, password, email, birthday, prename, surname, id);
	}
	
}
