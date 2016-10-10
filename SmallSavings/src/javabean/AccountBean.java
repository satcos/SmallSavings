package javabean;

public class AccountBean
{
	int slno;
	String accno;
	String aslaas;
	String add;
	String doj;
	int amount;
	long balance;
	String lpd;
	//Flags
	boolean addsuccess;
	//Getters and Setters
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
	public String getAslaas() {
		return aslaas;
	}
	public void setAslaas(String aslaas) {
		this.aslaas = aslaas;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
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
	public boolean isAddsuccess() {
		return addsuccess;
	}
	public void setAddsuccess(boolean addsuccess) {
		this.addsuccess = addsuccess;
	}
	
}
