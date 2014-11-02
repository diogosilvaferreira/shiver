package org.shiver.model;

import java.util.Date;

/**
 * 
 * @author Tom
 *
 */
public class SwimResult 
{
	private Date date;
	private String host;
	private int port;
	private String target;
	private String requestType;
	private String statusCode;
	
	/**
	 * @return the date
	 */
	public Date getDate() 
	{
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	/**
	 * @return the host
	 */
	public String getHost() 
	{
		return host;
	}
	
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) 
	{
		this.host = host;
	}
	
	/**
	 * @return the port
	 */
	public int getPort() 
	{
		return port;
	}
	
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}
	
	/**
	 * @return the target
	 */
	public String getTarget() 
	{
		return target;
	}
	
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) 
	{
		this.target = target;
	}
	
	/**
	 * @return the requestType
	 */
	public String getRequestType() 
	{
		return requestType;
	}
	
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) 
	{
		this.requestType = requestType;
	}
	
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() 
	{
		return statusCode;
	}
	
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) 
	{
		this.statusCode = statusCode;
	}	
}
