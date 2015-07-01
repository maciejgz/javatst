//
// $Id$
//

package pl.mg.javatst.unba.datasource;

import org.apache.commons.dbcp.DelegatingConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.ObjectPool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/**
 * DataSource pool based on object pool.
 */
public class PoolableDataSource implements DataSource {
	
    /** The log. */
    protected static Log log = LogFactory.getLog(PoolableDataSource.class);
    
    /** The _log writer. */
    protected PrintWriter _logWriter = null;
    
    /** The _pool. */
    protected ObjectPool _pool = null;
    
    /** The critical codes. */
    protected int[] criticalCodes = new int[] {1000,1001,1003,1555,1562};
    
	/**
	 * Instantiates a new poolable data source.
	 */
	public PoolableDataSource() {
		
	}
	
	/**
	 * Instantiates a new poolable data source.
	 * 
	 * @param pool the pool
	 */
	public PoolableDataSource(ObjectPool pool) {
		_pool = pool;
	}
	
	/**
	 * Sets the pool.
	 * 
	 * @param pool the new pool
	 * 
	 * @throws IllegalStateException the illegal state exception
	 * @throws NullPointerException the null pointer exception
	 */
	public void setPool(ObjectPool pool) throws IllegalStateException, NullPointerException {
        if(null != _pool) {
            throw new IllegalStateException("Pool already set");
        } else if(null == pool) {
            throw new NullPointerException("Pool must not be null.");
        } else {
            _pool = pool;
        }
    }
	
	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getConnection()
	 */
	public Connection getConnection() throws SQLException {
	    log.debug("getConnection init");
		try {
			Connection connection = (Connection)_pool.borrowObject();
			
			log.debug("getConnection borrow");
			if (connection != null) {
				connection = new PoolGuardConnectionWrapper(connection);
				log.debug("getConnection wrapper");
			}
			log.debug("getConnection connection: " + connection);
			int active = _pool.getNumActive();
            int idle = _pool.getNumIdle();
            log.debug("getConnection active: " + active);
            log.debug("getConnection idle: " + idle);
            return connection;
		} catch(NoSuchElementException e) {
		    log.warn("NoSuchElementError: " + e.getMessage(),e);
			throw new SQLException("Cannot get a connection, pool exhausted " + e.getMessage(), e);
		} catch(RuntimeException e) {
            log.warn("RuntimeError: " + e.getMessage(),e);
		    throw e;
		} catch(SQLException e) {
		    log.warn("SQLError: " + e.getMessage(),e);
            throw e;
		} catch(Exception e) {
		    log.warn("Error: " + e.getMessage(), e);
			throw new SQLException("Cannot get a connection, general error " + e.getMessage(), e);
		}
	}

	/**
	 * Throws {@link UnsupportedOperationException}.
	 * Do this configuration within my {@link ObjectPool}.
	 * 
	 * @param username the username
	 * @param password the password
	 * 
	 * @return the connection
	 * 
	 * @throws UnsupportedOperationException 	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection(String username, String password) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Filter sql exception.
	 * 
	 * @param connection the connection
	 * @param exception the exception
	 * 
	 * @throws SQLException the SQL exception
	 */
	protected void filterSqlException(PoolGuardConnectionWrapper connection, SQLException exception) throws SQLException {
		if (exception == null) {
			return;
		}
		int code = exception.getErrorCode();
		log.debug("filteringException code: " + code, exception);
		if (code > 0 && criticalCodes != null) {
			if (Arrays.binarySearch(criticalCodes, code) >=0) {
				try {
					log.warn("SQLException code=" + code + " in critical codes list. connection marked as invalid");
					connection.setInvalid(true);
				} catch (Exception e) {
					log.warn(e.getMessage(), e);
				}
			}
		}
		throw exception;
	}
	
	/**
	 * Sets the critical codes.
	 * 
	 * @param codes the new critical codes
	 */
	public void setCriticalCodes(int[] codes) {
        if (codes != null) {
            criticalCodes = (int[]) codes.clone();
            Arrays.sort(criticalCodes);
            StringBuffer sb = new StringBuffer();
            int i;
            sb.append("codes is ");
            for (i=0; i<codes.length; i++) {
                sb.append(' ').append(Integer.toString(codes[i]));
            }
            log.info(sb);
        }
        else {
            log.warn("codes are null using default codeset");
            this.criticalCodes = new int[] {1000,1001,1003,1555,1562};
        }
    }
	
	/**
	 * Gets the critical codes.
	 * 
	 * @return the critical codes
	 */
	public int[] getCriticalCodes() {
	    return this.criticalCodes;
	}
    
	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter out) throws SQLException {
        _logWriter = out;       
    }
	
    /* (non-Javadoc)
     * @see javax.sql.CommonDataSource#getLogWriter()
     */
    public PrintWriter getLogWriter() throws SQLException {
        return _logWriter;
    }
    
    /**
     * Throws {@link UnsupportedOperationException}.
     * Do this configuration within my {@link ObjectPool}.
     * 
     * @param seconds the seconds
     * 
     * @throws UnsupportedOperationException      * @throws SQLException the SQL exception
     */
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Throws {@link UnsupportedOperationException}.
     * Do this configuration within my {@link ObjectPool}.
     * 
     * @return the login timeout
     * 
     * @throws UnsupportedOperationException      * @throws SQLException the SQL exception
     */
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }    
    
    /**
     * The Class PoolGuardConnectionWrapper.
     */
    private class PoolGuardConnectionWrapper extends DelegatingConnection {

        /** The invalid. */
        private boolean invalid;
    
        /**
         * Instantiates a new pool guard connection wrapper.
         * 
         * @param delegate the delegate
         */
        PoolGuardConnectionWrapper(Connection delegate) {
            super(delegate);
            invalid = false;
            _closed = false;
        }

        /**
         * Sets the invalid.
         * 
         * @param b the new invalid
         */
        public void setInvalid(boolean b) {
            invalid = b;            
        }

        /* (non-Javadoc)
         * @see org.apache.commons.dbcp.DelegatingConnection#handleException(java.sql.SQLException)
         */
        protected void handleException(SQLException ex) throws SQLException {
            filterSqlException(this, ex);
            super.handleException(ex);
        }
        
        /* (non-Javadoc)
         * @see org.apache.commons.dbcp.DelegatingConnection#passivate()
         */
        protected void passivate() throws SQLException {
            
        }
        
        /* (non-Javadoc)
         * @see org.apache.commons.dbcp.DelegatingConnection#close()
         */
        public void close() throws SQLException {
            log.debug("close connection");
            if (invalid) {
                try {
                    log.debug("close invalidating connection");
                    _pool.invalidateObject(getDelegate());
                    _closed = true;
                } catch (Exception e) {
                    log.warn("problem in connection invalidation.", e);
                }
            }
            else {
                log.debug("close connection is valid");
                super.close();
                int active = _pool.getNumActive();
                int idle = _pool.getNumIdle();
                log.debug("close connection active: " + active);
                log.debug("close connection idle: " + idle);
                log.debug("close connection success");
            }
        }

        @Override
        public void setSchema(String schema) throws SQLException {

        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }
    }

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		
		return null;
	}
    
}
