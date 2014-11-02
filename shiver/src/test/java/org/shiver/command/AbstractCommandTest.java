package org.shiver.command;

import org.junit.runner.RunWith;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Tom
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"classpath:META-INF/spring/spring-shell-plugin.xml"})
public abstract class AbstractCommandTest 
{
	protected Bootstrap bootstrap;
	protected JLineShellComponent shellComponent;
	
	/**
	 * 
	 */
	public AbstractCommandTest()
	{
		bootstrap = new Bootstrap();
		shellComponent = bootstrap.
				getJLineShellComponent();
	}
	
	/**
	 * 
	 * @return
	 */
	protected JLineShellComponent getShellComponent()
	{
		return shellComponent;
	}
}
