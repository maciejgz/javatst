package pl.mg.javatst.unba.datasource;

import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * DataSouce factory for use with Tomcat Context Resource Definition.
 * 
 * @version $Revision$
 *
 */
public class JndiDataSourceFactory implements ObjectFactory {
	
	/** Component logger */
	protected static Log log = LogFactory.getLog(JndiDataSourceFactory.class);
	
	private final static String PROP_DEFAULTAUTOCOMMIT = "defaultAutoCommit";
	private final static String PROP_DEFAULTREADONLY = "defaultReadOnly";
	private final static String PROP_DEFAULTCATALOG = "defaultCatalog";
	private final static String PROP_DRIVERCLASSNAME = "driverClassName";
	private final static String PROP_MAXACTIVE = "maxActive";
	private final static String PROP_MAXIDLE = "maxIdle";
	private final static String PROP_MINIDLE = "minIdle";
	private final static String PROP_INITIALSIZE = "initialSize";
	private final static String PROP_MAXWAIT = "maxWait";
	private final static String PROP_TESTONBORROW = "testOnBorrow";
	private final static String PROP_TESTONRETURN = "testOnReturn";
	private final static String PROP_TIMEBETWEENEVICTIONRUNSMILLIS = "timeBetweenEvictionRunsMillis";
	private final static String PROP_NUMTESTSPEREVICTIONRUN = "numTestsPerEvictionRun";
	private final static String PROP_MINEVICTABLEIDLETIMEMILLIS = "minEvictableIdleTimeMillis";
	private final static String PROP_TESTWHILEIDLE = "testWhileIdle";
	private final static String PROP_PASSWORD = "password";
	private final static String PROP_URL = "url";
	private final static String PROP_USERNAME = "username";
	private final static String PROP_VALIDATIONQUERY = "validationQuery";
	private final static String PROP_ACCESSTOUNDERLYINGCONNECTIONALLOWED = "accessToUnderlyingConnectionAllowed";
	private final static String PROP_POOLPREPAREDSTATEMENTS = "poolPreparedStatements";
	private final static String PROP_MAXOPENPREPAREDSTATEMENTS = "maxOpenPreparedStatements";
	private final static String PROP_CONNECTIONPROPERTIES = "connectionProperties";
	private final static String PROP_FATALCODES = "fatalCodes";
	private final static String PROP_INITIALSTATEMENT = "initialStatement";
	
