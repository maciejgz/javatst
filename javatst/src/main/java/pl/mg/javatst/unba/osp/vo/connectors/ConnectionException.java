/*
 * Created on 2007-01-22
 *
 */
package pl.mg.javatst.unba.osp.vo.connectors;

/**
 * Standard exception with code and message.
 * @author Piotr Zaborowski
 *
 */
public class ConnectionException extends Exception {
    private static final long serialVersionUID = -2198915657682088994L;
    private String message;
    private int code;
    
    public ConnectionException(int code, String message){
        super(message);
    	this.code = code;
        this.message = message;
    }
    
    public ConnectionException(int code, String message, Throwable cause){
        super(message, cause);
    	this.code = code;
        this.message = message;
    }
    
    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
     
}
