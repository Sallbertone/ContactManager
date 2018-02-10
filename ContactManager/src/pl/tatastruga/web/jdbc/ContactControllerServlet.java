package pl.tatastruga.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ContactControllerServlet")
public class ContactControllerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	//request.setCharacterEncoding("UTF-8");
	//response.setCharacterEncoding("UTF-8");
	
	ContactDBUtil contactDBUtil;

	@Resource(name = "jdbc/creall_CM")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException
	{
		super.init();

		try
		{
			contactDBUtil = new ContactDBUtil(dataSource);
		} 
		catch (Exception exc)
		{
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		try
		{
			listContact(request, response);
		} 
		catch (Exception e)
		{
			throw new ServletException(e);
		}

	}

	private void listContact(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<Contact> contactList = contactDBUtil.getContacts();

		request.setAttribute("CONTACT_LIST", contactList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts-list.jsp");

		dispatcher.forward(request, response);
	}

}
