package samples.util;

import java.io.IOException;
import java.io.InputStream;


public class IOUtil {
	
	/**
	 * Returns the content of the input stream as a string.
	 * 
	 * @param inputStream the input stream
	 * @return the content of the input stream
	 * @throws IOException 
	 * @see IOUtils#toString(java.io.InputStream)
	 */
	public static String toString(InputStream inputStream) throws IOException {
		 java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
}
