package org.shiver.command;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.shiver.controller.ShiverController;
import org.shiver.model.SwimResult;
import org.shiver.utils.PrettyPrint;
import org.shiver.validator.ProxyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.support.logging.HandlerUtils;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Tom
 *
 */
@Component
public class SwimCommand implements CommandMarker, PropertyChangeListener
{	
	@Autowired
	private ShiverController controller;	
	private final String EMPTY_MESSAGE = "";
	private Logger logger = HandlerUtils.
			getLogger(SwimCommand.class);
			
	/**
	 * 
	 * @param target
	 * @param requests
	 * @param mixed
	 * @return
	 */
	@CliCommand(value = "swim", help = "Execute GET requests in a specified url")
	public String swim(
			@CliOption(key = {"target"}, mandatory = true, help = "The target url") final String target,
			@CliOption(key = {"sharks"}, mandatory = true, help = "The number of requests") final int sharks,
			@CliOption(key = {"mixed"} , mandatory = true, help = "Use mixed proxies") final boolean mixed)
	{
		if (!ProxyValidator.isValidTarget(target))
		{
			logger.severe(PrettyPrint.print("Invalid target url"));
			return EMPTY_MESSAGE;			
		}
				
		logger.info(PrettyPrint.printSwimResultHeader());
		
		controller.addSwimEventListener(this);		
		int status = controller.swim(target, sharks, mixed);
		
		return "Process ended with status code [" + status + "]" + OsUtils.LINE_SEPARATOR;
	}

	public void propertyChange(PropertyChangeEvent evt) 
	{
		//TODO why logger is not working with async tasks
		//logger.info(PrettyPrint.print(result));
		
		SwimResult result = (SwimResult) evt.getNewValue();
		System.out.println(PrettyPrint.print(result));			
	}
}
