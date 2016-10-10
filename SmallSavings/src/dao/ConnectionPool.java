package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool implements Runnable	{ 
    //db parameters 
    private String dbDriverName;
    private String dbConnectionURL;
    private String dbUserName;
    private String dbPassword;
    private int initialConnectionCount;
    
    // A list of available connections for use. 
    @SuppressWarnings("rawtypes")
	private Vector availableConnections = new Vector(); 
    
    // A list of connections being used currently. 
    @SuppressWarnings("rawtypes")
	private Vector usedConnections = new Vector(); 
    
    // The cleanup thread     
    private Thread cleanupThread = null; 

    //Constructor 
    @SuppressWarnings("unchecked")
	public ConnectionPool(String dbDriverName,String dbConnectionURL,String dbUserName,String dbPassword, int initialConnectionCount) 
    {
    	this.dbDriverName=dbDriverName;
    	this.dbConnectionURL=dbConnectionURL;
    	this.dbUserName=dbUserName;
    	this.dbPassword=dbPassword;
    	this.initialConnectionCount=initialConnectionCount;
    	
    	try
    	{
	    	Class.forName(this.dbDriverName);
	        for(int cnt=0; cnt<this.initialConnectionCount; cnt++) 
	        { 
	            // Add a new connection to the available list. 
	            availableConnections.addElement(getConnection()); 
	        } 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
        // Create the cleanup thread 
        cleanupThread = new Thread(this); 
        cleanupThread.start(); 
    }     
     
    private Connection getConnection() throws SQLException 
    { 
        return DriverManager.getConnection(dbConnectionURL, dbUserName, dbPassword); 
    } 
     
    @SuppressWarnings("unchecked")
	public synchronized Connection checkout()
    { 
        Connection newConnxn = null; 
        if(availableConnections.size() == 0) 
        { 
            // Out of connections. Create one more. 
             try {
				newConnxn = getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            // Add this connection to the "Used" list. 
             usedConnections.addElement(newConnxn); 
            // We dont have to do anything else since this is 
            // a new connection.
             System.out.println("New Connection Created "+availableCount());
        } 
        else 
        { 
            // Connections exist ! 
            // Get a connection object 
            newConnxn = (Connection)availableConnections.lastElement(); 
            // Remove it from the available list. 
            availableConnections.removeElement(newConnxn); 
            // Add it to the used list. 
            usedConnections.addElement(newConnxn);
        }         
        // Either way, we should have a connection object now. 
        return newConnxn; 
    } 
     

    @SuppressWarnings("unchecked")
	public synchronized void checkin(Connection c) 
    { 
        if(c != null) 
        { 
            // Remove from used list. 
            usedConnections.removeElement(c); 
            // Add to the available list 
            availableConnections.addElement(c);         
        } 
    }             
     
    public int availableCount() 
    { 
        return availableConnections.size(); 
    } 
     
    public void run() 
    { 
        try 
        { 
            while(true) 
            { 
//            	System.out.println("CLEANUP : Available Connections : " + availableCount());
                synchronized(this) 
                { 
                    while(availableConnections.size() > initialConnectionCount) 
                    { 
                        // Clean up extra available connections. 
                        Connection c = (Connection)availableConnections.lastElement(); 
                        availableConnections.removeElement(c); 
                         
                        // Close the connection to the database. 
                        c.close(); 
                    } 
                    // Clean up is done 
                } 
                // Now sleep for 1 minute 
                Thread.sleep(60000); 
            }     
        } 
        catch(SQLException sqle) 
        { 
            sqle.printStackTrace(); 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
} 


