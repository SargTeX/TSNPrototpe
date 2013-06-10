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
public class LanguageItem implements IDataModel {
	
	private String name;
	private boolean existing = false;
	private LanguageItemTranslation translation;
	
	public LanguageItem() {}
	public LanguageItem(ResultSet rs) throws SQLException {this.initialize(rs);}
	private void initialize(ResultSet rs) throws SQLException {
		name = rs.getString("name");
	}
	
	public String getName() {return this.name;}
	public LanguageItem setName(String name) {this.name = name; return this;}
	
	public void loadTranslation(String languageCode) throws SQLException {
		if (translation != null) return;
		translation = LanguageItemTranslation.find("itemName = ? AND languageCode = ?", name, languageCode);
		if (translation == null) translation = new LanguageItemTranslation().setItemName(name).setLanguageCode(languageCode).setTranslation("").create();
	}
	
	public static LanguageItem[] getAll() throws SQLException {
		List<LanguageItem> items = new ArrayList<LanguageItem>();
		String query = "SELECT * FROM language_item";
		ResultSet rs = SqlDatabase.getInstance().fetch(query);
		while (rs.next()) items.add(new LanguageItem(rs));
		return items.toArray(new LanguageItem[]{});
	}

	@Override
	public boolean exists() {return this.existing;}

	@Override
	public IDataModel create() throws SQLException {
		String query = "INSERT INTO language_item (name) VALUES (?)";
		SqlDatabase.getInstance().execute(query, name);
		existing = true;
		return this;
	}

	@Override
	public IDataModel remove() throws SQLException {
		String query = "DELETE FROM language_item WHERE name = ?";
		SqlDatabase.getInstance().execute(query, name); // TODO delete sub items?
		existing = false;
		return this;
	}

	@Override
	public IDataModel read() throws SQLException {
		String query = "SELECT * FROM language_item WHERE name = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, name);
		if (rs.first()) {
			existing = true;
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public IDataModel update() throws SQLException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public IInstallationCandidate install() throws Exception {
		String query = "CREATE TABLE language_item ("
				+ "name VARCHAR( 255 ) NOT NULL PRIMARY KEY)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public IInstallationCandidate uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS language_item";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
}
