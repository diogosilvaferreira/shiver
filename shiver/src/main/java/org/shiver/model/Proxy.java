package org.shiver.model;

/**
 * 
 * @author Tom
 *
 */
public class Proxy 
{
	private int id;
	private String host;
	private int port;
	private boolean isSecure;
	private boolean isDefault;
	private String user;
	private String password;
	
	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
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
	 * @return the user
	 */
	public String getUser() 
	{
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) 
	{
		this.user = user;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * @return the isSecure
	 */
	public boolean isSecure() 
	{
		return isSecure;
	}
	
	/**
	 * @param isSecure the isSecure to set
	 */
	public void setSecure(boolean isSecure) 
	{
		this.isSecure = isSecure;
	}
	
	/**
	 * @return the isDefault
	 */
	public boolean isDefault() 
	{
		return isDefault;
	}
	
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefault(boolean isDefault) 
	{
		this.isDefault = isDefault;
	}	
}
