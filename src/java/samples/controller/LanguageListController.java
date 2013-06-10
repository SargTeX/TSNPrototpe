/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.data.model.Language;
import samples.util.DebugUtil;

/**
 *
 * @author SargTeX
 */
public class LanguageListController extends Controller {
	
	private Language[] languages;
	
	@Override
	public void readData() {
		try {
			languages = Language.getAll();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		setTemplate("content", "languageList");
	}
	
	@Override
	public void assignVariables() {
		set("languages", languages);
	}
	
}
