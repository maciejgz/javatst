/*
 * Created on 2007-01-22
 *
 */
package pl.mg.javatst.unba.osp.vo.connectors;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import pl.mg.javatst.unba.datasource.*;

/**
 * Db connection factory obtaining connection from context: "java:/comp/env".
 * 
 * @author Piotr Zaborowski
 * 
 */
public class DbConnectionFactory {
	// private static Logger logger =
	// Logger.getLogger(DbConnectionFactory.class);
	private static Logger logger = Logger.getLogger("pl.era.datasource.DbConnectionFactory");

	// public static Map<String, BasicDataSourceFactory> dataSouce;
	public static Map<String, List<BasicDataSourceFactory>> basicDataSources;

	private static Map<String, Integer> indexes = new HashMap<String, Integer>();

	private static List<String> restartDSErrorMessages = new LinkedList<String>();

	private static int[] errorCodes = new int[0];

	private String stage = "";
	private int id;
	private String jndiName;

	public DbConnectionFactory() {
		id = 0;
	}
	
	public void restartDataSource(String connectionPool) throws ConnectionException {
		logger.debug("Restart data source factory for connection pool: " + connectionPool);
		List<BasicDataSourceFactory> bdsfl = basicDataSources.get(connectionPool);
		if (bdsfl == null) {
			return;
		}
		Integer index = indexes.get(connectionPool);
		if (index == null) {
			index = 0;
		} else if (index.intValue() < 0 || index.intValue() >= bdsfl.size()) {
			index = 0;
		}
		logger.debug("Get basic data sources factory: " + index.intValue());
		BasicDataSourceFactory bdsf = bdsfl.get(index.intValue());
		if (bdsf != null) {
			logger.info("Restart basic data source factory: " + bdsf + " for connection pool: " + connectionPool);
			try {
				bdsf.destroy();
				logger.info("Destroy success");
			} catch (NamingException e) {
				logger.error("Restart error: " + e.getMessage(),e);
			} catch (SQLException e) {
				logger.error("Restart error: " + e.getMessage(),e);
			}
			try {
				bdsf.bind();
				logger.info("Init success");
			} catch (SQLException e) {
				logger.error("Restart error: " + e.getMessage(),e);
			}
		}
	}

	public void nextConnection(String connectionPool, String errorMessage) throws ConnectionException {
		id++;
		logger.debug("Next connection " + id + " for this connection pool: " + connectionPool);

		int size = 0;

		Integer index = indexes.get(connectionPool);
		if (index == null) {
			logger.warn("No index defined for this connection pool: " + connectionPool);
			index = indexes.get("");
			if (index == null) {
				logger.warn("No default index defined.");
				index = -1;
			}
			List<BasicDataSourceFactory> bdsfl = basicDataSources.get("");
			size = bdsfl.size();
			if (id >= size) {
				logger.warn("No more available default data sources. " + errorMessage);
				throw new ConnectionException(3, "No more available data soruces for " + connectionPool + ". " + errorMessage);
			}
			int val = index.intValue() + 1;
			if (val >= size) {
				val = 0;
			}
			indexes.put("", val);
			logger.debug("Next connection for this connection pool: " + connectionPool + " is " + val);
		} else {
			List<BasicDataSourceFactory> bdsfl = basicDataSources.get(connectionPool);
			size = bdsfl.size();
			if (id >= size) {
				logger.warn("No more available data sources. " + errorMessage);
				throw new ConnectionException(3, "No more available data soruces for " + connectionPool + ". " + errorMessage);
			}

			int val = index.intValue() + 1;
			if (val >= size) {
				val = 0;
			}
			indexes.put(connectionPool, val);
			logger.debug("Next connection for this connection pool: " + connectionPool + " is " + val);
		}

	}

