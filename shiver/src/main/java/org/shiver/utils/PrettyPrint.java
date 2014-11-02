package org.shiver.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import org.shiver.model.Proxy;
import org.shiver.model.SwimResult;
import org.springframework.shell.support.table.Table;
import org.springframework.shell.support.table.TableHeader;
import org.springframework.shell.support.table.TableRenderer;
import org.springframework.shell.support.table.TableRow;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Tom
 *
 */
public class PrettyPrint
{	
	private static SimpleDateFormat dateFormat = 
			new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private static final String[] SWIM_TABLE_COLUMN_NAMES =
		{
			"date",
			"proxy_host",
			"proxy_port",
			"target",
			"type",
			"status"
		};
	
	private static final String [] PROXY_TABLE_COLUMN_NAMES =
		{
			"id",
			"host",
			"port",
			"user",
			"password",
			"default_proxy",
			"secure"
		};
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String print(String message)
	{
		String pattern = OsUtils.LINE_SEPARATOR + 
				"%s" + OsUtils.LINE_SEPARATOR;
		
		return String.format(pattern, message);
	}
	
	/**
	 * 
	 * @param proxies
	 * @return
	 */
	public static String print(List<Proxy> proxies)
	{				
		Table table = new Table();
		for (int i = 0; i < PROXY_TABLE_COLUMN_NAMES.length; i++)
		{
			table.addHeader(i, new TableHeader(PROXY_TABLE_COLUMN_NAMES[i]));
		}
					
		for (Proxy proxy : proxies)
		{
			TableRow row = table.newRow();
			
			row.addValue(0, ""+proxy.getId());
			row.addValue(1, proxy.getHost());
			row.addValue(2, ""+proxy.getPort());
			row.addValue(3, emptyStringIfNull(proxy.getUser()));
			row.addValue(4, emptyStringIfNull(proxy.getPassword()));
			row.addValue(5, ""+proxy.isDefault());
			row.addValue(6, ""+proxy.isSecure());
										
		}
				
		return TableRenderer.
				renderTextTable(table) + 
				OsUtils.LINE_SEPARATOR;
		
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public static String print(SwimResult result)
	{
		StringBuilder builder = new StringBuilder();
		String row = 
				String.format("%-23s%-25s%-14s%-40s%-7s%-5s", 
						dateFormat.format(result.getDate()),
						result.getHost(),
						result.getPort(),
						result.getTarget(),
						result.getRequestType(),
						result.getStatusCode());
								
			builder.append(row);			
			builder.append(OsUtils.LINE_SEPARATOR);
			
			return builder.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public static String printSwimResultHeader()
	{
		StringBuilder builder = new StringBuilder();
		String header = 
				String.format("%-23s%-25s%-14s%-40s%-7s%-5s", 
						SWIM_TABLE_COLUMN_NAMES[0],
						SWIM_TABLE_COLUMN_NAMES[1],
						SWIM_TABLE_COLUMN_NAMES[2],
						SWIM_TABLE_COLUMN_NAMES[3],
						SWIM_TABLE_COLUMN_NAMES[4],
						SWIM_TABLE_COLUMN_NAMES[5]);
					
			builder.append(OsUtils.LINE_SEPARATOR);
			builder.append(header);
			builder.append(OsUtils.LINE_SEPARATOR);
			builder.append("--------------------------------------------------------------"
					+ "----------------------------------------------------------------");			
			
			return builder.toString();
	}
	
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	private static String emptyStringIfNull(String str)
	{
		return StringUtils.isEmpty(str) ? "" : str;
	}
	
}
