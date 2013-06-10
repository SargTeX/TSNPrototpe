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
public class LanguageEditController extends Controller {
	
	private String code;
	private String name;
	private String description;
	private Language language;
	
	@Override
	public void readParameters() {
		code = getParam("code");
		name = getParam("name");
		description = getParam("description");
	}
	
	@Override
	public void readData() {
		try {
			language = new Language().setCode(code).read();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void validate() {
		if (!language.exists()) this.addError("input.code.wrong");
		if (StringUtil.isEmpty(name)) this.addError("input.name.wrong");
	}
	
	@Override
	public void save() {
		try {
			language.setName(name).setDescription(description).update();
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
		set("action", "edit");
		set("code", language.getCode());
		set("name", language.getName());
		set("description", language.getDescription());
	}
	
}
