package org.shiver.validator;

import org.apache.commons.validator.routines.UrlValidator;
import org.shiver.model.Proxy;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Tom
 *
 */
public class ProxyValidator 
{
	private static UrlValidator urlValidator = 
			new UrlValidator(
					new String[]{"http", "https"}, 
					UrlValidator.ALLOW_ALL_SCHEMES);
	
	/**
	 * 
	 * @param proxy
	 * @return
	 */
	public static boolean validate(Proxy proxy) 
	{
		if (StringUtils.isEmpty(proxy))
			return false;
		
		if (!urlValidator.isValid(proxy.getHost()))
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isValidTarget(String url)
	{
		return urlValidator.isValid(url);
	}
	
	
}
