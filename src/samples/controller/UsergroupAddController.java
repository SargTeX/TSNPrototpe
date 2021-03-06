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
import samples.data.model.Usergroup;
import samples.data.model.Userlist;
import samples.exception.PermissionDeniedException;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class UsergroupAddController extends Controller {
	
	private String name;
	private String description;
	
	@Override
	public void readParameters() {
		this.name = this.getParam("name");
		this.description = this.getParam("description");
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
		if (StringUtil.isEmpty(description)) this.addError("input.description.empty");
		try {
			Permission.check("usergroup.canAdd");
		} catch (PermissionDeniedException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void save() {
		try {
			Usergroup group = new Usergroup().setName(name).setDescription(description).setUserlistId(0);
			group.create();
			Userlist list = new Userlist().setName("usergroup-"+group.getId()).setObjectId(group.getId()).setObjectType("usergroup").create();
			group.setUserlistId(list.getId()).update();
			
			this.name = "";
			this.description = "";
		} catch (SQLException ex) {
			this.addError(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "usergroupAdd");
	}
	
	@Override
	public void assignVariables() {
		this.set("name", name);
		this.set("description", description);
	}
	
}
