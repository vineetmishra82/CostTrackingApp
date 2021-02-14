package Exceptions;



public class CostManagerException extends Exception {

	/**
	 * Follows the standards of Exception Class
	 */
	private static final long serialVersionUID = 1L;
	
	 public CostManagerException(String message) {
	        super(message);
	    }
	 
	 public CostManagerException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
