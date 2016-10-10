package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javabean.SearchBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccDao;
import dao.ConnectionPool;


@WebServlet("/SearchAccount")
public class SearchAccount extends HttpServlet {
	private ConnectionPool cp;
	private static final long serialVersionUID = 1L;
	public SearchAccount() {
		super();
	}
	public String checkForNull(String input)
	{
		if(input==null)
			return("");
		else
			return input;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		this.cp=(ConnectionPool)getServletContext().getAttribute("cp");
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("edit"))	{
			//Getting Data from form
			int slno=Integer.parseInt(request.getParameter("slno"));
			String position=checkForNull(request.getParameter("position"));
			
			//Variable Deceleration
			SearchBean editResult=new SearchBean();

			//Creating DAO object
			AccDao db=new AccDao(cp);

			//Getting account details
			if(position.equals(""))
				editResult=db.editAccount(slno, 0);
			else if(position.equals("Next"))
				editResult=db.editAccount(slno, 1);
			else if(position.equals("Previous"))
				editResult=db.editAccount(slno, -1);
			else
				;
			
			if(editResult == null)	{
				if(position.equals("Next"))	{
					session.setAttribute("msg", "Last record reached.");
				}
				else if(position.equals("Previous"))	{
					session.setAttribute("msg", "First record reached.");
				}
				else
					;
			}
			else	{
				session.setAttribute("editResult", editResult);
			}

			//Redirecting to edit page
			response.sendRedirect("edit.jsp");

		}
		else if(action.equalsIgnoreCase("delete"))	{
			int slno=Integer.parseInt(request.getParameter("slno"));
			//Creating DAO object
			AccDao db=new AccDao(cp);
			//Deleting account
			db.deleteAccount(slno);
			session.setAttribute("deleteAccount", "deleted");
			
			@SuppressWarnings("unchecked")
			ArrayList<SearchBean> srchResult =(ArrayList<SearchBean>)session.getAttribute("srchResult");
			SearchBean result=null;
			Iterator<SearchBean> it = srchResult.iterator();
			while(it.hasNext())
			{
				result=it.next();
				if(slno==result.getSlno())
				{
					it.remove();
					break;
				}
			}
			session.setAttribute("srchResult", srchResult);
			//Redirecting to search page
			response.sendRedirect("search.jsp");
		}
		else
			System.out.println("From others.");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		this.cp=(ConnectionPool)getServletContext().getAttribute("cp");
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("search"))
		{
			//Getting Data from form
			String slno=checkForNull(request.getParameter("slno"));
			String accno=checkForNull(request.getParameter("accountno"));
			String add=checkForNull(request.getParameter("address"));
			String doj=checkForNull(request.getParameter("doj"));

			//Variable Deceleration
			ArrayList<SearchBean> srchResult =new ArrayList<SearchBean>();

			//Creating DAO object
			AccDao db=new AccDao(cp);

			//Searching account details
			srchResult=db.searchAccount(slno, accno, add, doj);
			session.setAttribute("numberOfResult", srchResult.size());
			session.setAttribute("srchResult", srchResult);

			//Redirecting to search page
			response.sendRedirect("search.jsp");
		}
		else if(action.equalsIgnoreCase("update"))
		{
			int slno=Integer.parseInt(request.getParameter("slnoh"));
			String accno=request.getParameter("accountno");
			String aslaas=request.getParameter("aslaas");
			String add=request.getParameter("address");
			int amount=Integer.parseInt(request.getParameter("amount"));
			long balance=Integer.parseInt(request.getParameter("balance"));
			String doj=request.getParameter("doj");
			String lpd=request.getParameter("lpd");
			SearchBean editResult=new SearchBean();
			AccDao db=new AccDao(cp);
			db.updateAccount(slno, accno, aslaas, add, doj, amount, balance, lpd);
			editResult=db.editAccount(slno, 0);
			session.setAttribute("editResult", editResult);
			session.setAttribute("success", "true");
			response.sendRedirect("edit.jsp");
		}
		else if(action.equalsIgnoreCase("newacc"))	{
			//Getting parameter 
			String add=request.getParameter("address");
			int amount=Integer.parseInt(request.getParameter("amount"));
			String doj=request.getParameter("doj");
			int payno=Integer.parseInt(request.getParameter("payno"));

			//Variable Deceleration
			int slno=0;
			//connecting to Db
			AccDao db=new AccDao(cp);
			//Inserting account details
			slno=db.newAccount(add, amount, doj, payno);
			//setting SLNO in session
			session.setAttribute("slno", slno);
			//Redirecting to next page
			response.sendRedirect("newacc.jsp");
		}
		else
			;
	}

}
