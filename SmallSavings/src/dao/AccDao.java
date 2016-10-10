package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabean.SearchBean;

public class AccDao {
	private ConnectionPool cp;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public AccDao(ConnectionPool cp)	{
		this.cp=cp;
	}
	
	public int newAccount(String add,int amount,String doj,int payno)
	{
		int slno=0;
		try 
		{
			con=cp.checkout();
			ps=con.prepareStatement("insert into account(accno,aslaas,name,amount,doj,payno,lpd,balance,status) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, "New");
			ps.setString(2, "New");
			ps.setString(3, add);
			ps.setInt(4, amount);
			ps.setString(5, doj);
			ps.setInt(6, payno);
			ps.setString(7, doj);
			ps.setInt(8, amount);
			ps.setInt(9, 1);
			ps.executeUpdate();
			ps=con.prepareStatement("SELECT LAST_INSERT_ID()");
			rs=ps.executeQuery();
			rs.next();
			slno=rs.getInt(1);
			ps=con.prepareStatement("insert into payment(slno,amount,balance,dop,payno,month) values(?,?,?,?,?,?)");
			ps.setInt(1, slno);
			ps.setInt(2, amount);
			ps.setInt(3, amount);
			ps.setString(4, doj);
			ps.setInt(5, payno);
			ps.setInt(6, 1);
			ps.executeUpdate();
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slno;
	}
	public ArrayList<SearchBean> searchAccount(String slno,String accno,String add,String doj)
	{
		ArrayList<SearchBean> srchResult =new ArrayList<SearchBean>();
		SearchBean result = new SearchBean();
		try 
		{
			con=cp.checkout();
			if(slno=="")
			{
				ps=con.prepareStatement("select slno,accno,name,doj,amount,balance,lpd,aslaas from account where accno like ? and name like ? and doj like ?");
			}
			else
			{
				ps=con.prepareStatement("select slno,accno,name,doj,amount,balance,lpd,aslaas from account where accno like ? and name like ? and doj like ? and slno=?");
				ps.setInt(4, Integer.parseInt(slno));
			}
			ps.setString(1, "%"+accno+"%");
			ps.setString(2, "%"+add+"%");
			ps.setString(3, "%"+doj+"%");
			rs=ps.executeQuery();
			while(rs.next())
			{
				//Storing result in bean element
				result=new SearchBean();
				result.setSlno(rs.getInt(1));
				result.setAccno(rs.getString(2));
				result.setAdd(rs.getString(3));
				result.setDoj(rs.getString(4));
				result.setAmount(rs.getInt(5));
				result.setBalance(rs.getLong(6));
				result.setLpd(rs.getString(7));
				result.setAslaas(rs.getString(8));
				srchResult.add(result);
			}
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return srchResult;
	}
	public SearchBean editAccount(int slno, int position)
	{
		SearchBean result =new SearchBean();
		try 
		{
			con=cp.checkout();
			if(position == -1){
				ps=con.prepareStatement("SELECT slno,accno,name,doj,amount,balance,lpd,aslaas FROM account WHERE slno < ? ORDER BY slno DESC LIMIT 1");
			}
			else if(position == 1)	{
				ps=con.prepareStatement("SELECT slno,accno,name,doj,amount,balance,lpd,aslaas FROM account WHERE slno > ? ORDER BY slno LIMIT 1");
			}
			else	{
				ps=con.prepareStatement("SELECT slno,accno,name,doj,amount,balance,lpd,aslaas FROM account WHERE slno=?");
			}
			ps.setInt(1,slno);
			rs=ps.executeQuery();
			cp.checkin(con);
			if(rs.next())	{
				//Storing result in bean element
				result.setSlno(rs.getInt(1));
				result.setAccno(rs.getString(2));
				result.setAdd(rs.getString(3));
				result.setDoj(rs.getString(4));
				result.setAmount(rs.getInt(5));
				result.setBalance(rs.getLong(6));
				result.setLpd(rs.getString(7));
				result.setAslaas(rs.getString(8));
			}
			else	{
				return(null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public void updateAccount(int slno,String accno,String aslaas,String add,String doj,int amount,long balance,String lpd)
	{
		try 
		{
			con=cp.checkout();
			ps=con.prepareStatement("update account set accno=?,name=?,doj=?,amount=?,balance=?,lpd=?,aslaas=? where slno=?");
			ps.setString(1, accno);
			ps.setString(2, add);
			ps.setString(3, doj);
			ps.setInt(4, amount);
			ps.setLong(5, balance);
			ps.setString(6, lpd);
			ps.setString(7, aslaas);
			ps.setInt(8,slno);
			ps.executeUpdate();
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteAccount(int slno)
	{
		try 
		{
			con=cp.checkout();
			ps=con.prepareStatement("delete from account where slno=?");
			ps.setInt(1,slno);
			ps.executeUpdate();
			cp.checkin(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
