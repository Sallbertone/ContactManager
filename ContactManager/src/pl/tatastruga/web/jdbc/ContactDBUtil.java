package pl.tatastruga.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void addContact(Contact theContact) throws SQLException 
	{
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		
		
		try
		{
			myConn = dataSource.getConnection();

		
			String sql = "INSERT INTO contacts (first_name, last_name, email, phone_number, circle) VALUES (?, ?, ?, ?, ?)";
					
			prepStmt = myConn.prepareStatement(sql);
			
			prepStmt.setString(1, theContact.getFirstName());
			prepStmt.setString(2, theContact.getLastName());
			prepStmt.setString(3, theContact.getEmail());
			prepStmt.setString(4, theContact.getPhoneNumber());
			prepStmt.setString(5, theContact.getCircle());
			
			prepStmt.execute();
		
		} 
		finally 
		{
			close(myConn, prepStmt, null);
		}
		
	}

	public Contact getContactById(String theID) throws Exception
	{
		Contact editedContact = null;
		
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		ResultSet myRs = null;
		
		
		try
		{
			int id = Integer.parseInt(theID);
			
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * FROM contacts WHERE id=?";
			
			prepStmt = myConn.prepareStatement(sql);
			
			prepStmt.setInt(1, id);
			
			myRs = prepStmt.executeQuery();
			
			if(myRs.next())
			{
				
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				String circle = myRs.getString("circle");
						
				editedContact = new Contact(id, firstName, lastName, email, phoneNumber, circle);
			}
			else
				throw new Exception("Could not find Contact ID: " + id);
			
			return editedContact;
		}
		finally
		{
			close(myConn, prepStmt, myRs);
		}
	}

	public void updateContact(Contact theContact) throws SQLException 
	{
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		
		try
		{
			myConn = dataSource.getConnection();
			String sql = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone_number = ?, circle = ? WHERE id = ?";
			prepStmt = myConn.prepareStatement(sql);
			
			prepStmt.setString(1, theContact.getFirstName());
			prepStmt.setString(2, theContact.getLastName());
			prepStmt.setString(3, theContact.getEmail());
			prepStmt.setString(4, theContact.getPhoneNumber());
			prepStmt.setString(5, theContact.getCircle());
			prepStmt.setInt(6, theContact.getId());
						
			prepStmt.execute();
		} 
		
		
		finally
		{
			close(myConn, prepStmt, null);
		}
		
	}

	public void deleteContact(int id) throws Exception
	{
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		
		try
		{
			myConn = dataSource.getConnection();
			String sql = "DELETE FROM contacts WHERE id = ?";
			prepStmt = myConn.prepareStatement(sql);
			prepStmt.setInt(1,id);
			prepStmt.execute();
		} 
		finally
		{
			close(myConn, prepStmt, null);
		}
		
	}

	public List<Contact> sortByCircle(String circle) throws Exception
	{
		List<Contact> sortedList = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		ResultSet myRs = null;
		
		try
		{
			myConn = dataSource.getConnection();
			String sql = "Select * FROM contacts WHERE circle = ?";
			prepStmt = myConn.prepareStatement(sql);
			prepStmt.setString(1, circle);
			
			myRs = prepStmt.executeQuery();
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				circle = myRs.getString("circle");
				
				Contact tempContact = new Contact(id, firstName, lastName, email, phoneNumber, circle);
				
				sortedList.add(tempContact);
			}
		} 
		
		finally
		{
			close(myConn, prepStmt, myRs);
		}
		
		return sortedList;
	}

	public List<Contact> searchContact(String searchedInput) throws SQLException
	{
		List<Contact> foundList = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement prepStmt = null;
		ResultSet myRs = null;
		
		try
		{
			
			myConn = dataSource.getConnection();
			String sql = "SELECT DISTINCT * FROM contacts WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR phone_number LIKE ?";
			prepStmt = myConn.prepareStatement(sql);
			prepStmt.setString(1, searchedInput);
			prepStmt.setString(2, searchedInput);
			prepStmt.setString(3, searchedInput);
			prepStmt.setString(4, searchedInput);
			
			myRs = prepStmt.executeQuery();
			
			while (myRs.next())
			{
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				String circle = myRs.getString("circle");
				
				Contact tempContact = new Contact(id, firstName, lastName, email, phoneNumber, circle);
				
				foundList.add(tempContact);
			}
			
			
		} 
		finally
		{
			close(myConn, prepStmt, myRs);
		}

		
		return foundList;
	}


	
	
}
