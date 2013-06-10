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
import samples.data.model.Language;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class LanguageAddController extends Controller {
	
	private String code;
	private String name;
	private String description;
	
	@Override
	public void readParameters() {
		code = this.getParam("code");
		name = getParam("name");
		description = getParam("description");
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(code)) this.addError("input.code.empty");
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
		try {
			if (new Language().setCode(code).read().exists()) this.addError("input.code.duplicate");
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void save() {
		try {
			new Language().setCode(code).setName(name).setDescription(description).create();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "languageAdd");
	}
	
	@Override
	public void assignVariables() {
		set("action", "add");
		set("code", code);
		set("name", name);
		set("description", description);
	}
	
}
