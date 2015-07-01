package pl.mg.javatst.unba.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.naming.Context;
import javax.naming.NamingException;
import java.sql.SQLException;

//import javax.naming.InitialContext;

public class BasicDataSourceFactory {
	protected static Log log = LogFactory.getLog(BasicDataSourceFactory.class);

	protected BasicDataSource basicDataSource;
	protected Context context;
	protected String jndiURL;
	protected String driverClassName = null;
	protected int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
	protected int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;;
	protected int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;;
	protected int initialSize = 0;
	protected int validationQueryTimeout = 0;
	protected long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;
	protected String password = null;
	protected String url = null;
	protected String username = null;
	protected String initialStatement = null;
	protected String validationQuery = null;
	protected int[] criticalCodes = new int[0];
	protected boolean poolingStatements = false;
	protected boolean testOnBorrow = false;
	protected boolean testOnReturn = false;
	protected boolean testWhileIdle = false;
	protected int timeout = 0;

	public void bind() throws SQLException {
		log.info("datasource bind");
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMinIdle(minIdle);
		ds.setInitialSize(initialSize);
		ds.setMaxWait(maxWait);
		ds.setPassword(password);
		ds.setUsername(username);
		ds.setUrl(url);
		ds.setValidationQuery(validationQuery);
		ds.setTestOnBorrow(testOnBorrow);
		ds.setTestOnReturn(testOnReturn);
		ds.setTestWhileIdle(testWhileIdle);
		ds.setCriticalCodes(criticalCodes);
		ds.setValidationQueryTimeout(validationQueryTimeout);
		ds.setTimeout(this.timeout);

		ds.setPoolPreparedStatements(poolingStatements);

		ds.setInitialStatement(initialStatement);

		/*
		 * try { if (context == null) { System.out.println("context null");
		 * context = new InitialContext(); }else{
		 * System.out.println("context set: "+context.getClass()); }
		 * 
		 * //context = ServletContext.getServletContext().
		 * //org.springframework.jndi.JndiObjectFactoryBean a = new
		 * org.springframework.jndi.JndiObjectFactoryBean();
		 * 
		 * System.out.println("context binding"); context.bind(jndiURL, ds);
		 * System.out.println("context bound"); Object obj =
		 * context.lookup(jndiURL);
		 * System.out.println("looked up "+jndiURL+": result: "+obj); } catch
		 * (NamingException e) { logger.error("dataSource bind failure", e); }
		 * 
		 * finally { ds.close(); }
		 */

		basicDataSource = ds;
		log.info("datasource initialized: " + basicDataSource);
	}

	public void destroy() throws NamingException, SQLException {
		// context.unbind(jndiURL);
		basicDataSource.close();
		log.debug("datasource close");
	}

	public String getJndiURL() {
		return jndiURL;
	}

	public void setJndiURL(String jndiURL) {
		this.jndiURL = jndiURL;
		log.debug("jndiURL: " + jndiURL);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
		log.debug("context: " + context);
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
		log.debug("driverClassName: " + driverClassName);
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
		log.debug("initialSize: " + initialSize);
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
		log.debug("maxActive: " + maxActive);
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
		log.debug("maxIdle: " + maxIdle);
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
		log.debug("maxWait: " + maxWait);
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
		log.debug("minIdle: " + minIdle);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		log.debug("url: " + url);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		log.debug("username: " + username);
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
		log.debug("validationQuery: " + validationQuery);
	}

	public int[] getCriticalCodes() {
		return criticalCodes;
	}

	public void setCriticalCodes(int[] criticalCodes) {
		this.criticalCodes = criticalCodes;
		log.debug("criticalCodes: " + criticalCodes);
	}

	/**
	 * @return the initialStatement
	 */
	public String getInitialStatement() {
		return initialStatement;
	}

	public void setInitialStatement(String initialStatement) {
		this.initialStatement = initialStatement;
		log.debug("initialStatement: " + initialStatement);
	}

	/**
	 * @return the basicDataSource
	 */
	public BasicDataSource getBasicDataSource() {
		return basicDataSource;
	}

	/**
	 * @param basicDataSource
	 *            the basicDataSource to set
	 */
	public void setBasicDataSource(BasicDataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
		log.debug("basicDataSource: " + basicDataSource);
	}

	/**
	 * @param poolingStatements
	 *            the if poolingStatements enabled
	 */
	public void setPoolPreparedStatements(boolean poolingStatements) {
		this.poolingStatements = poolingStatements;
		log.debug("poolingStatements: " + this.poolingStatements);
	}

	/**
	 * Gets the validation query timeout.
	 * 
	 * @return validation query timeout.
	 */
	public int getValidationQueryTimeout() {
		return validationQueryTimeout;
	}

	/**
	 * Sets the validation query timeout, the amount of time, in seconds, that
	 * connection validation will wait for a response from the database when
	 * executing a validation query. Use a value less than or equal to 0 for no
	 * timeout.
	 * 
	 * @param validationQueryTimeout
	 */
	public void setValidationQueryTimeout(int validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	/**
	 * Sets the testOnBorrow property. This property determines whether or not
	 * the pool will validate objects before they are borrowed from the pool.
	 * For a true value to have any effect, the validationQuery property must be
	 * set to a non-null string.
	 * 
	 * @param testOnBorrow
	 *            new value for testOnBorrow property
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * Returns the testOnBorrow property.
	 * 
	 * @return true if objects are validated before being borrowed from the pool
	 */
	public boolean getTestOnBorrow() {
		return this.testOnBorrow;
	}

	/**
	 * Returns the value of the testOnReturn property.
	 * 
	 * @return true if objects are validated before being returned to the pool
	 */
	public boolean getTestOnReturn() {
		return testOnReturn;
	}

	/**
	 * Sets the testOnReturn property. This property determines whether or not
	 * the pool will validate objects before they are returned to the pool. For
	 * a true value to have any effect, the validationQuery property must be set
	 * to a non-null string.
	 * 
	 * @param new value for testOnReturn property
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * Returns the value of the testWhileIdle property.
	 * 
	 * @return true if objects examined by the idle object evictor are validated
	 */
	public boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * Sets the testWhileIdle property. This property determines whether or not
	 * the idle object evictor will validate connections. For a true value to
	 * have any effect, the validationQuery property must be set to a non-null
	 * string.
	 * 
	 * @param testWhileIdle
	 *            new value for testWhileIdle property
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}


}
