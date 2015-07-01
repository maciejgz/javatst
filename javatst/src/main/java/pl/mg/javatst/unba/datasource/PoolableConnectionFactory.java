/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.mg.javatst.unba.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.AbandonedConfig;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;


/**
 * A {@link PoolableObjectFactory} that creates
 * {@link PoolableConnection}s.
 * 
 * @author Piotr Zaborowski
 */
public class PoolableConnectionFactory extends org.apache.commons.dbcp.PoolableConnectionFactory {
    
    /** The log. */
    protected static Log log = LogFactory.getLog(PoolableConnectionFactory.class);
    
    /** The initial statement. */
    private String initialStatement = null;
    
    /**
     * Instantiates a new poolable connection factory.
     * 
     * @param connFactory the conn factory
     * @param pool the pool
     * @param stmtPoolFactory the stmt pool factory
     * @param validationQuery the validation query
     * @param validationQueryTimeout the validation query timeout
     * @param defaultReadOnly the default read only
     * @param defaultAutoCommit the default auto commit
     * @param defaultTransactionIsolation the default transaction isolation
     * @param defaultCatalog the default catalog
     * @param config the config
     * @param initialStatement the initial statement
     */
    public PoolableConnectionFactory(
            ConnectionFactory connFactory,
            ObjectPool pool,
            KeyedObjectPoolFactory stmtPoolFactory,
            String validationQuery,
            int validationQueryTimeout,
            boolean defaultReadOnly,
            boolean defaultAutoCommit,
            int defaultTransactionIsolation,
            String defaultCatalog,
            AbandonedConfig config,
            String initialStatement) {

        super(connFactory,pool,
                stmtPoolFactory,
                validationQuery,
                validationQueryTimeout,
                defaultReadOnly,
                defaultAutoCommit,
                defaultTransactionIsolation,
                defaultCatalog,
                config);
        
        this.initialStatement = initialStatement;
    } 
    
    /* (non-Javadoc)
     * @see org.apache.commons.dbcp.PoolableConnectionFactory#makeObject()
     */
    synchronized public Object makeObject() throws Exception {
        Object conn = super.makeObject();
        if(initialStatement!=null && conn instanceof Connection){
            try{
                log.debug("Prepare initial statement: " + initialStatement);
                PreparedStatement statement = ((Connection)conn).prepareStatement(initialStatement);
                log.debug("Executing initial statement: " + initialStatement);
                statement.execute();
                log.debug("Executed initial statement: " + initialStatement);
            }catch(SQLException e){
                log.error("SQLError: " + e.getMessage(),e);
            }catch(Exception e){
                log.fatal("Error: " + e.getMessage(),e);
            }
        }
        return conn;
    }
    
    /**
     * Gets the initial statement.
     * 
     * @return the initialStatement
     */
    public String getInitialStatement() {
        return initialStatement;
    }
    
    /**
     * Sets the initial statement.
     * 
     * @param initialStatement the initialStatement to set
     */
    public void setInitialStatement(String initialStatement) {
        this.initialStatement = initialStatement;
    }

}
