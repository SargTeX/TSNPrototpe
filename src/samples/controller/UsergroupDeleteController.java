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
import samples.data.model.Usergroup;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class UsergroupDeleteController extends Controller {
	
	private int usergroupId;
	private Usergroup group;
	
	@Override
	public void readParameters() {
		if (this.hasParam("id")) this.usergroupId = Integer.parseInt(this.getParam("id"));
	}
	
	@Override
	public void validate() {
		if (usergroupId == 0) this.addError("input.usergroupId.empty");
		try {
			group = new Usergroup().setId(usergroupId).read();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
		if (!group.exists()) this.addError("input.usergroupId.wrong");
	}
	
	@Override
	public void save() {
		try {
			group.remove();
			this.addError("removed");
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
}
