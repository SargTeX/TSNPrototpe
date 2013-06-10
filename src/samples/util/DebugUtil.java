/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SargTeX
 */
public class DebugUtil {
	
	private static List<Exception> exceptionList = new ArrayList<Exception>();
	
	/**
	 * Adds another exception for logging purposes.
	 * 
	 * @param exception the exception
	 */
	public static void log(Exception ex) {
		exceptionList.add(ex);
	}
	
}
