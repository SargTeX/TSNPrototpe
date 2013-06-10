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
import samples.data.model.User;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class UserAddController extends Controller {
	
	private String username;
	private String password;
	private String email;
	private String prename;
	private String surname;
	
	@Override
	public void readParameters() {
		this.username = this.getParam("username");
		this.password = this.getParam("password");
		this.email = this.getParam("email");
		this.prename = this.getParam("prename");
		this.surname = this.getParam("surname");
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(this.username)) this.addError("input.username.empty");
		if (StringUtil.isEmpty(this.password)) this.addError("input.password.empty");
	}
	
	@Override
	public void save() {
		try {
			new User().setUsername(username).setPassword(password).setEmail(email).setPrename(prename).setSurname(surname).create();
		} catch (SQLException ex) {
			this.addError("technical.save");
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "userAdd");
	}
	
	
	
}
