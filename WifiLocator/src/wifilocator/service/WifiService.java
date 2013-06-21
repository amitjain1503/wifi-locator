package wifilocator.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

/**
 * Wifi service provider; manage Wifi operations.
 * @author Amit Jain
 * @version beta
 */
public class WifiService {
	private WifiManager m_wifiManager;
	private WifiInfo m_wifiInfo;
	private WifiLock m_wifiLock;
	private List<WifiConfiguration> m_wifiConfigList;
	private List<ScanResult> m_wifiList;
	private long m_timeStamp;
	private List<ScanResult> m_wholeList;
	
	/**
	 * constructor function of WifiHelper
	 * @author Amit Jain
	 * @param interface to the application environment
	 */
	public WifiService(Context context)
	{
		m_wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		m_wifiList=new ArrayList<ScanResult>();
		//m_wifiInfo=m_wifiManager.getConnectionInfo();	
	}
	
	/**
	 * open the WIFI
	 * @author Amit Jain
	 */
	public void openWifi()
	{
		if(!m_wifiManager.isWifiEnabled())
		{
			m_wifiManager.setWifiEnabled(true);
		}
	}
	
	/**
	 * close the WIFI
	 * @author Amit Jain
	 */
	public void closeWifi()
	{
		if(m_wifiManager.isWifiEnabled())
		{
			m_wifiManager.setWifiEnabled(false);
		}
	}
	
	/**
	 * get the WIFI state
	 * @author Amit Jain
	 * @return the state of Wifi
	 */
	public int getState()
	{
		return m_wifiManager.getWifiState();
	}
	
	/**
	 * create the WIFI Lock
	 * @author Amit Jain
	 */
	public void createWifiLock()
	{
		m_wifiLock=m_wifiManager.createWifiLock("Wifi Lock");
	}
	
	/**
	 * acquire the WIFI Lock
	 * @author Amit Jain
	 */
	public void acquireWifiLock()
	{
		m_wifiLock.acquire();
	}
	
	/**
	 * release the WIFI Lock
	 * @author Amit Jain
	 */
	public void releaseWifiLock()
	{
		if(m_wifiLock.isHeld())
			m_wifiLock.release();
	}
	
	/**
	 * get the WIFI configurations
	 * @author Amit Jain
	 * @return Configuration List of Wifi Networks
	 */
	public List<WifiConfiguration> getWifiConfiguration()
	{
		return m_wifiConfigList;
	}
	
	/**
	 * start WIFI scan
	 * @author Amit Jain
	 * @return True if the operation succeeded, false otherwise
	 */
	public boolean startScan()
	{
		if(m_wifiManager.startScan())
		{
			m_timeStamp=System.currentTimeMillis();
			m_wholeList=m_wifiManager.getScanResults();
			m_wifiList.clear();
			if(m_wholeList!=null) 
			{	for(int i=0;i<m_wholeList.size();i++)
				{
					if(m_wholeList.get(i).SSID.equals("eduroam"))
					m_wifiList.add(m_wholeList.get(i));
				}
			}
			m_wifiConfigList=m_wifiManager.getConfiguredNetworks();
			return true;
		}
		else return false;
	}
	
	/**
	 * get the results of the latest access point scan
	 * @author Amit Jain
	 * @return List of access points.
	 */
	public List<ScanResult> getWifiList()
	{
		return m_wifiList;
	}
	
	/**
	 * get WIFI List in String
	 * @author Amit Jain
	 * @return String Form: the list of access points.
	 */
	public StringBuilder getWifiListInString()
	{
		StringBuilder result=new StringBuilder();
        for(int i=0;i<m_wifiList.size();i++)
		{
			result.append(m_wifiList.get(i).toString()).append("\n");
		}
		return result;
	}
	
	/**
	 * get the state of any WIFI connection that is active or in the process of being set up
	 * @author Amit Jain
	 * @return dynamic information about the current Wi-Fi connection
	 */
	public WifiInfo getWifiInfo()
	{
		m_wifiInfo=m_wifiManager.getConnectionInfo();
		return m_wifiInfo;
	}
	
	/**
	 * get the WIFI INFO in String
	 * @author Amit Jain
	 * @return String Form: active WIFI connection information
	 */
	public StringBuilder getWifiInfoInString()
	{
		m_wifiInfo=m_wifiManager.getConnectionInfo();
		StringBuilder wifiInfo=new StringBuilder();
		if(m_wifiInfo!=null)
			wifiInfo.append(m_wifiInfo.toString());
		else wifiInfo.append("NULL");
		return wifiInfo;
	}
	
	/**
	 * add and connect to a new network described by the configuration
	 * @author Amit Jain
	 * @param configuration of a Wifi access point
	 */
	public void connectNewNetWork(WifiConfiguration config){  
        int netId=m_wifiManager.addNetwork(config);  
        m_wifiManager.enableNetwork(netId, true);  
    }
	
	/**
	 * connect to an already configured network
	 * @author Amit Jain
	 * @param index of this configured network in the configuration list
	 */
	public void connectConfiguration(int index){  
        if(index>m_wifiConfigList.size()){  
            return ;  
        }  
        m_wifiManager.enableNetwork(m_wifiConfigList.get(index).networkId, true);  
    }  
	
	/**
	 * disconnect a network with a specified ID
	 * @author Amit Jain
	 * @param Network ID
	 */
	public boolean disconnectNetWork(int netId)
	{
		 boolean r1=m_wifiManager.disableNetwork(netId);  
	     boolean r2=m_wifiManager.disconnect(); 
	     return r1&r2;
	}
	
	/**
	 * @author Amit Jain
	 * @return timeStamp of the scanning event
	 */
	public long getTimeStamp()
	{
		return m_timeStamp;
	}

}

