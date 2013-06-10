/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.data.model.Usergroup;
import samples.util.DebugUtil;

/**
 *
 * @author SargTeX
 */
public class UsergroupListController extends Controller {
	
	private Usergroup[] usergroups;
	
	@Override
	public void readData() {
		try {
			usergroups = Usergroup.getAll();
			
			// load user count
			for (Usergroup usergroup : usergroups) {
				usergroup.getUserCount();
			}
		} catch (SQLException ex) {
			this.addError("technical.sql");
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignVariables() {
		this.set("usergroups", usergroups);
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "usergroupList");
	}
	
	
	
}
