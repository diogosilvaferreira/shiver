package org.shiver.command;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.shell.core.CommandResult;

public class ProxyListCommandTest extends AbstractCommandTest
{	
	private final String COMMAND = "proxy list";
	
	/**
	 * 
	 */
	@Test
	public void proxyListTest()
	{			
		String cmd = String.format("%s", COMMAND);		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(true, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxyListInvalidCommandTest()
	{			
		String cmd = String.format("%s", "proxy");		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxyListInvalidCommandTest2()
	{			
		String cmd = String.format("%s", "list");		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
}
