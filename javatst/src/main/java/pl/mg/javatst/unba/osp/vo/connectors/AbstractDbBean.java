/*
 * Created on 2007-02-06
 *
 */
package pl.mg.javatst.unba.osp.vo.connectors;

import org.apache.log4j.Logger;
import pl.mg.javatst.unba.datasource.BasicDataSource;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for obtaining DB connection from connection pool and providing
 * additional methods for typical Db connector. To be extended by other
 * connectors. Method dbCall() is empty method to be overwriten by bussiness
 * logic method.
 * 
 * Contains standard connector setters and getters; other can be added in
 * extending classes.
 * 
 * @author Piotr Zaborowski
 * 
 */
public class AbstractDbBean {
	/**
	 * Stage: preparing callable statement.
	 */
	public static String STAGEPREPARECALL = "PrepareCall";

	/**
	 * Stage: setting input and registering output parameters.
	 */
	public static String STAGEREGISTERPARAMS = "RegisterParameters";

	/**
	 * Stage: executing callable statement.
	 */
	public static String STAGEEXECUTE = "Execute";

	/**
	 * Stage: getting output parameters.
	 */
	public static String STAGEGETPARAMS = "Get Parmeters";

	protected String encoding = "cp1250";

	protected String stage = "";

	protected String errorCode = "0";

	protected String errorMessage = "";

	protected int sessionId = 0;

	protected Logger logger;

	protected String application = "";

	protected Connection conn = null;
	protected String systemName = "";
	protected String functionName = "";
	protected String packageName = "";

	/**
	 * Statement created
	 */
	protected CallableStatement cs = null;

	private final String[] polishSigns = { "ą", "ę", "ć", "ś", "ń", "ź", "ż", "ó", "ł", "Ą", "Ę", "Ć", "Ś", "Ń", "Ź",
			"Ż", "Ó", "Ł" };

	private final String[] voSigns = { "~a", "~e", "~c", "~s", "~n", "~x", "~z", "~o", "~l", "~A", "~E", "~C", "~S",
			"~N", "~X", "~Z", "~O", "~L" };

	protected String connectionPool = null;

	protected boolean transcode2En = false;

	protected long calculationTime = -2;

	protected long startTime = 0;

	public AbstractDbBean() {
		this.logger = Logger.getLogger(this.getClass());
	}

	public void callProcess(boolean errorCall) throws Exception {
		stage = "createDbConnectionFactory";
		DbConnectionFactory fact = new DbConnectionFactory();
		boolean result = false;
		do {
			stage = "get connection";
			if (errorCall) {
				logger.debug("callProcess: error call");
			}
			BasicDataSource bds = fact.getBasicDataSource(connectionPool);

			try {
				this.conn = fact.getConnection(connectionPool);
			} catch (ConnectionException e) {
				if (this.checkRestartErrorMessage(e.getMessage()) && !errorCall) {
					logger.debug("cought error message with restart execution at first call. Second (errorCall) call will be omitted.");
//					result = false;
//					this.removeDataSource(bds);
//					errorCall();
//					return;
					fact.restartDataSource(connectionPool);
					
					bds = fact.getBasicDataSource(connectionPool);
					this.conn = fact.getConnection(connectionPool);

				} else {
					throw e;
				}
			}

			logger.debug("jndiName: " + fact.getJndiName());
			try {
				stage = "db call";
				dbCall();
				result = true;
			} catch (SQLException e) {
				logger.warn("SQLState: " + e.getSQLState() + " SQLCode: " + e.getErrorCode() + " SQLMessage: " + e.getMessage().replaceAll("\n", " "));
				int code = e.getErrorCode();
				boolean errorOnList = false;
				for (int val : DbConnectionFactory.getErrorCodes()) {
					if ((code == val))
						errorOnList = true;
				}

				if (this.checkRestartErrorMessage(e.getMessage()) && !errorCall) {
					logger.debug("cought error message with restart execution at first call. Second (errorCall) call will be omitted.");
					result = false;
//					this.removeDataSource(bds);
					fact.restartDataSource(connectionPool);
//					errorCall();
					return;
				} else if (errorOnList) {
					result = false;
//					logger.warn("SQLErrorCode: " + e.getErrorCode());
//					logger.warn("SQLMessage: " + e.getMessage());
//					logger.warn("SQLState: " + e.getSQLState());

					if (bds != null) {
//						bds.close();
						fact.restartDataSource(connectionPool);
					}
					if (this.conn != null) {
						if (!this.conn.isClosed()) {
							this.conn.close();
						}
						this.conn = null;
					}
					fact.nextConnection(
							connectionPool,
							"sqlCode: " + e.getErrorCode() + " sqlMessage: " + e.getMessage().replaceAll("\n", " ") + ", sqlState: "
									+ e.getSQLState());
				} else {
					throw e;
				}

				/*
				 * if ((code == 22001) || (code == 22002)) { result = false;
				 * logger.warn("SQLErrorCode: " + e.getErrorCode());
				 * logger.warn("SQLMessage: " + e.getMessage());
				 * logger.warn("SQLState: " + e.getSQLState());
				 * 
				 * if (bds != null) { bds.close(); } if (this.conn != null) { if
				 * (!this.conn.isClosed()) { this.conn.close(); } this.conn =
				 * null; } fact.nextConnection(connectionPool); } else { throw
				 * e; }
				 */

			} finally {
				logger.debug("callProccessResult: " + result);
			}
			// if (!result) {
			// stage = "next connection";
			// fact.nextConnection(connectionPool);
			// }
		} while (!result);
	}

