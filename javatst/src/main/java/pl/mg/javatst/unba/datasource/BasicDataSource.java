package pl.mg.javatst.unba.datasource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * The Class BasicDataSource.
 */
public class BasicDataSource extends org.apache.commons.dbcp.BasicDataSource {

	/** The log. */
	protected static Log log = LogFactory.getLog(BasicDataSource.class);

	/** The critical codes. */
	protected int[] criticalCodes = null;

	/** The initial statement. */
	protected String initialStatement = null;

	/** The stage. */
	protected String stage = "";

	/** The timeout **/
	protected int timeout = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.dbcp.BasicDataSource#createDataSource()
	 */
	protected synchronized DataSource createDataSource() throws SQLException {

		log.debug("createDataSource init");
		try {
			stage = "initial";
			if (closed) {
				throw new SQLException("Data source is closed");
			}
			// Return the pool if we have already created it
			if (dataSource != null) {
				log.debug("createDataSource alreadyCreated dataSource: " + dataSource);
				return (dataSource);
			}

			stage = "createConnectionFactory";
			// Create factory which returns raw physical connections
			ConnectionFactory driverConnectionFactory = createConnectionFactory();
			log.debug("createDataSource driverConnectionFactory: " + driverConnectionFactory);

			stage = "createGenericObjectPool";
			// Create an object pool to contain our active connections
			connectionPool = createConnectionPoolObject();
			log.debug("createDataSource connectionPool: " + connectionPool);

			stage = "createStatementPoolFactory";
			// Set up statement pool, if desired
			GenericKeyedObjectPoolFactory statementPoolFactory = null;
			log.debug("createDataSource poolPreparedStatements: " + poolPreparedStatements);
			if (isPoolPreparedStatements()) {
				statementPoolFactory = new GenericKeyedObjectPoolFactory(null, -1, // unlimited
																					// maxActive
																					// (per
																					// key)
						GenericKeyedObjectPool.WHEN_EXHAUSTED_FAIL, 0, // maxWait
						1, // maxIdle (per key)
						maxOpenPreparedStatements);
			}
			log.debug("createDataSource statementPoolFactory: " + statementPoolFactory);

			stage = "createInitialStatement";
			this.initialStatement = connectionProperties.getProperty("initialStatement");
			log.debug("createDataSource initialStatement: " + initialStatement);

			stage = "createConnectionFactory";
			// Set up the poolable connection factory
			PoolableConnectionFactory connectionFactory = createPoolableConnectionFactory(driverConnectionFactory,
					statementPoolFactory, initialStatement);
			log.debug("createDataSource connectionFactory: " + connectionFactory);

			stage = "createPoolableDataSource";
			// Create and return the pooling data source to manage the
			// connections
			dataSource = createPoolableDataSource();
			log.debug("createDataSource dataSource: " + dataSource);

			stage = "connectionPoolAddObject";
			try {
				for (int i = 0; i < initialSize; i++) {
					connectionPool.addObject();
				}
			} catch (Exception e) {
				throw new SQLException("Error preloading the connection pool", e);
			}
			log.debug("createDataSource success");
		} catch (NullPointerException e) {
			String msg = "NullPointerError: " + e.getMessage() + ";stage: " + stage;
			log.error(msg, e);
			throw new SQLException(msg, e);
		} catch (RuntimeException e) {
			String msg = "RuntimeError: " + e.getMessage() + ";stage: " + stage;
			log.error(msg, e);
			throw new SQLException(msg, e);
		} catch (SQLException e) {
			String msg = "SQLError: " + e.getMessage() + ";stage: " + stage;
			log.error(msg, e);
			throw new SQLException(msg, e);
		} catch (Exception e) {
			String msg = "Error: " + e.getMessage() + ";stage: " + stage;
			log.fatal(msg, e);
			throw new SQLException(msg, e);
		}
		return dataSource;
	}

	/**
	 * Sets the critical codes.
	 * 
	 * @param criticalCodes
	 *            the new critical codes
	 */
	public synchronized void setCriticalCodes(int[] criticalCodes) {
		this.criticalCodes = criticalCodes;
	}

