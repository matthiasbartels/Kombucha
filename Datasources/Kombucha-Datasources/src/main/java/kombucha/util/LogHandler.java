/**
 * 
 */
package kombucha.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ronny Walter
 *
 */
public class LogHandler {
	
	private static Log log;
	
	public static void logError(Class source, String message) {
		log = LogFactory.getLog(source);
		log.error(message);
	}
	
	public static void logError(Class source, String message, Throwable ex) {
		log = LogFactory.getLog(source);
		log.error(message, ex);
	}
	
	public static void logSevereError(Class source, String message, Throwable ex) throws RuntimeException {
		log = LogFactory.getLog(source);
		log.error(message);
		throw new RuntimeException(ex);
	}
	
	public static void logSevereError(Class source, String message) throws RuntimeException {
		log = LogFactory.getLog(source);
		log.error(message);
		throw new RuntimeException(message);
	}
	
	public static void logInfo(Class source, String message, Throwable ex) {
		log = LogFactory.getLog(source);
		log.info(message, ex);	
	}
	
	public static void logInfo(Class source, String message) {
		log = LogFactory.getLog(source);
		log.info(message);	
	}
	
	public static void logDebug(Class source, String message) {
		log = LogFactory.getLog(source);
		log.debug(message);	
	}
}
