package org.shiver.command;

import java.util.List;

import org.shiver.controller.ShiverController;
import org.shiver.model.Proxy;
import org.shiver.utils.PrettyPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Tom
 *
 */
@Component
public class ProxyListCommand implements CommandMarker 
{	
	@Autowired
	private ShiverController controller;		
	private final String NO_PROXY_FOUND_MESSAGE = "Proxy table is empty.";	
	
	/**
	 * 
	 * @return
	 */
	@CliCommand(value = "proxy list", help = "List all registered proxies")
	public String proxyList()
	{
		List<Proxy> proxies = controller.getRegisteredProxies();
		if (proxies.size() == 0)
			return PrettyPrint.print(NO_PROXY_FOUND_MESSAGE);
		
		return PrettyPrint.print(proxies);
	}
	
}
