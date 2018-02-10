package pl.tatastruga.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ContactDBUtil
{

	private DataSource dataSource;

	public ContactDBUtil(DataSource theDataSource)
	{
		this.dataSource = theDataSource;
	}
	
	public List<Contact> getContacts() throws Exception
	{
		List<Contact> contacts = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			String sql = "SELECT*FROM contacts";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				String circle = myRs.getString("circle");
				
				Contact tempContact = new Contact(id, firstName, lastName, email, phoneNumber, circle);
				
				contacts.add(tempContact);
			}		
			
			return contacts;
		
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
		
	}

	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs)
	{
		try
		{
			if(myRs!=null)
				myRs.close();
			
			if(myStmt!=null)
				myStmt.close();
			
			if(myConn!=null)
				myConn.close();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
