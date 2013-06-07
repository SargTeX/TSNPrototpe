/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.SQLException;
import samples.data.SqlDatabase;

/**
 *
 * @author SargTeX
 */
public class User implements IDataModel {
	
	private String username;
	private String password;
	private String email;
	private int birthday;
	private String prename;
	private String surname;
	
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public String getEmail() {return this.email;}
	public int getBirthday() {return this.birthday;}
	public String getPrename() {return this.prename;}
	public String getSurname() {return this.surname;}
	
	public User setUsername(String username) {this.username = username; return this;}
	public User setPassword(String password) {this.password = password; return this;}
	public User setEmail(String emaiL) {this.email = email; return this;}
	public User setBirthday(int birthday) {this.birthday = birthday; return this;}
	public User setPrename(String prename) {this.prename = prename; return this;}
	public User setSurname(String surname) {this.surname = surname; return this;}
	
	@Override
	public void create() throws SQLException { // TODO throw exception or not?
		SqlDatabase.getInstance().execute("INSERT INTO user (username, password, email, birthday, prename, surname) VALUES (?, ?, ?, ?, ?, ?)", username, password, email, birthday, prename, surname);
	}
	
}
