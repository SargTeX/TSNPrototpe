/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.sql.SQLException;
import samples.data.model.Permission;
import samples.util.StringUtil;
import samples.util.DebugUtil;

/**
 *
 * @author SargTeX
 */
public class PermissionAddController extends Controller {
	
	private String name;
	private String description;
	private String defaultValue;
	private String type;
	
	@Override
	public void readParameters() {
		this.name = this.getParam("name");
		this.description = this.getParam("description");
		this.defaultValue = this.getParam("defaultValue");
		this.type = this.getParam("type");
	}
	
	@Override
	public void validate() {
		try {
			if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
			else if (new Permission().setName(name).read().exists()) this.addError("input.name.duplicate");
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
		
		if (StringUtil.isEmpty(type)) this.addError("input.type.empty");
	}
	
	@Override
	public void save() {
		Permission permission = new Permission();
		try {
			permission.setName(name).setDescription(description).setDefaultValue(defaultValue).setType(type).create();
			this.name = "";
			this.description = "";
			this.defaultValue = "";
			this.type = "";
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
		this.set("action", "add");
		this.set("name", this.name);
		this.set("description", this.description);
		this.set("defaultValue", this.defaultValue);
		this.set("type", this.type);
	}
	
}
