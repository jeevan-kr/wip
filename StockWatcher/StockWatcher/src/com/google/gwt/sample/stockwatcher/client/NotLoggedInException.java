/**
 * 
 */
package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

/**
 * @author jeeva_000
 *
 */
public class NotLoggedInException extends Exception implements Serializable {

	  public NotLoggedInException() {
	    super();
	  }

	  public NotLoggedInException(String message) {
	    super(message);
	  }

	}