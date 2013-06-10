/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import samples.core.SqlDatabase;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class PermissionValue implements IDataModel {
	
	private int id;
	private String value;
	private String permissionName;
	private int objectId;
	private String objectType;
	private boolean existing = false;
	
	public PermissionValue() {}
	public PermissionValue(ResultSet rs) throws SQLException {
		this.initialize(rs);
	}
	private void initialize(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		value = rs.getString("value");
		permissionName = rs.getString("permissionName");
		objectId = rs.getInt("objectId");
		objectType = rs.getString("objectType");
	}
	
	public int getId() {return this.id;}
	public String getValue() {return this.value;}
	public String getPermissionName() {return this.permissionName;}
	public int getObjectId() {return this.objectId;}
	public String getObjectType() {return this.objectType;}
	public PermissionValue setValue(String value) {this.value = value; return this;}
	public PermissionValue setPermissionName(String permissionName) {this.permissionName = permissionName; return this;}
	public PermissionValue setObjectId(int objectId) {this.objectId = objectId; return this;}
	public PermissionValue setObjectType(String objectType) {this.objectType = objectType; return this;}
	
	public static PermissionValue find(String clause, String... parameters) throws SQLException {
		String query = "SELECT * FROM permission_value";
		if (!StringUtil.isEmpty(clause)) query += " WHERE "+clause;
		ResultSet rs = SqlDatabase.getInstance().fetch(query, parameters);
		if (rs.first()) return new PermissionValue(rs);
		return null;
	}
	
	@Override
	public boolean exists() {return existing;}

	@Override
	public PermissionValue create() throws SQLException {
		String query = "INSERT INTO permission_value (value, permissionName, objectId, objectType) VALUES (?, ?, ?, ?)";
		this.id = SqlDatabase.getInstance().execute(query, value, permissionName, objectId, objectType);
		return this;
	}

	@Override
	public PermissionValue remove() throws SQLException {
		String query = "DELETE FROM permission_value WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
		return this;
	}

	@Override
	public PermissionValue read() throws SQLException {
		String query = "SELECT * FROM permission_value WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			existing = true;
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public PermissionValue update() throws SQLException {
		String query = "UPDATE permission_value SET value = ?, permissionName = ?, objectId = ?, objectType = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, value, permissionName, objectId, objectType, id);
		return this;
	}

	@Override
	public PermissionValue install() throws Exception {
		String query = "CREATE TABLE permission_value ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "value TEXT,"
				+ "permissionName VARCHAR( 255 ) NOT NULL,"
				+ "objectId INT( 11 ) NOT NULL,"
				+ "objectType VARCHAR( 255 ) NOT NULL)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public PermissionValue uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS permission_value";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
}
