package org.shiver.command;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.shell.core.CommandResult;

/**
 * 
 * @author Tom
 *
 */
public class ProxyAddCommandTest extends AbstractCommandTest
{
	private final String COMMAND        = "proxy add";
	private final String HOST_SSL_PARAM = "--host 'https://192.168.0.1'";
	private final String HOST_PARAM     = "--host 'http://192.168.0.1'";
	private final String PORT_PARAM     = "--port 80";
	private final String USER_PARAM     = "--user 'test'";
	private final String PASSWORD_PARAM = "--password 'test'";
	
	/**
	 * 
	 */
	@Test
	public void proxyAddTest()
	{			
		String cmd = String.format("%s %s %s", 
				COMMAND, 
				HOST_PARAM,
				PORT_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(true, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxyAddSSLTest()
	{			
		String cmd = String.format("%s %s %s %s %s", 
				COMMAND, 
				HOST_SSL_PARAM,
				PORT_PARAM,
				USER_PARAM,
				PASSWORD_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(true, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxyAddNoHostParamTest()
	{			
		String cmd = String.format("%s %s", 
				COMMAND, 
				PORT_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void proxyAddNoPortParamTest()
	{			
		String cmd = String.format("%s %s", 
				COMMAND, 
				HOST_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
		
	
}
