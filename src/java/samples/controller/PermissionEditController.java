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
public class PermissionEditController extends Controller {
	
	private String name;
	private String description;
	private String defaultValue;
	private String type;
	private Permission permission;
	
	@Override
	public void readParameters() {
		this.name = this.getParam("name");
		this.description = this.getParam("description");
		this.defaultValue = this.getParam("defaultValue");
		this.type = this.getParam("type");
	}
	
	@Override
	public void readData() {
		try {
			this.permission = new Permission().setName(name).read();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
		else if (!this.permission.exists()) this.addError("input.name.wrong");
		if (StringUtil.isEmpty(type)) this.addError("input.type.empty");
	}
	
	@Override
	public void save() {
		this.permission.setDescription(description).setDefaultValue(defaultValue).setType(type);
		try {
			this.permission.update();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "permissionAdd");
	}
	
	@Override
	public void assignVariables() {
		set("action", "edit");
		set("name", name);
		set("description", description);
		set("defaultValue", defaultValue);
		set("type", type);
	}
	
}
