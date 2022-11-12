package Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoLog {
	static final Logger logger = LogManager.getLogger(DemoLog.class.getName());
//	public static void main(String[] args) {
		public static void sample(int i)
		{
		if (i == 1)
		logger.trace("Tracking");
		else if (i == 2) 
		logger.info("Started Test");
		else if(i == 3) 
		logger.error("Error message");
		else if(i == 4)
		logger.warn("This is an warning message");
		else if(i == 5)
		logger.fatal("This is an fatal message");
	}
}
