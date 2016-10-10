package model;

import java.util.ArrayList;

import constant.Constant;

import javabean.PayDispBean;
import dao.ConnectionPool;
import dao.PaymentDao;

public class PaymentOperation {
	private ConnectionPool cp;
	
	public PaymentOperation(ConnectionPool cp)	{
		this.cp=cp;
	}
	
	public ArrayList<PayDispBean> paymentStep1(int month, int year)	{
		int noOfAcc=0;
		int i;
		int lpdMonth, lpdYear;
		int fine=0;
		
		//Creating Array list to store account to be paid
		ArrayList<PayDispBean> payDisp=new ArrayList<PayDispBean>();
		
		//creating object to Payment DAO
		PaymentDao db=new PaymentDao(cp);
		
		//Getting account
		payDisp=db.paymentStep1(month, year);
		noOfAcc=payDisp.size();
		for(i=0; i<noOfAcc; i++)	{
			lpdMonth=Integer.parseInt(payDisp.get(i).getLpd().substring(5, 7));
			lpdYear=Integer.parseInt(payDisp.get(i).getLpd().substring(0, 4));
			payDisp.get(i).setMonthPaying(((12 - lpdMonth) + 12 * (year - lpdYear - 1) + month));
			payDisp.get(i).setAmountPaying(payDisp.get(i).getAmount() * payDisp.get(i).getMonthPaying());
			payDisp.get(i).setPostBalance(payDisp.get(i).getBalance() + payDisp.get(i).getAmountPaying());
//			fine=Amount * 2/100 * Month * (Month -1)/2
//			2 Rs per 100 Rs per month
			fine=(int)payDisp.get(i).getAmount() * Constant.finePercentage / 100 * payDisp.get(i).getMonthPaying() * (payDisp.get(i).getMonthPaying() - 1) / 2;
			payDisp.get(i).setFinePaying(fine);
		}
		return(payDisp);
	}
	public void paymentStep2(String paySlno[], String dop, int payno, ArrayList<PayDispBean> payDisp)	{
		int noOfAcc=0;
		int i,j;
		int lastUsed;
		int startMonth=0;
		int endMonth=0;
		String months[]={"","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","Jan","Feb","Mar","Apr","May","Jun"};
		String remarks=null;
		int slno[]=new int[paySlno.length+1];
		PaymentDao db=new PaymentDao(cp);
		
		//converting String Slno to Integer
		slno[0]=0;
		for(i=1;i<paySlno.length+1;i++)
			slno[i]=Integer.parseInt(paySlno[i-1]);
		lastUsed=slno.length-1;
		
		//Removing Accounts that are not paid
		noOfAcc=payDisp.size();
		for(i=noOfAcc-1;i>=0;i--)	{
			if(payDisp.get(i).getSlno()==slno[lastUsed])	{
				lastUsed--;
				startMonth=Integer.parseInt(payDisp.get(i).getLpd().substring(5, 7));
				endMonth=startMonth+payDisp.get(i).getMonthPaying();
				remarks=months[startMonth]+",";
				for(j=startMonth+1;j<endMonth;j++)
					remarks=remarks+months[j]+",";
				remarks=remarks.substring(0, remarks.length()-1);
				payDisp.get(i).setRemarks(remarks);
			}
			else	{
				payDisp.remove(i);
			}
		}
		db.paymentStep2(dop, payno, payDisp);
	}
	public String deleteSingle(String dop,int payno,int slno)	{
		PaymentDao db=new PaymentDao(cp);
		String msg=db.deleteSingle(dop, payno, slno);
		return msg;
	}
	

}
