package pl.mg.javatst.unba;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
/**
 * Configuration Bean for UNBA Connector (GetUnbillDataBean)
 * @author Geomant Inc. Pawel Biedronski
 */
public class UnbaConfiguration{
	// nazwa pakietu w Oracle
    public static String packageName;
    // nazwa puli polaczen
    public static String connectionPool;
    // flaga konwersji polskich znakow
    public static boolean transcode2En;
    // sciezka do pliku konfiguracyjnego log4J
    public static String logConfFile;
    // time out wywolania procedury UNBA w Oracle
    public static int timeOut = 0;
    
    private Logger logger = null;
    
    public UnbaConfiguration(){
        this.logger = Logger.getLogger("UNBA2Logger");
    }
    
    /**
     * @param logConfFile the logConfFile to set
     */
    public void setLogConfFile(String logConfFile) {
    	System.out.println("UNBA Connector Info: UNBA2Logger start initializing: "+logConfFile);
    	UnbaConfiguration.logConfFile = logConfFile;
        if(logConfFile != null && !logConfFile.equals("")) {
        	DOMConfigurator.configure(logConfFile);
        	this.logger = Logger.getLogger("UNBA2Logger");
        	System.out.println("UNBA Connector Info: UNBA2Logger initialized");
        }
        else {
        	System.out.println("UNBA Connector Warn: logConfFile is null");
        }
        this.logger.debug("logConfFile: "+logConfFile);
    }
   

    /**
     * @param connectionPool the connectionPool to set
     */
    public void setConnectionPool(String connectionPool) {
    	UnbaConfiguration.connectionPool = connectionPool;
        this.logger.debug("connectionPool: "+connectionPool);
    }

    /**
     * @param transcode2En the transcode2En to set
     */
    public void setTranscode2En(String transcode2En) {
    	UnbaConfiguration.transcode2En = Boolean.getBoolean(transcode2En);
        this.logger.debug("transcode2En: "+transcode2En);
    }    
    
    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
    	UnbaConfiguration.packageName = packageName;
        this.logger.debug("packageName: "+packageName);
    }
    
    /**
     * @param timeOut the timeOut to set
     */
	public void setTimeOut(String timeOut) {
		UnbaConfiguration.timeOut = Integer.parseInt(timeOut);
		this.logger.debug("timeOut: "+UnbaConfiguration.timeOut);
	}
   
}
