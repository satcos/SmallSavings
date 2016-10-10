package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.ConnectionPool;

public class MyServletContextListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent event)	{
		String dbDriverName;
	    String dbConnectionURL;
	    String dbUserName;
	    String dbPassword;
	    int initialConnectionCount=1;
	    
		ServletContext sc = event.getServletContext();
		dbDriverName=sc.getInitParameter("driverName");
		dbConnectionURL=sc.getInitParameter("connectionURL");
	    dbUserName=sc.getInitParameter("userName");
	    dbPassword=sc.getInitParameter("password");
	    initialConnectionCount=Integer.parseInt(sc.getInitParameter("initialConnectionCount"));
	    
		System.out.println("Starting up the Small Savings application");
		System.out.println("Driver name: " + dbDriverName+
							"\nConnection URL: "+dbConnectionURL+
							"\nUser Name: "+dbUserName+
							"\nPassword: "+dbPassword+
							"\nInitial No of connection: "+initialConnectionCount);
		ConnectionPool cp =new ConnectionPool(dbDriverName,dbConnectionURL,dbUserName,dbPassword,initialConnectionCount);
		sc.setAttribute("cp", cp);
	}
	public void contextDestroyed(ServletContextEvent event)	{

	}
}
