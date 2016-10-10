package utility;

import constant.Constant;

public class Utility {
	public static int calculateCommission(long totalAmount)	{
		return((int)totalAmount * Constant.commissionPercentage /100);
	}
	public static String getIndianDate(String machineDate)	{
		return(machineDate.substring(8, 10)+"-"+machineDate.substring(5, 7)+"-"+machineDate.substring(0, 4));
	}
	public static String getMachineDate(String indianDate)	{
		return(indianDate.substring(6, 10)+"-"+indianDate.substring(3, 5)+"-"+indianDate.substring(0,2));
	}
}