	private final static String[] ALL_PROPERTIES = {
		PROP_DEFAULTAUTOCOMMIT,
		PROP_DEFAULTREADONLY,
		PROP_DEFAULTCATALOG,
		PROP_DRIVERCLASSNAME,
		PROP_MAXACTIVE,
		PROP_MAXIDLE,
		PROP_MINIDLE,
		PROP_INITIALSIZE,
		PROP_MAXWAIT,
		PROP_TESTONBORROW,
		PROP_TESTONRETURN,
		PROP_TIMEBETWEENEVICTIONRUNSMILLIS,
		PROP_NUMTESTSPEREVICTIONRUN,
		PROP_MINEVICTABLEIDLETIMEMILLIS,
		PROP_TESTWHILEIDLE,
		PROP_PASSWORD,
		PROP_URL,
		PROP_USERNAME,
		PROP_VALIDATIONQUERY,
		PROP_ACCESSTOUNDERLYINGCONNECTIONALLOWED,
		PROP_POOLPREPAREDSTATEMENTS,
		PROP_MAXOPENPREPAREDSTATEMENTS,
		PROP_CONNECTIONPROPERTIES,
		PROP_FATALCODES,
		PROP_INITIALSTATEMENT
	};
	
	
	@SuppressWarnings("unchecked")
    public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable environment)
	throws Exception {
	    log.debug("getObjectInstance");
		try {
//			We only know how to deal with <code>javax.naming.Reference</code>s
//			that specify a class name of "javax.sql.DataSource"
			
			if ((obj == null) || !(obj instanceof Reference)) {
				return null;
			}
			Reference ref = (Reference) obj;
			
			if (!"javax.sql.DataSource".equals(ref.getClassName())) {
				return null;
			}
			
			Properties properties = new Properties();
			for (int i = 0 ; i < ALL_PROPERTIES.length ; i++) {
				String propertyName = ALL_PROPERTIES[i];
				RefAddr ra = ref.get(propertyName);
				if (ra != null) {
					String propertyValue = ra.getContent().toString();
					log.debug("setting prop: " + propertyName + "=\"" + propertyValue + "\"");
					properties.setProperty(propertyName, propertyValue);
				}
			}
			
			log.info("JndiDataSourceFactory creating DataSource");
			return createDataSource(log, properties);
		}
		catch(Throwable e) {
			log.error("getObjectInstance error", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public static DataSource createDataSource(Log logger, Properties properties) throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		String value = null;
		
		value = properties.getProperty(PROP_DEFAULTAUTOCOMMIT);
		if (value != null) {
			dataSource.setDefaultAutoCommit(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_DEFAULTREADONLY);
		if (value != null) {
			dataSource.setDefaultReadOnly(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_DEFAULTCATALOG);
		if (value != null) {
			dataSource.setDefaultCatalog(value);
		}
		
		value = properties.getProperty(PROP_DRIVERCLASSNAME);
		if (value != null) {
			dataSource.setDriverClassName(value);
		}
		
		value = properties.getProperty(PROP_MAXACTIVE);
		if (value != null) {
			dataSource.setMaxActive(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_MAXIDLE);
		if (value != null) {
			dataSource.setMaxIdle(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_MINIDLE);
		if (value != null) {
			dataSource.setMinIdle(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_INITIALSIZE);
		if (value != null) {
			dataSource.setInitialSize(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_MAXWAIT);
		if (value != null) {
			dataSource.setMaxWait(Long.parseLong(value));
		}
		
		value = properties.getProperty(PROP_TESTONBORROW);
		if (value != null) {
			dataSource.setTestOnBorrow(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_TESTONRETURN);
		if (value != null) {
			dataSource.setTestOnReturn(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_TIMEBETWEENEVICTIONRUNSMILLIS);
		if (value != null) {
			dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(value));
		}
		
		value = properties.getProperty(PROP_NUMTESTSPEREVICTIONRUN);
		if (value != null) {
			dataSource.setNumTestsPerEvictionRun(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_MINEVICTABLEIDLETIMEMILLIS);
		if (value != null) {
			dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(value));
		}
		
		value = properties.getProperty(PROP_TESTWHILEIDLE);
		if (value != null) {
			dataSource.setTestWhileIdle(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_PASSWORD);
		if (value != null) {
			dataSource.setPassword(value);
		}
		
		value = properties.getProperty(PROP_URL);
		if (value != null) {
			dataSource.setUrl(value);
		}
		
		value = properties.getProperty(PROP_USERNAME);
		if (value != null) {
			dataSource.setUsername(value);
		}
		
		value = properties.getProperty(PROP_VALIDATIONQUERY);
		if (value != null) {
			dataSource.setValidationQuery(value);
		}
		
		value = properties.getProperty(PROP_ACCESSTOUNDERLYINGCONNECTIONALLOWED);
		if (value != null) {
			dataSource.setAccessToUnderlyingConnectionAllowed(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_POOLPREPAREDSTATEMENTS);
		if (value != null) {
			dataSource.setPoolPreparedStatements(Boolean.valueOf(value).booleanValue());
		}
		
		value = properties.getProperty(PROP_MAXOPENPREPAREDSTATEMENTS);
		if (value != null) {
			dataSource.setMaxOpenPreparedStatements(Integer.parseInt(value));
		}
		
		value = properties.getProperty(PROP_CONNECTIONPROPERTIES);
		if (value != null) {
			Properties p = getProperties(value);
			Enumeration e = p.propertyNames();
			while (e.hasMoreElements()) {
				String propertyName = (String) e.nextElement();
				dataSource.addConnectionProperty(propertyName, p.getProperty(propertyName));
			}
		}
		value = properties.getProperty(PROP_FATALCODES);
		if (value != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("spliting codes: [" + value + "]");
			}
			String[] codesTxt = value.split(",");
			int[] codes = new int[codesTxt.length];
			try {
				for (int i=0; i<codesTxt.length;i++) {
					codes[i] = Integer.parseInt(codesTxt[i].trim());
				}
				dataSource.setCriticalCodes(codes);
			}
			catch(NumberFormatException ex) {
				logger.error("failed to set fatalCodes: " + value, ex);
				throw ex;
			}
		}
		value = properties.getProperty(PROP_INITIALSTATEMENT);
	      if (value != null) {
	                dataSource.addConnectionProperty(PROP_INITIALSTATEMENT, value);
	        }
		// Return the configured DataSource instance
		return dataSource;
	}
	
	/**
	 * <p>Parse properties from the string. Format of the string must be [propertyName=property;]*<p>
	 * @param propText
	 * @return Properties
	 * @throws Exception
	 */
	static private Properties getProperties(String propText) throws Exception {
		Properties p = new Properties();
		if (propText != null) {
			p.load(new ByteArrayInputStream(propText.replace(';', '\n').getBytes()));
		}
		return p;
	}
	
}
