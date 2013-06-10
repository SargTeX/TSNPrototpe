/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.exception;

/**
 *
 * @author SargTeX
 */
public class PermissionDeniedException extends Exception {

	public PermissionDeniedException() {
	}

	public PermissionDeniedException(String string) {
		super(string);
	}

	public PermissionDeniedException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public PermissionDeniedException(Throwable thrwbl) {
		super(thrwbl);
	}

	public PermissionDeniedException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
		super(string, thrwbl, bln, bln1);
	}
	
}
