package org.shiver.command;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.shell.core.CommandResult;

/**
 * 
 * @author Tom
 *
 */
public class SwimCommandTest extends AbstractCommandTest 
{
	private final String COMMAND      = "swim";
	private final String TARGET_PARAM = "--target 'http://www.google.com'";
	private final String SHARKS_PARAM = "--sharks 10";
	private final String MIXED_PARAM  = "--mixed false";
	
	/**
	 * 
	 */
	@Test
	public void swimTest()
	{			
		String cmd = String.format("%s %s %s %s", 
				COMMAND, 
				TARGET_PARAM,
				SHARKS_PARAM,
				MIXED_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(true, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void swimNoMixedParamTest()
	{			
		String cmd = String.format("%s %s %s", 
				COMMAND, 
				TARGET_PARAM,
				SHARKS_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	/**
	 * 
	 */
	@Test
	public void swimNoSharksParamTest()
	{			
		String cmd = String.format("%s %s %s", 
				COMMAND, 
				TARGET_PARAM,
				MIXED_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void swimNoTargetParamTest()
	{			
		String cmd = String.format("%s %s %s", 
				COMMAND, 
				SHARKS_PARAM,
				MIXED_PARAM);
		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
	
	/**
	 * 
	 */
	@Test
	public void swimNoParamTest()
	{			
		String cmd = String.format("%s", COMMAND);		
		CommandResult cr = getShellComponent().
				executeCommand(cmd);
		
		Assert.assertEquals(false, cr.isSuccess());		
	}
}
