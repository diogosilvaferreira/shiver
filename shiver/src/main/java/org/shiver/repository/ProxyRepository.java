package org.shiver.repository;

import org.shiver.model.Proxy;

/**
 * 
 * @author Tom
 *
 */
public interface ProxyRepository extends Repository<Proxy>
{
	/**
	 * 
	 * @return
	 */
	public Proxy findDefaultProxy();
	
	/**
	 * 
	 */
	public void init();	
	
	/**
	 * 
	 */
	public void createSchema();
	
}
