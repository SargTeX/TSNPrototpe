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
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class UsergroupEditController extends Controller {
	
	private Usergroup group;
	private String name;
	private String description;
	private Permission[] permissions;
	
	@Override
	public void readParameters() {
		if (this.hasParam("id")) this.group = new Usergroup().setId(Integer.parseInt(this.getParam("id")));
		this.name = this.getParam("name");
		this.description = this.getParam("description");
	}
	
	@Override
	public void readData() {
		try {
			if (this.group != null) {
				group.read();
				permissions = Permission.getPermissions(group.getId(), "usergroup");
			}
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void validate() {
		if (!this.group.exists()) this.addError("input.id.wrong");
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
	}
	
	@Override
	public void save() {
		try {
			group.setName(name).setDescription(description).update();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "usergroupAdd");
	}
	
	@Override
	public void assignVariables() {
		this.set("id", group.getId());
		this.set("name", group.getName());
		this.set("description", group.getDescription());
		this.set("permissions", permissions);
	}
	
	
}
