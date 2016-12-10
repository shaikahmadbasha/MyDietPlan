/**
 * 
 */
package com.diet.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author learning
 *
 */
public abstract class BaseProcessor {

	public abstract Object processRequest(HttpServletRequest request, HttpServletResponse response);
	public abstract Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonRequest);
}
