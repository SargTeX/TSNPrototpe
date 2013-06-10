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

/**
 *
 * @author SargTeX
 */
public class LanguageDeleteController extends Controller {
	
	private String code;
	private Language language;
	
	@Override
	public void readParameters() {
		code = getParam("code");
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
	}
	
	@Override
	public void save() {
		try {
			language.remove();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
}
