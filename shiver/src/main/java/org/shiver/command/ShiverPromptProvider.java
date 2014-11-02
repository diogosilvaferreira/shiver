package org.shiver.command;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShiverPromptProvider extends DefaultPromptProvider 
{
	private final String PROMPT = "shiver>";
	
	@Override
	public String getPrompt() 
	{
		return PROMPT;
	}

	
	@Override
	public String getProviderName() 
	{
		return ShiverPromptProvider.
				class.
				getName();
	}
}
