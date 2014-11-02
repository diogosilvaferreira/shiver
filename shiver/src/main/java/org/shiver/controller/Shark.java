package org.shiver.controller;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.shiver.model.Proxy;
import org.shiver.model.SwimResult;

/**
 * 
 * @author Tom
 *
 */
public class Shark implements Runnable
{	
	private Proxy proxy;
	private String target;
	private PropertyChangeSupport swimEventSupport;
	
	private final String SWIM_EVENT = "SWIM_TERMINATED";
	
	/**
	 * 
	 * @param proxy
	 * @param target
	 */
	public Shark(Proxy proxy, 
			String target, 
			PropertyChangeSupport swimEventSupport)
	{
		this.proxy = proxy;
		this.target = target;
		this.swimEventSupport = swimEventSupport;
	}
	
	
	public void run() 
	{
		SwimResult result = new SwimResult();
		HttpResponse response = null;	
			
		try 
		{
			if (proxy == null)
			{
				result.setHost("no proxy");
				result.setPort(0);
				response = executeSimpleHttpGet();			
			}
			else if (proxy.isSecure())
			{
				result.setHost(proxy.getHost());
				result.setPort(proxy.getPort());
				response = executeHttpGetWithSSLProxy();
			}
			else
			{
				result.setHost(proxy.getHost());
				result.setPort(proxy.getPort());
				response = executeHttpGetWithProxy();
			}
		}						
		catch (Exception e)
		{
			result.setStatusCode("500 - Internal Server Error");
		}
		finally
		{
			result.setDate(new Date(System.currentTimeMillis()));			
			result.setRequestType("GET");
			result.setStatusCode(response.getStatusLine().toString());
			result.setTarget(target);
			
			swimEventSupport.firePropertyChange(
					SWIM_EVENT, 
					null, 
					result);	
		}
	}
	
	/**
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	private HttpResponse executeSimpleHttpGet() throws ClientProtocolException, IOException
	{		
		HttpClient httpClient = HttpClientBuilder.
				create().
				build();
		
		HttpGet httpGet = new HttpGet(target);
		return httpClient.execute(httpGet);
	}
	
	/**
	 * 
	 * @param address
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private HttpResponse executeHttpGetWithProxy() throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String proxyHost = formatProxyHost();
		String address = fomartTarget();
		
		HttpHost httpTarget = new HttpHost(address);
        HttpHost httpProxy = new HttpHost(proxyHost, proxy.getPort());
        
        RequestConfig config = RequestConfig.custom()
                .setProxy(httpProxy)
                .build();
        
        HttpGet request = new HttpGet("/");
        request.setConfig(config);
        
        return httpClient.execute(httpTarget, request);
	}
	
	/**
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse executeHttpGetWithSSLProxy() throws ClientProtocolException, IOException
	{
		String proxyHost = formatProxyHost();
		String address = fomartTarget();
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyHost, proxy.getPort()),
                new UsernamePasswordCredentials(proxy.getUser(), proxy.getPassword()));
        
		CloseableHttpClient httpClient = HttpClients.
				custom().
				setDefaultCredentialsProvider(credsProvider).
				build();
				
		HttpHost httpTarget = new HttpHost(address);
        HttpHost httpProxy = new HttpHost(proxyHost, proxy.getPort());
        
        RequestConfig config = RequestConfig.custom()
                .setProxy(httpProxy)
                .build();
        
        HttpGet request = new HttpGet("/");
        request.setConfig(config);
        
        return httpClient.execute(httpTarget, request);
	}
	
	/**
	 * 
	 * @return
	 */
	private String formatProxyHost()
	{
		String proxyHost = proxy.getHost().toLowerCase();
		if (proxyHost.contains("http://") || proxyHost.contains("https://"))
		{			
			proxyHost = proxyHost.replaceAll("http://", "");
			proxyHost = proxyHost.replaceAll("https://", "");					
		}
		
		return proxyHost;
	}
	
	/**
	 * 
	 * @return
	 */
	private String fomartTarget()
	{
		String address = target.toLowerCase();		
		if (address.contains("http://") || address.contains("https://"))
		{
			address = address.replaceAll("http://", "");
			address = address.replaceAll("https://", "");
		}
		
		return address;
	}
}
