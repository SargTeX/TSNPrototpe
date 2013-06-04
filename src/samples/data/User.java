/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data;

/**
 *
 * @author SargTeX
 */
public class User extends DataModel {
	
	private String username;
	private String password;
	
	public static void login(String username, String password) throws ValidationException {
		// sinnlose Überprüfung; muss bei createSession ohnehin nochmals durchgeführt werden;
		if (!Core.getDB().exists("FROM user WHERE username = ? AND password = ?", username, password)) {
			throw new ValidationException("input.credentials.wrong");
		}
		
		// create session
		Core.createSession(username, password); // Nutzerdaten müssen überprüft werden, da Plugins sonst sessions erschummeln könnten
	}
	
}
