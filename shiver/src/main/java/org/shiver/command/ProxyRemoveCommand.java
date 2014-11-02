package org.shiver.command;

import java.util.logging.Logger;

import org.shiver.controller.ShiverController;
import org.shiver.utils.PrettyPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.support.logging.HandlerUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Tom
 *
 */
@Component
public class ProxyRemoveCommand implements CommandMarker
{
	@Autowired
	private ShiverController controller;	
	private final String EMPTY_MESSAGE = "";
	private Logger logger = HandlerUtils.
			getLogger(ProxyRemoveCommand.class);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@CliCommand(value = "proxy remove", help = "Remove a proxy from the database")
	public String proxyRemove(
			@CliOption(key = {"id"}, mandatory = true, help = "The proxy id") final int id)
	{
		if (id < 1)
		{
			logger.severe(PrettyPrint.print("Invalid proxy id"));
			return EMPTY_MESSAGE;
		}
		
		int rowCount = controller.removeProxy(id);
		if (rowCount > 0)
		{
			return PrettyPrint.print("Proxy successfuly removed!");
		}
					
		logger.severe(PrettyPrint.print("Invalid proxy id"));
		return EMPTY_MESSAGE;
	}
}
