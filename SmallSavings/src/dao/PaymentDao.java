package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import constant.Constant;

import javabean.PayDateBean;
import javabean.PayDispBean;
import javabean.PayPrintBean;
import javabean.ReportBean;

public class PaymentDao {
	private ConnectionPool cp;
	private Connection con;
	private PreparedStatement ps;
	private PreparedStatement psAcc;
	private ResultSet rs;
	
	public PaymentDao(ConnectionPool cp)	{
		this.cp=cp;
	}
	
	public ArrayList<PayDispBean> paymentStep1(int month,int year)	{
		ArrayList<PayDispBean> payResult =new ArrayList<PayDispBean>();
		PayDispBean result = new PayDispBean();
		try	{
			con=cp.checkout();
			ps=con.prepareStatement("select slno,accno,name,amount,balance,lpd,payno from account where ((?-year(lpd))*12+?-month(lpd))>0 and ((?-year(lpd))*12+?-month(lpd))<= ? and status=1");
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.setInt(3, year);
			ps.setInt(4, month);
			ps.setInt(5, Constant.accumulationMonths);
			rs=ps.executeQuery();
			while(rs.next())	{
				//Storing result in bean element
				result=new PayDispBean();
				result.setSlno(rs.getInt(1));
				result.setAccno(rs.getString(2));
				result.setAdd(rs.getString(3));
				result.setAmount(rs.getInt(4));
				result.setBalance(rs.getLong(5));
				result.setLpd(rs.getString(6));
				result.setPayno(rs.getInt(7));
				payResult.add(result);
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payResult;
	}
	public void paymentStep2(String dop,int payno,ArrayList<PayDispBean> payDisp)	{
		int noOfAcc=payDisp.size();
		int i;
		try	{
			con=cp.checkout();
			ps=con.prepareStatement("insert into payment(slno,amount,balance,dop,payno,month,fine,remarks,prevdop,prevpayno) values(?,?,?,?,?,?,?,?,?,?)");
			psAcc=con.prepareStatement("update account set balance=?,lpd=?,payno=? where slno=?");
			psAcc.setString(2, dop);
			psAcc.setInt(3, payno);
			for(i=0;i<noOfAcc;i++)	{
				ps.setInt(1, payDisp.get(i).getSlno());
				ps.setInt(2, payDisp.get(i).getAmountPaying());
				ps.setLong(3, payDisp.get(i).getPostBalance());
				ps.setString(4, dop);
				ps.setInt(5, payno);
				ps.setInt(6, payDisp.get(i).getMonthPaying());
				ps.setFloat(7, payDisp.get(i).getFinePaying());
				ps.setString(8, payDisp.get(i).getRemarks());
				ps.setString(9, payDisp.get(i).getLpd());
				ps.setInt(10, payDisp.get(i).getPayno());
				
				psAcc.setLong(1, payDisp.get(i).getPostBalance());				
				psAcc.setInt(4, payDisp.get(i).getSlno());
				ps.executeUpdate();
				psAcc.executeUpdate();
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ReportBean paymentPrint(String dop,int payno)
	{
		ReportBean rb=new ReportBean();
		ArrayList<PayPrintBean> payResult =new ArrayList<PayPrintBean>();
		
		PayPrintBean result = new PayPrintBean();
		int month=0;
		try 
		{
			con=cp.checkout();
			//Extracting No of Account and sum of Ammount and Fine
			ps=con.prepareStatement("SELECT COUNT(*), SUM(payment.amount), SUM(payment.fine) FROM account, payment WHERE (payment.dop=? AND payment.payno=? AND payment.slno=account.slno)");
			ps.setString(1, dop);
			ps.setInt(2, payno);
			rs=ps.executeQuery();
			rs.next();
			rb.setNoOfAccount(rs.getInt(1));
			rb.setTotalAmount(rs.getLong(2));
			rb.setFine(rs.getInt(3));
			rb.setPayno(payno);
			rb.setDate(dop);
			
			//Extracting Account payment details
			ps=con.prepareStatement("SELECT account.accno, account.name, account.aslaas, payment.amount, payment.balance, payment.month, payment.fine, payment.remarks, account.slno FROM account, payment WHERE (payment.dop=? AND payment.payno=? AND payment.slno=account.slno) ORDER BY account.accno ASC, account.slno ASC");
			ps.setString(1, dop);
			ps.setInt(2, payno);
			rs=ps.executeQuery();
			while(rs.next())	{
				//Storing result in bean element
				result=new PayPrintBean();
				result.setAccno(rs.getString(1));
				result.setAdd(rs.getString(2));
				result.setAslaas(rs.getString(3));
				result.setAmount(rs.getInt(4));
				result.setBalance(rs.getLong(5));
				result.setFine(rs.getInt(7));
				result.setRemarks(rs.getString(8));
				result.setSlno(rs.getInt(9));
				month=month+1;
				result.setMonth(month);
				payResult.add(result);
			}
			rb.setTable(payResult);
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rb;
	}
	
	public int[] getPayYear()
	{
		int payYear[] = null;
		int count=0;
		try 
		{
			con=cp.checkout();
			
//			Get number of different years
			ps=con.prepareStatement("SELECT COUNT(DISTINCT YEAR(dop))FROM payment;");
			rs=ps.executeQuery();
			rs.next();
			payYear=new int[rs.getInt(1)];
			
//			Extract years
			ps=con.prepareStatement("SELECT DISTINCT YEAR(dop) FROM payment ORDER BY dop ASC;");
			rs=ps.executeQuery();
			while(rs.next())	{
				payYear[count] = rs.getInt(1);
				count++;
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return(payYear);
	}
	
	public boolean[] getPayMonth(int year)
	{
		boolean payMonth[] = new boolean[12];
		try 
		{
			con=cp.checkout();
//			Extract months
			ps=con.prepareStatement("SELECT DISTINCT MONTH(dop) FROM payment WHERE YEAR(dop)=? ORDER BY dop ASC;");
			ps.setInt(1, year);
			rs=ps.executeQuery();
			while(rs.next())	{
				payMonth[rs.getInt(1) - 1]=true;
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return(payMonth);
	}
	
	public ArrayList<PayDateBean> getPayDay(int year, int month)
	{
		ArrayList<PayDateBean> payDay=new ArrayList<PayDateBean>();
		PayDateBean result=new PayDateBean();
		try 
		{
			con=cp.checkout();
			ps=con.prepareStatement("SELECT DISTINCT dop, payno FROM payment WHERE YEAR(dop)=? and MONTH(dop)=? ORDER BY dop asc, payno asc;");
			ps.setInt(1, year);
			ps.setInt(2, month);
			rs=ps.executeQuery();
			while(rs.next())	{
				//Storing result in bean element
				result=new PayDateBean();
				result.setDop(rs.getString(1));
				result.setPayNo(rs.getInt(2));
				payDay.add(result);
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(payDay);
	}
	
	public String deleteSingle(String dop,int payno,int slno)	{
		int amount=0;
		long balance=0;
		String prevdop="";
		int prevpayno=0;
		String lpd;
		int lpayno;
		String msg="";
		try	{
			con=cp.checkout();
			ps=con.prepareStatement("select lpd,payno from account where slno=?");
			ps.setInt(1,slno);
			rs=ps.executeQuery();
			rs.next();
			lpd=rs.getString(1);
			lpayno=rs.getInt(2);
			if(lpd.equalsIgnoreCase(dop)&&lpayno==payno)	{
				ps=con.prepareStatement("select amount,balance,prevdop,prevpayno from payment where (slno=? and dop=? and payno=?)");
				ps.setInt(1,slno);
				ps.setString(2, dop);
				ps.setInt(3,payno);
				rs=ps.executeQuery();
				while(rs.next())	{
					//Storing result in bean element
					amount=rs.getInt(1);
					balance=rs.getLong(2);
					prevdop=rs.getString(3);
					prevpayno=rs.getInt(4);
				}
//				update account set accno='523007',aslaas='007' where slno=1;				
				ps=con.prepareStatement("update account set balance=?,lpd=?,payno=? where slno=?");
				ps.setLong(1, (balance-amount));
				ps.setString(2, prevdop);
				ps.setInt(3, prevpayno);
				ps.setInt(4, slno);
				ps.executeUpdate();
				
				ps=con.prepareStatement("DELETE FROM payment WHERE (slno=? and dop=? and payno=?)");
				ps.setInt(1, slno);
				ps.setString(2, dop);
				ps.setInt(3, payno);
				ps.executeUpdate();
			}
			else
				msg="Only latest payment can be deleted. Please first delete payment made on "+lpd+"::"+lpayno;
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(msg);
	}
}
