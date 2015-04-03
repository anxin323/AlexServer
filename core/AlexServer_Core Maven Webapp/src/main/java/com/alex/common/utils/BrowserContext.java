package com.alex.common.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

public class BrowserContext
{
	private HttpClient client;
	public static String HTTPS = "https";
	public static String HTTP = "http";

	private Cookie[] cookies = new Cookie[0];

	public BrowserContext(String host)
	{
		/*Protocol easyhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", easyhttps);*/
		
        client = new HttpClient();
				 
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		
		client.getHostConfiguration().setHost(host, 80, HTTP);

		
		
	}
	
//	public BrowserContext(String host, String proctolType)
//	{
//		Protocol easyhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
//		Protocol.registerProtocol("https", easyhttps);
//		client = new HttpClient();
//		// client.getHostConfiguration().setProxy("127.0.0.1", 8580);
//		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
//		client.getHostConfiguration().setHost(host, 80, proctolType);
//
//	}

	public void setCommonMethodHeaders(HttpMethodBase method)
	{
		// method.setRequestHeader( "Accept" , "*/*" );
		// method.setRequestHeader( "User-Agent" ,
		// "Mozilla/4.0(compatible; MSIE 7.0; Windows NT 5.1;");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.setRequestHeader("Connection", "Keep-Alive");
		method.setRequestHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13");
	}

	public String redirectToURL(String url)
	{
		GetMethod get = new GetMethod(url);
		return this.doGet(get);

	}

//	public String doPost(PostMethod post)
//	{
//		String responseString = null;
//		if (post == null)
//		{
//			return responseString;
//		}
//
//		try
//		{
//			if (this.getCookies() != null)
//			{
//				client.getState().addCookies(this.cookies);
//				post.addRequestHeader("Cookie", getCookiesAsStr());
//				
//				post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//				
//			}
//
//			this.setCommonMethodHeaders(post);
//			int statusCode = client.executeMethod(post);
//			cookies = client.getState().getCookies();
//			if(cookies.length > 0)
//			{
//				this.setCookies(cookies);
//			}
//			
//			responseString = post.getResponseBodyAsString();
//			if (statusCode == 301 || statusCode == 302)
//			{
//				String url = OAUtil.getUrl(post.getResponseHeader("Location").getValue());
//				responseString = redirectToURL(url);
//			}
//			post.releaseConnection();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			if (post != null)
//			{
//				post.releaseConnection();
//			}
//		}
//		return responseString;
//	}
	
//	public String doMultiPost(MultipartPostMethod post)
//	{
//		String responseString = null;
//		if (post == null)
//		{
//			return responseString;
//		}
//
//		try
//		{
//			if (this.getCookies() != null)
//			{
//				client.getState().addCookies(this.cookies);
//				post.addRequestHeader("Cookie", getCookiesAsStr());
//				post.addRequestHeader("Content-type", "multipart/form-data");
//				
//			}
//
//			this.setCommonMethodHeaders(post);
//			int statusCode = client.executeMethod(post);
//			cookies = client.getState().getCookies();
//			if(cookies.length > 0)
//			{
//				this.setCookies(cookies);
//			}
//			
//			responseString = post.getResponseBodyAsString();
//			if (statusCode == 301 || statusCode == 302)
//			{
//				String url = OAUtil.getUrl(post.getResponseHeader("Location").getValue());
//				responseString = redirectToURL(url);
//			}
//			post.releaseConnection();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			if (post != null)
//			{
//				post.releaseConnection();
//			}
//		}
//		return responseString;
//	}