	/**
	 * 1.Get connection from pool using DbConnectionFactory 2.Call dbCall
	 * method. 3.Handle typical exceptions.
	 * 
	 */
	public void call() {

		try {
			this.startTime = System.currentTimeMillis();
			this.stage = "call process";

			callProcess(false);

		} catch (ConnectionException e) {
			this.errorCode = String.valueOf(e.getCode());
			if (this.errorCode.equals("0"))
				this.errorCode = "1";
			this.errorMessage = "Severity:critical" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: ConnectionError:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (SQLException e) {
			if (e.getErrorCode() == 4068) {
				logger.debug("SQLerror: java.sql.SQLException: ORA-04068: existing state of packages has been discarded.");
				errorCall();
			} else if (e.getMessage().contains("Sesja zablokowana przez innego uzytkownika")) {
				this.errorCode = "11";
				this.errorMessage = "Severity:minor" +
	            		";Object:" + application + ":" + this.getClass().toString() +
	                    ";System:" + this.systemName +
	                    ";Function:" + this.functionName +
	                    ";ConnectionPool:" + this.connectionPool +
	                    ";Text: SQLerror:" + e.getMessage() +
	                    ";ErrorCode: " + this.errorCode +
	                    ";Stage: " + stage;
	            this.errorMessage = errorMessage.replaceAll("\n", " ");
	            logger.error(this.errorMessage, e);
			} else {
				this.errorCode = "5";
				this.errorMessage = "Severity:minor" +
	            		";Object:" + application + ":" + this.getClass().toString() +
	                    ";System:" + this.systemName +
	                    ";Function:" + this.functionName +
	                    ";ConnectionPool:" + this.connectionPool +
	                    ";Text: SQLerror:" + e.getMessage() +
	                    ";ErrorCode: " + this.errorCode +
	                    ";Stage: " + stage;
	            this.errorMessage = errorMessage.replaceAll("\n", " ");
	            logger.error(this.errorMessage, e);
			}
		} catch (UnsupportedEncodingException e) {
			this.errorCode = "5";
			this.errorMessage = "Severity:major" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: UnsupportedEncodingError:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (Exception e) {
            this.errorCode = "5";
            this.errorMessage = "Severity:minor" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: Error:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (Throwable t) {
			this.errorCode = "5";
            this.errorMessage = "Severity:critical" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: Error:" + t.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.fatal(this.errorMessage, t);
		} finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
			}
			cs = null;
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
			conn = null;
			calculationTime = System.currentTimeMillis() - startTime;
			logger.debug("calculationTime: " + this.calculationTime);
		}
	}

	/**
	 * This method is called when some of the typical errors have occurred.
	 */
	private void errorCall() {
		try {
			if (cs != null)
				cs.close();
		} catch (SQLException e) {
		}
		cs = null;
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
		conn = null;
		try {

			// DbConnectionFactory fact = new DbConnectionFactory();
			// this.conn = fact.getConnection(connectionPool);
			// dbCall();

			callProcess(true);

		} catch (ConnectionException e) {
			this.errorCode = String.valueOf(e.getCode());
			if (this.errorCode.equals("0"))
				this.errorCode = "1";
			this.errorMessage = "Severity:critical" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: ConnectionError:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (SQLException e) {
			if (e.getErrorCode() == 4068) {
				logger.debug("SQLerror: java.sql.SQLException: ORA-04068: existing state of packages has been discarded.");
				errorCall();
			} else if (e.getMessage().contains("Sesja zablokowana przez innego uzytkownika")) {
				this.errorCode = "11";
				this.errorMessage = "Severity:minor" +
	            		";Object:" + application + ":" + this.getClass().toString() +
	                    ";System:" + this.systemName +
	                    ";Function:" + this.functionName +
	                    ";ConnectionPool:" + this.connectionPool +
	                    ";Text: SQLerror:" + e.getMessage() +
	                    ";ErrorCode: " + this.errorCode +
	                    ";Stage: " + stage;
	            this.errorMessage = errorMessage.replaceAll("\n", " ");
	            logger.error(this.errorMessage, e);
			} else {
				this.errorCode = "5";
				this.errorMessage = "Severity:minor" +
	            		";Object:" + application + ":" + this.getClass().toString() +
	                    ";System:" + this.systemName +
	                    ";Function:" + this.functionName +
	                    ";ConnectionPool:" + this.connectionPool +
	                    ";Text: SQLerror:" + e.getMessage() +
	                    ";ErrorCode: " + this.errorCode +
	                    ";Stage: " + stage;
	            this.errorMessage = errorMessage.replaceAll("\n", " ");
	            logger.error(this.errorMessage, e);
			}
		} catch (UnsupportedEncodingException e) {
			this.errorCode = "5";
			this.errorMessage = "Severity:major" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: UnsupportedEncodingError:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (Exception e) {
            this.errorCode = "5";
            this.errorMessage = "Severity:minor" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: Error:" + e.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.error(this.errorMessage, e);
		} catch (Throwable t) {
			this.errorCode = "5";
            this.errorMessage = "Severity:critical" +
            		";Object:" + application + ":" + this.getClass().toString() +
                    ";System:" + this.systemName +
                    ";Function:" + this.functionName +
                    ";ConnectionPool:" + this.connectionPool +
                    ";Text: Error:" + t.getMessage() +
                    ";ErrorCode: " + this.errorCode +
                    ";Stage: " + stage;
            this.errorMessage = errorMessage.replaceAll("\n", " ");
            logger.fatal(this.errorMessage, t);
		} finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
			}
			cs = null;
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
			conn = null;
		}
	}

	/**
	 * To be overwritten
	 * 
	 * @throws Exception
	 */
	protected void dbCall() throws Exception {

	}

	/**
	 * Return empty string in place of null.
	 * 
	 * @param s
	 * @return
	 */
	protected String safeNull(String s) {
		if (s == null)
			return "";
		else
			return s;
	}

    protected String safeNullObj(Object o) {
    	if (o == null)
    		return "";
    	else
    		return o.toString();
    }

	protected String safeToString(Object o) {
		if (o == null)
			return "NULL";
		else
			return o.toString();
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage.replaceAll("\n", " ");
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
		this.logger.debug("application: " + application);
	}

	/**
     * Sets the system name.
     * 
     * @param system name the new system name
     */
	public void setSystemName(String systemName) {
        this.systemName = systemName;
        logger.debug("systemName: " + systemName);
    }

	/**
	 * Method returning safe (empty in place of null) string from
	 * CallableStatement. String can be encoded to cp1250 or national signs can
	 * be replaced if transcode2En flag is set to true.
	 * 
	 * @param pos
	 *            order number in CallableStatement
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	protected String getString(int pos) throws UnsupportedEncodingException, SQLException {
		if (cs == null)
			return null;
		;
		if (encoding == null)
			return null;
		;
		if (cs.getString(pos) == null)
			return "";
		if (transcode2En)
			return encode2Vo(new String(cs.getBytes(pos), encoding));
		else
			return new String(cs.getBytes(pos), encoding);
	}

	/**
	 * Tie cp1250 encoded string parameter to CallableStatement parameter.
	 * 
	 * @param pos
	 *            order number in CallableStatement
	 * @param str
	 *            value
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	protected void setString(int pos, String str) throws UnsupportedEncodingException, SQLException {
		if (cs == null)
			return;
		logger.debug("setting parameter no" + pos + " value: " + encode2Db(str) + " length=" + encode2Db(str).length());
		if (str.indexOf("~") >= 0)
			cs.setBytes(pos, encode2Db(str).getBytes("CP1250"));
		else
			cs.setString(pos, str);
	}

	/**
	 * Replace Polish national signs putting '~' sign before english signs. Ie.:
	 * ~a in place of ą.
	 * 
	 * @param s
	 * @return
	 */
	protected String encode2Vo(String s) {
		if (transcode2En) {
			for (int i = polishSigns.length - 1; i >= 0; i--)
				s = s.replaceAll(polishSigns[i], voSigns[i]);
		}
		return s;
	}

	/**
	 * Replace Polish national signs putting '~' sign before english signs. Ie.:
	 * ~a in place of ą.
	 * 
	 * @param s
	 * @return
	 */
	protected String encode2Db(String s) {
		logger.debug("transcode2En: " + transcode2En);
		if (transcode2En) {
			// logger.debug("before"+s);
			for (int i = polishSigns.length - 1; i >= 0; i--)
				s = s.replaceAll(voSigns[i], polishSigns[i]);
			// logger.debug("after"+s);
		}
		return s;
	}

	/**
	 * 
	 * @return the calculationTime
	 */
	public String getCalculationTime() {
		return String.valueOf(calculationTime);
	}

	/**
	 * Return escaped xml.
	 * 
	 * @param val
	 *            xml value for escaping.
	 * @return
	 */
	protected String escapeXmlValue(String val) {
		StringBuffer buf = new StringBuffer(val.length() * 2);

		int i;
		for (i = 0; i < val.length(); ++i) {
			char ch = val.charAt(i);
			int intValue = ch;
			if (intValue == 34) {
				buf.append("&quot;");
			} else if (intValue == 38) {
				buf.append("&amp;");
			} else if (intValue == 60) {
				buf.append("&lt;");
			} else if (intValue == 62) {
				buf.append("&gt;");
			} else if (intValue == 39) {
				buf.append("&apos;");
			} else if (ch > 0x7F) {
				buf.append("&#");
				buf.append(intValue);
				buf.append(';');
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Method compares sqlError with error messages which need DS object
	 * restart.
	 * 
	 * @param errorMessage
	 * @return flag indicates if DS restart is needed
	 */
	private boolean checkRestartErrorMessage(String errorMessage) {
		logger.debug("checkRestartErrorMessage:message=" + errorMessage.replaceAll("\n", " "));
		if (DbConnectionFactory.getRestartDSErrorMessages() != null
				&& DbConnectionFactory.getRestartDSErrorMessages().size() > 0) {
			for (String restartErrorMessage : DbConnectionFactory.getRestartDSErrorMessages()) {
				if (errorMessage.toLowerCase().contains(restartErrorMessage.toLowerCase())) {
					logger.debug("message on resatrt list: \"" + errorMessage + "\"");
					return true;
				}
			}
		} else if (errorMessage.toLowerCase().contains("no more data to read from socket")
				|| errorMessage.toLowerCase().contains("datasource closed")) {
			return true;
		}
		return false;
	}
}
