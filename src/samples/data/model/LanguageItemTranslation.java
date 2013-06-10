/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import samples.core.SqlDatabase;
import samples.data.IInstallationCandidate;
import samples.data.model.IDataModel;

/**
 *
 * @author SargTeX
 */
public class LanguageItemTranslation implements IDataModel {
	
	private int id;
	private String languageCode;
	private String itemName;
	private String translation;
	private boolean existing = false;
	
	public LanguageItemTranslation() {}
	public LanguageItemTranslation(ResultSet rs) throws SQLException {this.initialize(rs);}
	private void initialize(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		languageCode = rs.getString("languageCode");
		itemName = rs.getString("itemName");
		translation = rs.getString("translation");
		existing = true;
	}
	
	public int getId() {return this.id;}
	public String getLanguageCode() {return this.languageCode;}
	public String getItemName() {return itemName;}
	public String getTranslation() {return translation;}
	public LanguageItemTranslation setLanguageCode(String languageCode) {this.languageCode = languageCode; return this;}
	public LanguageItemTranslation setItemName(String itemName) {this.itemName = itemName; return this;}
	public LanguageItemTranslation setTranslation(String translation) {this.translation = translation; return this;}
	
	public static LanguageItemTranslation find(String clause, String... parameters) throws SQLException {
		String query = "SELECT * FROM language_item_translation WHERE "+clause+" LIMIT 1";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, parameters);
		if (rs.first()) return new LanguageItemTranslation(rs);
		return null;
	}

	@Override
	public boolean exists() {return this.existing;}

	@Override
	public LanguageItemTranslation create() throws SQLException {
		String query = "INSERT INTO language_item_translation (languageCode, itemName, translation) VALUES (?, ?, ?)";
		id = SqlDatabase.getInstance().execute(query, languageCode, itemName, translation);
		existing = true;
		return this;
	}

	@Override
	public LanguageItemTranslation remove() throws SQLException {
		String query = "DELETE FROM language_item_translation WHERE id = ?";
		SqlDatabase.getInstance().execute(query, id+"");
		existing = false;
		return this;
	}

	@Override
	public LanguageItemTranslation read() throws SQLException {
		String query = "SELECT * FROM language_item_translation WHERE id = ?";
		ResultSet rs = SqlDatabase.getInstance().fetch(query, id+"");
		if (rs.first()) {
			this.initialize(rs);
		}
		return this;
	}

	@Override
	public LanguageItemTranslation update() throws SQLException {
		String query = "UPDATE language_item_translation SET languageCode = ?, itemName = ?, translation = ? WHERE id = ?";
		SqlDatabase.getInstance().execute(query, languageCode, itemName, translation, id);
		return this;
	}

	@Override
	public IInstallationCandidate install() throws Exception {
		String query = "CREATE TABLE language_item_translation ("
				+ "id INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "languageCode VARCHAR( 255 ) NOT NULL,"
				+ "itemName VARCHAR( 255 ) NOT NULL,"
				+ "translation TEXT)";
		SqlDatabase.getInstance().execute(query);
		return this;
	}

	@Override
	public IInstallationCandidate uninstall() throws Exception {
		String query = "DROP TABLE IF EXISTS language_item_translation";
		SqlDatabase.getInstance().execute(query);
		return this;
	}
	
}
