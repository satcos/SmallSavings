package javabean;

import java.util.ArrayList;

public class ReportBean {
	ArrayList<PayPrintBean> table;
//	PayPrintBean table;
	String date;
	int payno;
	int noOfAccount;
	int comission;
	long totalAmount;
	float fine;
	String userName;
	String agentNo;
	
	public ArrayList<PayPrintBean> getTable() {
		return table;
	}
	public void setTable(ArrayList<PayPrintBean> table) {
		this.table = table;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNoOfAccount() {
		return noOfAccount;
	}
	public void setNoOfAccount(int noOfAccount) {
		this.noOfAccount = noOfAccount;
	}
	public int getComission() {
		return comission;
	}
	public void setComission(int comission) {
		this.comission = comission;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public float getFine() {
		return fine;
	}
	public void setFine(float fine) {
		this.fine = fine;
	}
	public int getPayno() {
		return payno;
	}
	public void setPayno(int payno) {
		this.payno = payno;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAgentNo() {
		return agentNo;
	}
	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}
}
