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
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class PermissionDeleteController extends Controller {
	
	private String name;
	private Permission permission;
	
	@Override
	public void readParameters() {
		this.name = this.getParam("name");
	}
	
	@Override
	public void readData() {
		try {
			this.permission = new Permission().setName(name).read();
		} catch(SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
		else if (!permission.exists()) this.addError("input.name.wrong");
	}
	
	@Override
	public void save() {
		try {
			permission.remove();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
}
