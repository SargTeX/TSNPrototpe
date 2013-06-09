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
import samples.exception.PermissionDeniedException;

/**
 *
 * @author SargTeX
 */
public class Permission implements IDataModel {
	
	private String name;
	private String description;
	private String defaultValue;
	private String type;
	private PermissionValue value;
	private boolean existing = false;
	
	public Permission() {}
	public Permission(ResultSet rs) throws SQLException {this.initialize(rs);}
	private void initialize(ResultSet rs) throws SQLException {
		existing = true;
		this.name = rs.getString("name");
		this.description = rs.getString("description");
		this.defaultValue = rs.getString("defaultValue");
		this.type = rs.getString("type");
	}
	
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
	
	protected void loadValue(int id, String type) throws SQLException {
		this.value = PermissionValue.find("id = ? AND type = ?", id+"", type);
	}
	
	public static Permission get(String name) throws SQLException { // TODO caching - if updated then reload (events)
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM permission WHERE name = ?", name);
		return new Permission(rs);
	}
	
	public static void check(String name) throws PermissionDeniedException, SQLException {
		Permission permission = new Permission().setName(name).read();
		if (!permission.exists() || !permission.getType().equals("boolean") || permission.getDefaultValue().equals("1")) return;
		
		// get value
		PermissionValue value = PermissionValue.find("permissionName = ? AND objectId = ? AND objectType = 'user'", name, Session.getInstance().getUserId()+"");
		if (value == null) value = PermissionValue.find("permissionName = ? AND objectId = ? AND objectType = 'usergroup'", name, Session.getInstance().getUser().getUsergroupId()+"");
		if (value == null || !value.getValue().equals("1")) throw new PermissionDeniedException("Missing permission: "+name);
	}
	
	public static String getValue(String name, int id, String type) throws SQLException {
		PermissionValue value = PermissionValue.find("objectId = ? AND type = ? AND name = ?", id+"", type, name);
		if (value != null) return value.getValue();
		return Permission.get(name).getDefaultValue();
	}
	
	public static PermissionValue[] getValues(int id, String type) throws SQLException {
		List<PermissionValue> values = new ArrayList<PermissionValue>();
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM permission_value WHERE objectId = ? and objectType = ?", id+"", type);
		while (rs.next()) {
			values.add(new PermissionValue(rs));
		}
		return values.toArray(new PermissionValue[]{});
	}
	
	public static Permission[] getPermissions() throws SQLException {
		List<Permission> permissions = new ArrayList<Permission>();
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM permission");
		while (rs.next()) {
			permissions.add(new Permission(rs));
		}
		return permissions.toArray(new Permission[]{});
	}
	
	public static Permission[] getPermissions(int id, String type) throws SQLException {
		Permission[] permissions = getPermissions();
		for (Permission permission : permissions) {
			permission.loadValue(id, type);
		}
		return permissions;
	}

	@Override
	public Permission create() throws SQLException {
		String query = "INSERT INTO permission (name, description, defaultValue, type) VALUES (?, ?, ?, ?)";
		SqlDatabase.getInstance().execute(query, name, description, defaultValue, type);
		existing = true;
		return this;
	}

	@Override
	public Permission remove() throws SQLException {
		String query = "DELETE FROM permission WHERE name = ?";
		SqlDatabase.getInstance().execute(query, name);
		existing = false;
		return this;
	}

	@Override
	public Permission read() throws SQLException {
		String query = "SELECT * FROM permission WHERE name = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, name);
		if (rs.first()) {
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public Permission update() throws SQLException {
		String query = "UPDATE permission SET description = ?, defaultValue = ?, type = ? WHERE name = ?";
		SqlDatabase.getInstance().execute(query, description, defaultValue, type, name);
		return this;
	}

	@Override
	public Permission install() throws Exception {
		String query = "CREATE TABLE permission ("
				+ "name VARCHAR( 255 ) NOT NULL PRIMARY KEY,"
				+ "description TEXT,"
				+ "defaultValue TEXT,"
				+ "type VARCHAR( 255 ) NOT NULL)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public Permission uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS permission";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
}
