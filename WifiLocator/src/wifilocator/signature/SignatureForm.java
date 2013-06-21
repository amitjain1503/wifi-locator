package wifilocator.signature;



/**
 * This is a class defined to store the wifi tupes 
 * of the form{TimeStamp,SSID,BSSID,LEVEL,FREQUENCY}
 * @author Amit Jain
 * @version 0
 */
public class SignatureForm {
	private String ssId;
	private String bssId;
	private int level;
	private int frequency;
	
	/**
	 * Constructor function of Signature
	 * @author Amit Jain
	 * @param ssId
	 * @param bssId
	 * @param level
	 * @param frequency
	 */
	public SignatureForm(String ssId,String bssId,int level, int frequency)
	{
		this.ssId=ssId;
		this.bssId=bssId;
		this.level=level;
		this.frequency=frequency;
	}
	
	/**
	 * Constructor function without parameters
	 * @author Amit Jain
	 */
	public SignatureForm()
	{
		ssId="N/A";
		bssId="N/A";
		level=0;
		frequency=0;
	}
	
	/**
	 * Convert Signature to string
	 * @author Amit Jain
	 * @return signature of String form
	 */
	public String toString()
	{
		StringBuilder str=new StringBuilder();
		//str.append(ssId).append(",").append(bssId).append(",").append(level).append(",").append(frequency).append("\r\n");
		str.append(bssId).append(",").append(level).append(",");
		return str.toString();
	}
	
	/**
	 * @author Amit Jain
	 * @return ssId
	 */
	public String getSSID()
	{
		return ssId;
	}
	
	/**
	 * @author Amit Jain
	 * @return bssId
	 */
	public String getBSSID()
	{
		return bssId;
	}
	
	/**
	 * @author Amit Jain
	 * @return level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * @author Amit Jain
	 * @return frequency
	 */
	public int getFrequency()
	{
		return frequency;
	}
}
