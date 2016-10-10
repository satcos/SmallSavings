package model;

import constant.Constant;
import utility.Utility;
import javabean.ReportBean;
import dao.ConnectionPool;
import dao.PaymentDao;

public class PrintOperation {
	private ConnectionPool cp;
	
	public PrintOperation(ConnectionPool cp)	{
		this.cp=cp;
	}
	public ReportBean generatePaidRecords(String dop,int payno)	{
		ReportBean report=new ReportBean();
		PaymentDao db=new PaymentDao(cp);
		report = db.paymentPrint(dop, payno);
		report.setComission(Utility.calculateCommission(report.getTotalAmount()));
		report.setPayno(payno);
		report.setDate(Utility.getIndianDate(dop));
		report.setUserName(Constant.userName);
		report.setAgentNo(Constant.agentNo);
		return(report);
	}
}
