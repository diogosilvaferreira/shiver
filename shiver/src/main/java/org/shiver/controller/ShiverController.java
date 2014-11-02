package org.shiver.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.shiver.model.Proxy;
import org.shiver.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ShiverController 
{
	@Autowired
	private ProxyRepository repository;
	
	private final int SWIM_EVENT_TERMINATED_STATUS = 1;
	private PropertyChangeSupport swimEventSupport = 
			new PropertyChangeSupport(this);
		
	/**
	 * 
	 * @param listener
	 */
	public void addSwimEventListener(PropertyChangeListener listener)
	{
		// remove all listeners first to not duplicate the log on terminal
		PropertyChangeListener[] listeners = swimEventSupport.getPropertyChangeListeners();
		for (PropertyChangeListener pcl : listeners)
		{
			swimEventSupport.removePropertyChangeListener(pcl);
		}
		
		swimEventSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Proxy> getRegisteredProxies()
	{
		return repository.findAll();
	}
	
	/**
	 * 
	 * @param proxy
	 * @return rowCount - number of rows affected in the database
	 */
	public int registerProxy(Proxy proxy)
	{
		if (!StringUtils.isEmpty(proxy.getUser()) 
				|| !StringUtils.isEmpty(proxy.getPassword()))
			proxy.setSecure(true);
		
		proxy.setDefault(false);
		
		return repository.insert(proxy);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int updateDefaultProxy(int id)
	{
		Proxy defaultProxy = repository.findDefaultProxy();
		if (defaultProxy != null)
		{
			defaultProxy.setDefault(false);
			repository.update(defaultProxy);
		}
		
		Proxy proxy = repository.findById(id);
		proxy.setDefault(true);
		
		return repository.update(proxy);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int removeProxy(int id)
	{
		Proxy proxy = repository.findById(id);
		if (proxy == null)
		{
			return 0;
		}
		
		return repository.delete(proxy);
	}
	
	/**
	 * 
	 * @param target - The url target
	 * @param sharks - The number of requests
	 * @param mixedProxies - true for mixed proxies, false otherwise
	 * @return status - swim event status
	 */
	public int swim(String target, int sharks, boolean mixedProxies)
	{
		Proxy defaultProxy  = repository.findDefaultProxy();
		List<Proxy> proxies = repository.findAll();
				
		ExecutorService executor = Executors.
				newFixedThreadPool(sharks);
					
		int min = 1;
		int max = proxies.size();
		Random random = new Random();
				
		for (int i = 0; i < sharks; i++)
		{
			Proxy proxy;
			if (mixedProxies)
			{				
				int index = random.nextInt(max - min + 1) + min;
				proxy = proxies.get(index);
			}
			else
			{
				proxy = defaultProxy;
			}
		
			executor.execute(new Shark(proxy, target, swimEventSupport));						
		}
		
		executor.shutdown();
		
		try
		{
			// hold it
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		}
		catch(Exception e)
		{
			//
		}
													
		return SWIM_EVENT_TERMINATED_STATUS;
	}
}