	/**
	 * Gets the critical codes.
	 * 
	 * @return the critical codes
	 */
	public int[] getCriticalCodes() {
		return criticalCodes;
	}

	/**
	 * Sets the initial statement.
	 * 
	 * @param initialStatement
	 *            the new initial statement
	 */
	public synchronized void setInitialStatement(String initialStatement) {
		this.initialStatement = initialStatement;
		log.debug("initialStatement: " + initialStatement);
	}

	/**
	 * Gets the initial statement.
	 * 
	 * @return the initial statement
	 */
	public String getInitialStatement() {
		return initialStatement;
	}

	/**
	 * Creates the connection pool object.
	 * 
	 * @return the generic object pool
	 */
	protected GenericObjectPool createConnectionPoolObject() {
		GenericObjectPool gop = new GenericObjectPool();

		log.debug("createDataSource maxActive: " + maxActive);
		gop.setMaxActive(maxActive);
		log.debug("createDataSource maxIdle: " + maxIdle);
		gop.setMaxIdle(maxIdle);
		log.debug("createDataSource minIdle: " + minIdle);
		gop.setMinIdle(minIdle);
		log.debug("createDataSource maxWait: " + maxWait);
		gop.setMaxWait(maxWait);
		log.debug("createDataSource testOnBorrow: " + testOnBorrow);
		gop.setTestOnBorrow(testOnBorrow);
		log.debug("createDataSource testOnReturn: " + testOnReturn);
		gop.setTestOnReturn(testOnReturn);
		log.debug("createDataSource timeBetweenEvictionRunsMillis: " + timeBetweenEvictionRunsMillis);
		gop.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		log.debug("createDataSource numTestsPerEvictionRun: " + numTestsPerEvictionRun);
		gop.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		log.debug("createDataSource minEvictableIdleTimeMillis: " + minEvictableIdleTimeMillis);
		gop.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		log.debug("createDataSource testWhileIdle: " + testWhileIdle);
		gop.setTestWhileIdle(testWhileIdle);

		return gop;
	}

	/**
	 * Creates the poolable connection factory.
	 * 
	 * @param driverConnectionFactory
	 *            the driver connection factory
	 * @param statementPoolFactory
	 *            the statement pool factory
	 * @param initialStatement
	 *            the initial statement
	 * 
	 * @return the poolable connection factory
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	protected PoolableConnectionFactory createPoolableConnectionFactory(ConnectionFactory driverConnectionFactory,
			KeyedObjectPoolFactory statementPoolFactory, String initialStatement) throws SQLException {
		PoolableConnectionFactory pcf = null;
		try {
			if (this.defaultReadOnly == null) {
				this.defaultReadOnly = false;
			}
			pcf = new PoolableConnectionFactory(driverConnectionFactory, connectionPool, statementPoolFactory,
					validationQuery, validationQueryTimeout, defaultReadOnly, defaultAutoCommit,
					defaultTransactionIsolation, defaultCatalog, null, initialStatement);

			if (pcf == null) {
				throw new SQLException("Cannot create PoolableConnectionFactory (null)");
			}
			validateConnectionFactory(pcf);
		} catch (RuntimeException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException("Cannot create PoolableConnectionFactory (" + e.getMessage() + ")", e);
		}
		return pcf;
	}


	/**
	 * Creates the poolable data source.
	 * 
	 * @return the poolable data source
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	protected PoolableDataSource createPoolableDataSource() throws SQLException {

		PoolableDataSource pds = new PoolableDataSource(connectionPool);
		((PoolableDataSource) pds).setCriticalCodes(criticalCodes);
		pds.setLogWriter(logWriter);

		return pds;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		if (dataSource != null) {
			log.debug("Set dataSource=" + dataSource);
		} else {
			log.debug("Set dataSource null");
		}
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		pl.mg.javatst.unba.datasource.Connection conn = new pl.mg.javatst.unba.datasource.Connection(super.getConnection());
		conn.setTimeout(this.timeout);
		return conn;
	}


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
