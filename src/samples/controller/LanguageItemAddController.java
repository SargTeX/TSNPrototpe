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
import samples.data.model.LanguageItem;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class LanguageItemAddController extends Controller {
	
	private String name;
	
	@Override
	public void readParameters() {
		name = getParam(name);
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(name)) this.addError("input.name.empty");
		try {
			if (new LanguageItem().setName(name).read().exists()) this.addError("input.name.duplicate");
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void save() {
		try {
			new LanguageItem().setName(name).create();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		setTemplate("content", "languageItemAdd");
	}
	
	@Override
	public void assignVariables() {
		set("name", name);
	}
	
}
