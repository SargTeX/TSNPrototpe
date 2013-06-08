/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.util;

import samples.util.JsonUtil;

/**
 *
 * @author marti_000
 */
public class StringUtil {
	
	/**
	 * Upper-cases the first letter of the string.
	 * 
	 * @param text the string that will be uppercased at the first letter
	 * @return the uppercased text
	 */
	public static String ucfirst(String text) {
		return Character.toUpperCase(text.charAt(0))+text.substring(1);
	}
	
	/**
	 * Lower-cases the first letter of the string.
	 * 
	 * @param text the string that will be lowercased at the first letter
	 * @return the lowercased text
	 */
	public static String lcfirst(String text) {
		return Character.toLowerCase(text.charAt(0))+text.substring(1);
	}
	
	/**
	 * Tries to form a string-representative of the object.
	 * This works for:
	 *  - Strings
	 *  - int, double, float
	 *  - boolean (returns "1" or "0")
	 *  - objects using toString() method
	 * 
	 * @param object the object that will be formed to a string
	 * @return the string
	 */
	public static String toString(Object object) {
		if (object == null) return null;
		if (object instanceof String) return (String) object;
		if (object instanceof Integer) return ""+object;
		if (object instanceof Double) return ""+object;
		if (object instanceof Float)  return ""+object;
		return JsonUtil.toJson(object);
	}
	
	/**
	 * Checks whether or not the given string is null or empty.
	 * 
	 * @param text the string that will be checked
	 * @return true if the string was null or empty, otherwise false
	 */
	public static boolean isEmpty(String text) {
		return (text == null || text.isEmpty());
	}
	
	/**
	 * Add an / at the end, if it isn't already
	 * @param text
	 * @return text with /
	 */
	public static String addTrailingSlash(String text){
		if(!text.endsWith("/"))
			text += "/";
		return text;
	}
	
	/**
	 * Returns the hex value from the text.
	 * 
	 * @param text the text
	 * @return the hex value as a string
	 */
	public static String getHexValue(String text) {
		byte[] bytes = text.getBytes();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
}
