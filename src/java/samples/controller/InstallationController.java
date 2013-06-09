/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.util.DebugUtil;
import samples.util.InstallationUtil;

/**
 *
 * @author SargTeX
 */
public class InstallationController extends Controller {
	
	private boolean accepted;
	
	@Override
	public void assignSections() {
		this.setTemplate("content", "installation");
	}
	
	@Override
	public void readParameters() {
		if (this.hasParam("accepted")) this.accepted = true;
	}
	
	@Override
	public void save() {
		try {
		//	InstallationUtil.uninstall();
			InstallationUtil.install();
		} catch (Exception ex) {
			DebugUtil.log(ex);
			this.addError(ex);
		}
	}
	
}