	public BasicDataSource getBasicDataSource(String connectionPool) throws ConnectionException {
		logger.debug("Get connection for this connection pool: " + connectionPool);
		BasicDataSource bds = null;
		BasicDataSourceFactory bdsf = null;
		try {
			stage = "get basic data source factory list";
			List<BasicDataSourceFactory> bdsfl = basicDataSources.get(connectionPool);
			if (bdsfl == null) {
				logger.warn("No data source factory list defined for this connection pool: " + connectionPool);
				stage = "get default basic data source factory list";
				bdsfl = basicDataSources.get("");
				if (bdsfl == null) {
					String msg = "Default data source factory list not definied for " + connectionPool;
					throw new ConnectionException(4, msg);
				} else {
					stage = "get default index";
					Integer index = indexes.get("");
					if (index == null) {
						logger.warn("No index defined for this connection pool: " + connectionPool);
						index = 0;
						indexes.put(connectionPool, 0);
					} else if (index.intValue() < 0 || index.intValue() >= bdsfl.size()) {
						logger.warn("Index " + index + " out of bounds for this connection pool: " + connectionPool);
						index = 0;
						indexes.put(connectionPool, 0);
					}
					logger.debug("Get default basic data sources factory: " + index.intValue());
					stage = "get default basic data source factory";
					bdsf = bdsfl.get(index.intValue());
					if (bdsf == null) {
						String msg = "Not found default data source factory for " + connectionPool;
						throw new ConnectionException(4, msg);
					}
					logger.debug("Get default basic data sources factory: " + bdsf.getJndiURL()
							+ " for this connection pool: " + connectionPool);
				}
			} else {
				stage = "get index for connection pool";
				Integer index = indexes.get(connectionPool);
				if (index == null) {
					logger.warn("No index defined for this connection pool: " + connectionPool);
					index = 0;
					indexes.put(connectionPool, 0);
				} else if (index.intValue() < 0 || index.intValue() >= bdsfl.size()) {
					logger.warn("Index " + index + " out of bounds for this connection pool: " + connectionPool);
					index = 0;
					indexes.put(connectionPool, 0);
				}
				logger.debug("Get basic data sources factory: " + index.intValue());
				stage = "get basic data source factory";
				bdsf = bdsfl.get(index.intValue());
				if (bdsf == null) {
					String msg = "Not found data source factory for " + connectionPool;
					throw new ConnectionException(4, msg);
				}
				logger.debug("Get basic data sources factory: " + bdsf.getJndiURL() + " for this connection pool: "
						+ connectionPool);
			}
			this.jndiName = bdsf.getJndiURL();

			stage = "get basic data source";
			bds = bdsf.getBasicDataSource();
			if (bds == null) {
				String msg = "Data source not definied for " + connectionPool + " data sources factory: " + bdsf.getJndiURL();
				throw new ConnectionException(4, msg);
			}

		} catch (ConnectionException e) {
			logger.warn("ConnectionError: " + stage, e);
			throw e;

		} catch (Exception e) {
			logger.warn("Error: " + stage, e);
			throw new ConnectionException(4, "General exception getting data source factory for " + connectionPool + ". " + e.getMessage(), e);
		}
		logger.debug("Get BasicDataSource from connection pool success.");
		return bds;
	}

