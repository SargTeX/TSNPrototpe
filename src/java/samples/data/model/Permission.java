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
public class Permission implements IDataModel {
	
	private String name;
	private String description;
	private String defaultValue;
	private String type;
	private boolean existing = false;
	
	@Override
	public boolean exists() {return this.existing;}
	
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public String getDefaultValue() {return this.defaultValue;}
	public String getType() {return this.type;}
	public Permission setName(String name) {this.name = name; return this;}
	public Permission setDescription(String description) {this.description = description; return this;}
	public Permission setDefaultValue(String defaultValue) {this.defaultValue = defaultValue; return this;}
	public Permission setType(String type) {this.type = type; return this;}

	@Override
	public void create() throws SQLException {
		String query = "INSERT INTO permission (name, description, defaultValue, type) VALUES (?, ?, ?, ?)";
		SqlDatabase.getInstance().execute(query, name, description, defaultValue, type);
	}

	@Override
	public void remove() throws SQLException {
		String query = "DELETE FROM permission WHERE name = ?";
		SqlDatabase.getInstance().execute(query, name);
	}

	@Override
	public void read() throws SQLException {
		String query = "SELECT * FROM permission WHERE name = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, name);
		if (rs.first()) {
			existing = true;
			this.description = rs.getString("description");
			this.defaultValue = rs.getString("defaultValue");
			this.type = rs.getString("type");
		}
	}

	@Override
	public void update() throws SQLException {
		String query = "UPDATE permission SET description = ?, defaultValue = ?, type = ? WHERE name = ?";
		SqlDatabase.getInstance().execute(query, description, defaultValue, type, name);
	}

	@Override
	public void install() throws Exception {
		String query = "CREATE TABLE permission ("
				+ "name VARCHAR( 255 ) NOT NULL PRIMARY KEY,"
				+ "description TEXT,"
				+ "defaultValue TEXT,"
				+ "type VARCHAR( 255 ) NOT NULL)";
		SqlDatabase.getInstance().execute(query);
	}

	@Override
	public void uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS permission";
		SqlDatabase.getInstance().execute(query);
	}
	
}
