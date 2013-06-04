/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

/**
 *
 * @author SargTeX
 */
public class UserLoginController extends Controller {
	
	private String username;
	private String password;
	
	@Override
	public void readParameters() {
		this.username = this.getParam("username", String.class);
		this.password = this.getParam("password", String.class);
	}
	
	@Override
	public void validate() {
		if (this.username == null) this.addError("input.username.empty");
		if (this.password == null) this.addError("input.password.empty");
		
		// check credentials and login
		try {
			User.login(this.username, this.password);
		} catch (ValidationException e) { // wird geworfen, wenn die Authentifizierungsdaten nicht stimmen
			this.addError(e);
		}
	}
	
}
