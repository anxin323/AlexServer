package com.alex.common.utils;


import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

import java.util.ResourceBundle;

public class IPUtil {
	public static String serverIp;
	
	public static String getServerIp() {
		if(serverIp==null){
			//ResourceBundle resource = ResourceBundle.getBundle("config");
			//serverIp = resource.getString("cimServerIp");
			serverIp = "192.168.1.120";
		}
		return serverIp;
	}
	/**
	 * window or linux 
	 * @return
	 */
	public static String getWinOrLinuxIpAddress(){
		String ipAdd= "";
		if(IPUtil.getEth0IP().equals("")){
			try {
				System.out.println("Window run HOST:"+InetAddress.getLocalHost().getHostAddress());
				ipAdd = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		else{
			ipAdd = getEth0IP();
			System.out.println("Linux  run HOST:"+ipAdd);
		}
		return ipAdd;
	}
    /**
     * get ech0IP
     * @return
     */
    public static String getEth0IP() {
    	NetworkInterface netInterface = null;
    	String ip = "";
		try {
			netInterface = getEth0NetworkInterface();
			if (netInterface != null) {
	            Enumeration addresses = netInterface.getInetAddresses();
	            for (Object obj : Collections.list(addresses)) {
	                InetAddress inetAddress = (InetAddress) obj;
	                if (inetAddress instanceof Inet6Address) {
	                    continue;
	                } else {
	                    ip = inetAddress.getHostAddress();
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ip;
    }

    /**
     * network ech0
     * @return
     * @throws Exception
     */
    public static NetworkInterface getEth0NetworkInterface() throws Exception {
        Enumeration nets = NetworkInterface.getNetworkInterfaces();
        NetworkInterface netInterfaceEth0 = null;
        for (Object obj : Collections.list(nets)) {
            NetworkInterface netInterface = (NetworkInterface) obj;
            if (netInterface.getName().equals("eth0")) {
                netInterfaceEth0 = netInterface;
            }
        }
        return netInterfaceEth0;
    }
}
