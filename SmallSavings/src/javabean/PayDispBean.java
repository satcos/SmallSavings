package javabean;

public class PayDispBean {
	int slno;
	String accno;
	String add;
	int amount;
	int monthPaying;
	int amountPaying;
	int finePaying;
	long balance;
	long postBalance;
	String lpd;
	String remarks;
	int payno;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getMonthPaying() {
		return monthPaying;
	}
	public void setMonthPaying(int monthPaying) {
		this.monthPaying = monthPaying;
	}
	public int getAmountPaying() {
		return amountPaying;
	}
	public void setAmountPaying(int amountPaying) {
		this.amountPaying = amountPaying;
	}
	public long getPostBalance() {
		return postBalance;
	}
	public int getFinePaying() {
		return finePaying;
	}
	public void setFinePaying(int finePaying) {
		this.finePaying = finePaying;
	}
	public void setPostBalance(long postBalance) {
		this.postBalance = postBalance;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getLpd() {
		return lpd;
	}
	public void setLpd(String lpd) {
		this.lpd = lpd;
	}
	public int getPayno() {
		return payno;
	}
	public void setPayno(int payno) {
		this.payno = payno;
	}
	
}
