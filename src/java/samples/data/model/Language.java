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
import samples.data.IInstallationCandidate;
import samples.data.model.IDataModel;

/**
 *
 * @author SargTeX
 */
public class Language implements IDataModel{
	
	private String code;
	private String name;
	private String description;
	private boolean existing = false;
	
	public Language() {}
	public Language(ResultSet rs) throws SQLException {this.initialize(rs);}
	private void initialize(ResultSet rs) throws SQLException {
		this.code = rs.getString("code");
		this.name = rs.getString("name");
		this.description = rs.getString("description");
	}
	
	public String getCode() {return this.code;}
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public Language setCode(String code) {this.code = code; return this;}
	public Language setName(String name) {this.name = name; return this;}
	public Language setDescription(String description) {this.description = description; return this;}

	public static Language[] getAll() throws SQLException {
		List<Language> languageList = new ArrayList<Language>();
		String query = "SELECT * FROM language";
		ResultSet rs = SqlDatabase.getInstance().fetch(query);
		while (rs.next()) {
			languageList.add(new Language(rs));
		}
		return languageList.toArray(new Language[]{});
	}
	
	@Override
	public boolean exists() {return this.existing;}

	@Override
	public Language create() throws SQLException {
		String query = "INSERT INTO language (code, name, description) VALUES (?, ?, ?)";
		SqlDatabase.getInstance().execute(query, code, name, description);
		existing = true;
		return this;
	}

	@Override
	public Language remove() throws SQLException {
		String query = "DELETE FROM language WHERE code = ?";
		SqlDatabase.getInstance().execute(query, code);	// TODO delete children?
		existing = false;
		return this;
	}

	@Override
	public Language read() throws SQLException {
		String query = "SELECT * FROM language WHERE code = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, code);
		if (rs.first()) {
			existing = true;
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public Language update() throws SQLException {
		String query = "UPDATE language SET name = ?, description = ? WHERE code = ?";
		SqlDatabase.getInstance().execute(query, name, description, code);
		return this;
	}

	@Override
	public IInstallationCandidate install() throws Exception {
		String query = "CREATE TABLE language ("
				+ "code VARCHAR( 255 ) NOT NULL PRIMARY KEY,"
				+ "name VARCHAR( 255 ) NOT NULL,"
				+ "description TEXT)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public IInstallationCandidate uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS language";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
	
	
}
