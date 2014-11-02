package org.shiver.command;

import org.junit.Assert;
import org.junit.Test;
import org.shiver.controller.ShiverController;
import org.shiver.model.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandResult;

public class ProxySetCommandTest extends AbstractCommandTest
{
	private final String COMMAND  = "proxy set";
	private String idParam = "--id";
	
	@Autowired
	private ShiverController controller;
	
	/**
	 * 
	 */
	@Test
	public void proxySetTest()
	{			
		Proxy proxy = controller.
			getRegisteredProxies().
			get(0);
		
		idParam = idParam + " " + (proxy == null ? "999" : proxy.getId());				
		String cmd = String.format("%s %s", 
				COMMAND, 
				idParam);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(true, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxySetNoIdParamTest()
	{								
		String cmd = String.format("%s", COMMAND);		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	
}