	public Connection getConnection(String connectionPool) throws ConnectionException {
		logger.debug("Get connection for this connection pool: " + connectionPool);
		Connection conn = null;
		BasicDataSourceFactory bdsf = null;
		try {
			stage = "get basic data source factory list";
			List<BasicDataSourceFactory> bdsfl = basicDataSources.get(connectionPool);
			if (bdsfl == null) {
				logger.warn("No data source factory list defined for this connection pool: " + connectionPool);
				stage = "get default basic data source factory list";
				bdsfl = basicDataSources.get("");
				if (bdsfl == null) {
					String msg = "Default data source factory list not definied for " + connectionPool;
					throw new ConnectionException(4, msg);
				} else {
					stage = "get default index";
					Integer index = indexes.get("");
					if (index == null) {
						logger.warn("No index defined for this connection pool: " + connectionPool);
						index = 0;
						indexes.put(connectionPool, 0);
					} else if (index.intValue() < 0 || index.intValue() >= bdsfl.size()) {
						logger.warn("Index " + index + " out of bounds for this connection pool: " + connectionPool);
						index = 0;
						indexes.put(connectionPool, 0);
					}
					logger.debug("Get default basic data sources factory: " + index.intValue());
					stage = "get default basic data source factory";
					bdsf = bdsfl.get(index.intValue());
					if (bdsf == null) {
						String msg = "Not found default data source factory for " + connectionPool;
						throw new ConnectionException(4, msg);
					}
					logger.debug("Get default basic data sources factory: " + bdsf.getJndiURL()
							+ " for this connection pool: " + connectionPool);
				}
			} else {
				stage = "get index for connection pool";
				Integer index = indexes.get(connectionPool);
				if (index == null) {
					logger.warn("No index defined for this connection pool: " + connectionPool);
					index = 0;
					indexes.put(connectionPool, 0);
				} else if (index.intValue() < 0 || index.intValue() >= bdsfl.size()) {
					logger.warn("Index " + index + " out of bounds for this connection pool: " + connectionPool);
					index = 0;
					indexes.put(connectionPool, 0);
				}
				logger.debug("Get basic data sources factory: " + index.intValue());
				stage = "get basic data source factory";
				bdsf = bdsfl.get(index.intValue());
				if (bdsf == null) {
					String msg = "Not found data source factory for " + connectionPool;
					throw new ConnectionException(4, msg);
				}
				logger.debug("Get basic data sources factory: " + bdsf.getJndiURL() + " for this connection pool: "
						+ connectionPool);
			}
			this.jndiName = bdsf.getJndiURL();

			stage = "get basic data source";
			BasicDataSource bds = bdsf.getBasicDataSource();
			if (bds == null) {
				String msg = "Data source not definied for data sources factory: " + bdsf.getJndiURL();
				throw new ConnectionException(4, msg);
			}
			stage = "get connection";
			conn = bds.getConnection();

			if (conn == null) {
				String msg = "Returned connection from data source is null for " + connectionPool;
				throw new ConnectionException(4, msg);
			}

		} catch (ConnectionException e) {
			logger.warn("ConnectionError: " + stage, e);
			throw e;
		} catch (SQLException e) {
			logger.warn("SQLError: " + stage, e);
			throw new ConnectionException(3, "Connection pool problem for " + connectionPool + ". " + e.getMessage(), e);
		} catch (Exception e) {
			logger.warn("Error: " + stage, e);
			throw new ConnectionException(4, "General exception getting connection for " + connectionPool + ". " + e.getMessage(), e);
		}

		// ----------------------------------------------------------------------
		// Zmiana dla podzielonego vtk z dataSource w applicationContext
		// try {
		// DataSource ds = null;
		// logger.debug("connectionPool: " + connectionPool);
		// if (connectionPool == null) {
		// connectionPool = "";
		// }
		// BasicDataSourceFactory bdsf =
		// DbConnectionFactory.dataSouce.get(connectionPool);
		// if (bdsf == null) {
		// logger.warn("No data source defined for this connection pool: " +
		// connectionPool);
		// bdsf = DbConnectionFactory.dataSouce.get("");
		// ds = bdsf.getBasicDataSource();
		// } else {
		// ds = bdsf.getBasicDataSource();
		// }
		// logger.debug("dataSource: " + ds);
		// if (ds == null) {
		// logger.error("Default data source not definied.");
		// }
		// conn = ds.getConnection();
		// } catch (SQLException e) {
		// throw new ConnectionException(3, "Connection pool problem:" +
		// e.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new ConnectionException(4, "General exception:" +
		// e.toString());
		// }
		// Zmiana dla podzielonego vtk z dataSource w applicationContext
		// ----------------------------------------------------------------------

		// ----------------------------------------------------------------------
		// Kod dla starego vtk bez dataSourde w applicationContext
		// try {
		// Context initContext = new InitialContext();
		// stage = "EnvContext";
		// Context envContext = (Context) initContext.lookup("java:/comp/env");
		// stage = "Lookup for " + connectionPool;
		// DataSource ds = (DataSource) envContext.lookup(connectionPool);
		// stage = "get connection";
		// conn = ds.getConnection();
		// } catch (NamingException e) {
		// throw new ConnectionException(2, "Context reading problem:" +
		// e.toString());
		// } catch (SQLException e) {
		// throw new ConnectionException(3, "Connection pool problem:" +
		// e.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new ConnectionException(4, "General exception:" +
		// e.toString());
		// }
		// Kod dla starego vtk bez dataSourde w applicationContext
		// ----------------------------------------------------------------------

		logger.debug("Get connection from connection pool success.");
		return conn;
	}

	public String getJndiName() {
		return this.jndiName;
	}

	/**
	 * Sets the dataSouces.
	 * 
	 * @param Map
	 *            <connectionPool, dataSouce>
	 */
	// public void setDataSouce(Map<String, BasicDataSourceFactory> dataSouce) {
	// DbConnectionFactory.dataSouce = dataSouce;
	// logger.debug("packageNames: " +
	// DbConnectionFactory.dataSouce.toString());
	// }
	public void setBasicDataSources(Map<String, List<BasicDataSourceFactory>> basicDataSources) {
		DbConnectionFactory.basicDataSources = basicDataSources;
		logger.debug("basicDataSources: " + basicDataSources);
		for (String key : basicDataSources.keySet()) {
			indexes.put(key, 0);
		}
		logger.debug("indexes: " + indexes);
	}

	/**
	 * Sets errorCodes
	 * 
	 * @param errorCodes
	 */
	public void setErrorCodes(int[] errorCodes) {
		if (errorCodes != null && errorCodes.length != 0) {
			DbConnectionFactory.errorCodes = new int[errorCodes.length];
			int i = 0;
			for (int val : errorCodes)
				DbConnectionFactory.errorCodes[i++] = val;
		} else {
			DbConnectionFactory.errorCodes = new int[3];
			DbConnectionFactory.errorCodes[0] = 22001;
			DbConnectionFactory.errorCodes[1] = 22002;
			DbConnectionFactory.errorCodes[2] = 22017;
		}
		logger.debug("errorCodes: " + DbConnectionFactory.errorCodes);
	}

	public static int[] getErrorCodes() {
		return DbConnectionFactory.errorCodes;
	}

	public static List<String> getRestartDSErrorMessages() {
		return restartDSErrorMessages;
	}

	public void setRestartDSErrorMessages(List<String> restartDSErrorMessages) {
		logger.debug("restartDSErrorMessages: " + restartDSErrorMessages);
		DbConnectionFactory.restartDSErrorMessages = restartDSErrorMessages;
	}
}