	public String doGet(GetMethod get)
	{
		String responseString = null;
		if (get == null)
		{
			return responseString;
		}

		if (cookies != null)
		{
			client.getState().addCookies(cookies);
			get.addRequestHeader("Cookie", getCookiesAsStr());
		}

		try
		{
			setCommonMethodHeaders(get);
			int statusCode = client.executeMethod(get);
			cookies = client.getState().getCookies();
			if(cookies.length > 0)
			{
				this.setCookies(cookies);
			}
			responseString = get.getResponseBodyAsString();
			if (statusCode == 301 || statusCode == 302)
			{
				responseString = redirectToURL(get.getResponseHeader("Location").getValue());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			if (get != null)
			{
				get.releaseConnection();
			}
		}

		get.releaseConnection();
		return responseString;
	}

	// 查看cookie信息
	public void printCookies()
	{
		System.out.println(" ---------------Cookie---------------- ");
		if (cookies != null)
		{
			for (Cookie c : cookies)
			{
				System.out.println(c.getName() + " : " + c.getValue());
			}
		}
		else
		{
			System.out.println(" 没有设置Cookies ");
		}

		System.out.println(" ---------------end Cookie---------------- ");
	}

	public Cookie[] getCookies()
	{
		return cookies;
	}

	public void setCookies(Cookie[] cookies)
	{
		this.cookies = cookies;
	}

	public void addCookie(Cookie c)
	{
		if (cookies != null && cookies.length > 0)
		{
			Cookie[] others = new Cookie[cookies.length + 1];
			System.arraycopy(cookies, 0, others, 0, cookies.length);
			others[others.length - 1] = c;
			cookies = others;
		}
		else
		{
			cookies = new Cookie[1];
			cookies[0] = c;
		}
	}

	public HttpClient getClient()
	{
		return client;
	}

	public String getCookiesAsStr()
	{
		StringBuffer sb = new StringBuffer();
		Cookie c;
		for (int i = 0; i < this.cookies.length; i++)
		{
			c = cookies[i];
			sb.append(c.getName() + "=" + c.getValue());
			if (i < this.cookies.length - 1)
			{
				sb.append(";");
			}

		}
		return sb.toString();
	}
	
	public static String encodeURls(String url)
	{ 
		String urlString ="";
		try
		{
			  urlString = URLEncoder.encode(url, "UTF-8");   
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return urlString;
	}

	
	public static String encodeURl(String url)
	{
		try
		{
			url = URLDecoder.decode(url);
			url = new String(url.getBytes("ISO-8859-1"), "UTF-8");
			//url = new String(url.getBytes("ISO-8859-1"));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		/***************** ******************/
		String protocol = url.substring(0, url.indexOf("//") + 2);
		String paramString = "";
		
		if(url.indexOf("?") != -1)
		{
			paramString = url.substring(url.indexOf("?"), url.length());
		}
		url = url.substring(url.indexOf(protocol) + protocol.length(), url.length() - paramString.length());
		
		String[] tempUrlArray = url.split("/");
		url = protocol;
		for(int i = 0; i < tempUrlArray.length; i++)
		{
			if(tempUrlArray[i].indexOf("?") == -1)
			{
				url += URLEncoder.encode(tempUrlArray[i]);
				if(i < tempUrlArray.length - 1)
					url += "/";
					
			}
			else
			{
				if(!url.endsWith("/"))
				  url += "/" + tempUrlArray[i];
				else
				  url += tempUrlArray[i];
			}
		}
		
		url += paramString;
		
		/***************** ******************/
		int paramIndex = url.indexOf("?");
		if (paramIndex != -1)
		{
			StringBuffer urlLink = new StringBuffer(url.substring(0, paramIndex));
			String[] paramters = url.substring(paramIndex + 1, url.length()).split("&");

			if (paramters.length > 0)
			{
				urlLink.append("?");
			}

			for (int i = 0; i < paramters.length; i++)
			{
				String[] valueName = paramters[i].split("=");
				urlLink.append(URLEncoder.encode(valueName[0]));
				
				if(paramters[i].indexOf("=") != -1)
				  urlLink.append("=");
				
				if (valueName.length > 1)
				{
					urlLink.append(URLEncoder.encode(valueName[1]));
				}
				
				if (i < paramters.length - 1)
				{
					urlLink.append("&");
				}
			}
			
			url = urlLink.toString();
		}
		else
		{
			String paramStr = url.substring(url.lastIndexOf("/") + 1, url.length());
			StringBuffer urlLink = new StringBuffer(url.substring(0, url.lastIndexOf("/")));
			if(paramStr.indexOf("&") == -1)
			{
				return urlLink + "/" + URLEncoder.encode(paramStr);
			}
			
		}
		
		return url;
	}
	public static void main(String[] args) {
		System.err.println("http://localhost:8080/neldtvproject/login/login/login/loginAction!Auth.do");
	}
	
 
	
	
	public static String encodeURl1(String url)
	{
		
		try
		{
			url = URLDecoder.decode(url);
//			url = new String(url.getBytes("ISO-8859-1"));
			url = new String(url.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/***************** ******************/
		String protocol = url.substring(0, url.indexOf("//") + 2);
		String paramString = url.substring(url.indexOf("?"), url.length());
		url = url.substring(url.indexOf(protocol) + protocol.length(), url.length() - paramString.length());
		
		String[] tempUrlArray = url.split("/");
		url = protocol;
		for(int i = 0; i < tempUrlArray.length; i++)
		{
			if(tempUrlArray[i].indexOf("?") == -1)
			{
				url += URLEncoder.encode(tempUrlArray[i]);
				if(i < tempUrlArray.length - 1)
					url += "/";
					
			}
			else
			{
				if(!url.endsWith("/"))
				  url += "/" + tempUrlArray[i];
				else
				  url += tempUrlArray[i];
			}
		}
		
		url += paramString;
		
		/***************** ******************/
		int paramIndex = url.indexOf("?");
		if (paramIndex != -1)
		{
			StringBuffer urlLink = new StringBuffer(url.substring(0, paramIndex));
			String[] paramters = url.substring(paramIndex + 1, url.length()).split("&");

			if (paramters.length > 0)
			{
				urlLink.append("?");
			}

			for (int i = 0; i < paramters.length; i++)
			{
				String[] valueName = paramters[i].split("=");
				urlLink.append(URLEncoder.encode(valueName[0]));
				
				if(paramters[i].indexOf("=") != -1)
				  urlLink.append("=");
				
				if (valueName.length > 1)
				{
					urlLink.append(URLEncoder.encode(valueName[1]));
				}
				
				if (i < paramters.length - 1)
				{
					urlLink.append("&");
				}
			}
			
			url = urlLink.toString();
		}
		else
		{
			String paramStr = url.substring(url.lastIndexOf("/") + 1, url.length());
			StringBuffer urlLink = new StringBuffer(url.substring(0, url.lastIndexOf("/")));
			if(paramStr.indexOf("&") == -1)
			{
				return urlLink + "/" + URLEncoder.encode(paramStr);
			}
			
		}
		
		return url;
	}
	
	public static String encodeURl2(String url)
	{
		
		/***************** ******************/
		String protocol = url.substring(0, url.indexOf("//") + 2);
		String paramString = url.substring(url.indexOf("?"), url.length());
		url = url.substring(url.indexOf(protocol) + protocol.length(), url.length() - paramString.length());
		
		String[] tempUrlArray = url.split("/");
		url = protocol;
		for(int i = 0; i < tempUrlArray.length; i++)
		{
			if(tempUrlArray[i].indexOf("?") == -1)
			{
				url += URLEncoder.encode(tempUrlArray[i]);
				if(i < tempUrlArray.length - 1)
					url += "/";
					
			}
			else
			{
				if(!url.endsWith("/"))
				  url += "/" + tempUrlArray[i];
				else
				  url += tempUrlArray[i];
			}
		}
		
		url += paramString;
		
		/***************** ******************/
		int paramIndex = url.indexOf("?");
		if (paramIndex != -1)
		{
			StringBuffer urlLink = new StringBuffer(url.substring(0, paramIndex));
			String[] paramters = url.substring(paramIndex + 1, url.length()).split("&");

			if (paramters.length > 0)
			{
				urlLink.append("?");
			}

			for (int i = 0; i < paramters.length; i++)
			{
				String[] valueName = paramters[i].split("=");
				urlLink.append(URLEncoder.encode(valueName[0]));
				
				if(paramters[i].indexOf("=") != -1)
				  urlLink.append("=");
				
				if (valueName.length > 1)
				{
					urlLink.append(URLEncoder.encode(valueName[1]));
				}
				
				if (i < paramters.length - 1)
				{
					urlLink.append("&");
				}
			}
			
			url = urlLink.toString();
		}
		else
		{
			String paramStr = url.substring(url.lastIndexOf("/") + 1, url.length());
			StringBuffer urlLink = new StringBuffer(url.substring(0, url.lastIndexOf("/")));
			if(paramStr.indexOf("&") == -1)
			{
				return urlLink + "/" + URLEncoder.encode(paramStr);
			}
			
		}
		
		return url;
	}
}
