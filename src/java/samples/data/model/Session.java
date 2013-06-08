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
public class Session implements IDataModel{

	//<editor-fold defaultstate="collapsed" desc="Attributes">
	private String sessionId;
	private int userId;
	private static Session instance;
	private boolean existing = false;
	//</editor-fold>
	
	//<editor-fold defaultstate="collapsed" desc="Getters and setters">
	public String getSessionId() {return this.sessionId;}
	public int getUserId() {return this.userId;}
	public Session setSessionId(String sessionId) {this.sessionId = sessionId; return this;}
	public Session setUserId(int userId) {this.userId = userId; return this;}
	public boolean exists() {return this.existing;}
	//</editor-fold>
	
	public static void initialize(String sessionId) throws SQLException {
		instance = new Session();
		instance.setSessionId(sessionId);
		instance.read();
		if (!instance.exists()) {
			instance.create();
			instance.read();
		}
	}
	
	public static Session getInstance() {
		return instance;
	}
	
	@Override
	public void create() throws SQLException {
		SqlDatabase.getInstance().execute("INSERT INTO session (sessionId, userId) VALUES (?, ?)", this.sessionId, this.userId);
		this.existing = true;
	}

	@Override
	public void install() throws Exception {
		SqlDatabase.getInstance().execute("CREATE TABLE session ("
				+ "sessionId VARCHAR( 255 ) NOT NULL PRIMARY KEY,"
				+ "userId INT( 11 ) NOT NULL )");
	}

	@Override
	public void uninstall() throws Exception {
		SqlDatabase.getInstance().execute("DROP TABLE IF EXISTS session");
	}

	@Override
	public void remove() throws SQLException {
		SqlDatabase.getInstance().execute("REMOVE FROM session WHERE sessionId = ?", this.sessionId);
	}

	@Override
	public void read() throws SQLException {
		ResultSet rs = SqlDatabase.getInstance().fetch("SELECT * FROM session WHERE sessionId = ?", this.sessionId);
		if (rs.first()) {
			this.userId = rs.getInt("userId");
			this.existing = true;
		}
	}

	@Override
	public void update() throws SQLException {
		SqlDatabase.getInstance().execute("UPDATE session SET userId = ? WHERE sessionId = ?", this.userId, this.sessionId);
	}
	
}
