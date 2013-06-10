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
import samples.data.model.Permission;
import samples.util.DebugUtil;

/**
 *
 * @author SargTeX
 */
public class PermissionListController extends Controller {
	
	private Permission[] permissions;
	
	@Override
	public void readData() {
		try {
			this.permissions = Permission.getPermissions();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "permissionList");
	}
	
	@Override
	public void assignVariables() {
		this.set("permissions", permissions);
	}
	
}
