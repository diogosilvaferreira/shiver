package org.shiver.command;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShiverBannerProvider extends DefaultBannerProvider  
{
	private final String VERSION = "1.0.0";
	private final String WELCOME_MESSAGE = "Welcome to Shiver Terminal";
	private final String PROVIDER_NAME = "Shiver Terminal";
	
	public String getBanner() 
	{
		StringBuffer buf = new StringBuffer();
		buf.append("***********************************************************************************" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                  ,-                                             *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                ,'::|                                            *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                               /::::|                                            *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                             ,*::::o*                                      _..   *" + OsUtils.LINE_SEPARATOR);
		buf.append("*          ____........-------,..::?88b                                  ,-' /    *" + OsUtils.LINE_SEPARATOR);
		buf.append("*  _.--****. . . .      .   .  .  .  **`-._                           ,-' .;'     *" + OsUtils.LINE_SEPARATOR);
		buf.append("* <. - :::::o......  ...   . . .. . .  .  .**--._                  ,-'. .;'       *" + OsUtils.LINE_SEPARATOR);
		buf.append("*  `-._  ` `*:`:`:`::||||:::::::::::::::::.:. .  **--._ ,*|     ,-*.  .;'         *" + OsUtils.LINE_SEPARATOR);
		buf.append("*      ***_=--       //'doo.. ````:`:`::::::::::.:.:.:. .`-`._-'.   .;'           *" + OsUtils.LINE_SEPARATOR);
		buf.append("*          **--.__     P(      \\               ` ``:`:``:::: .   .;'              *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                *\\**--.:-.     `.                             .:/                *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                  \\. /    `-._   `.**-----.,-..::(--*.\\**`. `:  \\                *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                    `P         `-._\\         `-:\\          `.`:  \\               *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                    **            *            `-._)             *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                                                                 *" + OsUtils.LINE_SEPARATOR);
		buf.append("* //shiver v." + this.getVersion() + "                                                                *" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                                                                 *" + OsUtils.LINE_SEPARATOR);
		buf.append("***********************************************************************************" + OsUtils.LINE_SEPARATOR);
					
		return buf.toString();
	}

	public String getVersion() 
	{
		return VERSION;
	}

	public String getWelcomeMessage() 
	{
		return WELCOME_MESSAGE +
					OsUtils.LINE_SEPARATOR;
	}
	
	@Override
	public String getProviderName() 
	{
		return PROVIDER_NAME;
	}
	
}
