package org.shiver.command;

import java.util.logging.Logger;

import org.shiver.controller.ShiverController;
import org.shiver.model.Proxy;
import org.shiver.utils.PrettyPrint;
import org.shiver.validator.ProxyValidator;
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
public class ProxyAddCommand  implements CommandMarker
{
	@Autowired
	private ShiverController controller;	
	private final String EMPTY_MESSAGE = "";
	private Logger logger = HandlerUtils.
			getLogger(ProxyAddCommand.class);
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * @return
	 */
	@CliCommand(value = "proxy add", help = "Register a proxy configuration in the database")
	public String proxyAdd(
			@CliOption(key = {"host"}, mandatory = true, help = "The proxy host") final String host,
			@CliOption(key = {"port"}, mandatory = true, help = "The proxy port") final int port,
			@CliOption(key = {"user"}, mandatory = false, help = "The username for proxy authentication") final String user,
			@CliOption(key = {"password"}, mandatory = false, help = "The password for proxy authentication") final String password)
	{
		Proxy proxy = new Proxy();
		proxy.setHost(host);
		proxy.setPort(port);
		proxy.setUser(user);
		proxy.setPassword(password);
		
		if (!ProxyValidator.validate(proxy))
		{
			logger.severe(PrettyPrint.print("Invalid proxy configuration"));
			return EMPTY_MESSAGE;
		}
				
		int rowCount = controller.registerProxy(proxy);
		if (rowCount > 0)
		{
			return PrettyPrint.
					print("Proxy successfuly registered!");
		}
					
		logger.severe(PrettyPrint.print("Unable to regiter the proxy configuration"));
		return EMPTY_MESSAGE;
	}
	
}
