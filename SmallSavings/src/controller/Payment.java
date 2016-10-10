package controller;

import java.io.IOException;
import java.util.ArrayList;

import javabean.PayDateBean;
import javabean.PayDispBean;
import javabean.ReportBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.Utility;

import dao.ConnectionPool;
import dao.PaymentDao;
import model.PaymentOperation;
import model.PrintOperation;

@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private ConnectionPool cp;
	private static final long serialVersionUID = 1L;
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		this.cp=(ConnectionPool)getServletContext().getAttribute("cp");
		String action=request.getParameter("action");
		PaymentDao db=new PaymentDao(cp);
		if(action.equalsIgnoreCase("printDate"))	{
			int payYear[];
			payYear = db.getPayYear();
			session.setAttribute("level", "Year");
			session.setAttribute("payYear", payYear);
			response.sendRedirect("paydate.jsp");
		}
		else if(action.equalsIgnoreCase("printMonth"))	{
			boolean payMonth[] = new boolean[12];
			int year = Integer.parseInt(request.getParameter("year"));
			payMonth = db.getPayMonth(year);
			session.setAttribute("level", "Month");
			session.setAttribute("year", year);
			session.setAttribute("payMonth", payMonth);
			response.sendRedirect("paydate.jsp");
		}
		else if(action.equalsIgnoreCase("printDay"))	{
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			ArrayList<PayDateBean> payDay=new ArrayList<PayDateBean>();
			payDay=db.getPayDay(year, month);
			session.setAttribute("level", "Day");
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("payDay", payDay);
			response.sendRedirect("paydate.jsp");
		}
		else if(action.equalsIgnoreCase("print"))	{
			ReportBean report=new ReportBean();
			PrintOperation print=new PrintOperation(cp);
			
			String dop=request.getParameter("dop");
			int payno=Integer.parseInt(request.getParameter("payno"));
			
			report=print.generatePaidRecords(dop, payno);
			
			session.setAttribute("report", report);
			response.sendRedirect("print.jsp");
		}
		else if(action.equalsIgnoreCase("deleteSingle"))	{
			int slno=Integer.parseInt(request.getParameter("slno"));
			String dop=request.getParameter("dop");
			int payno=Integer.parseInt(request.getParameter("payno"));
			
			PaymentOperation pay=new PaymentOperation(cp);
			String msg="";
			
			dop = Utility.getMachineDate(dop);
			msg=pay.deleteSingle(dop, payno, slno);
			
			if(msg.equalsIgnoreCase(""))	{
				msg="Payment Successfully Deleted";
				ReportBean report=new ReportBean();
				PrintOperation print=new PrintOperation(cp);
				report=print.generatePaidRecords(dop, payno);
				session.setAttribute("report", report);
			}
			
			session.setAttribute("msg", msg);
			response.sendRedirect("print.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		this.cp=(ConnectionPool)getServletContext().getAttribute("cp");
		String action=request.getParameter("action");
		
		PaymentOperation pay=new PaymentOperation(cp);
		
		if(action.equalsIgnoreCase("paymentstep1"))	{
			//Getting Value from Form
			String dop=request.getParameter("dop");
			int payno=Integer.parseInt(request.getParameter("payno"));
			
			int month=Integer.parseInt(dop.substring(5, 7));
			int year=Integer.parseInt(dop.substring(0, 4));
			
			//Creating Array list to store account to be paid
			ArrayList<PayDispBean> payDisp=new ArrayList<PayDispBean>();
			
			//Getting account
			payDisp=pay.paymentStep1(month, year);
			
			//Setting variables to session
			session.setAttribute("dop", dop);
			session.setAttribute("payno", payno);
			session.setAttribute("payDisp", payDisp);
			response.sendRedirect("payment.jsp");
		}
		else if(action.equalsIgnoreCase("paymentstep2"))	{
			String paySlno[]=request.getParameterValues("pay");
			if(paySlno!=null)	{
				ReportBean report=new ReportBean();
				PrintOperation print=new PrintOperation(cp);
				
				String dop=(String) session.getAttribute("dop");
				int payno=(Integer) session.getAttribute("payno");
				@SuppressWarnings("unchecked")
				ArrayList<PayDispBean> payDisp =(ArrayList<PayDispBean>)session.getAttribute("payDisp");
				
				pay.paymentStep2(paySlno, dop, payno, payDisp);
				report = print.generatePaidRecords(dop, payno);
				
				session.removeAttribute("payno");
				session.removeAttribute("payDisp");
				session.setAttribute("report", report);
				response.sendRedirect("print.jsp");
			}
			else
				response.sendRedirect("payment.jsp");

		}
		
	}

}
