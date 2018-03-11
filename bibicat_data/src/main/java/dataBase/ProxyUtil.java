package dataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class ProxyUtil {

	public static Proxy getProxy() {
		String ip_port = RedisUtil.sPop(RedisUtil.ip);
		if(null != ip_port) {
			String[] ip_prot_list = ip_port.split(":");
			String ip = ip_prot_list[0];
			Integer port = Integer.valueOf(ip_prot_list[1]);
			boolean test = testIp(ip, port);
			if(test) {
				InetSocketAddress addr = new InetSocketAddress(ip,port);
				RedisUtil.sAdd(RedisUtil.ip, ip_port);
				return new Proxy(Proxy.Type.HTTP, addr);
			}
		}
		return null;
	}
	
	public static boolean testIp(String ip,Integer port) {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip,port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String s = convertStreamToString(in);
		if (s.indexOf("baidu") > 0) {// 有效IP
			return true;
		}
		return false;
	}
	
	public static String convertStreamToString(InputStream is) {
        if (is == null) {
        	 return "";
        }
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
        try {
        	while ((line = reader.readLine()) != null) {
        		sb.append(line + "/n");
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	try {
        		is.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
        return sb.toString();
	}
}
