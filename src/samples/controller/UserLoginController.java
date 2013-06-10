/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import samples.core.SqlDatabase;
import samples.data.model.Session;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class UserLoginController extends Controller {
	
	private String username;
	private String password;
	private int userId;
	
	@Override
	public void readParameters() {
		this.username = this.getParam("username");
		this.password = this.getParam("password");
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(this.username)) this.addError("input.username.empty");
		if (StringUtil.isEmpty(this.password)) this.addError("input.password.empty");
		
		// check credentials and login
		try {
			ResultSet rs = SqlDatabase.getInstance().fetch("SELECT id FROM user WHERE username = ? AND password = ?", this.username, this.password);
			if (!rs.first()) this.addError("input.credentials.wrong");
			else {
				this.userId = rs.getInt("id");
			}
		}catch (SQLException ex) {
			this.addError("error.credentials");
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void save() {
		try {
			// login
			Session.getInstance().setUserId(userId).update();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "userLogin");
	}
	
	@Override
	public void assignVariables() {
		this.set("username", username);
		this.set("password", password);
	}
	
}
