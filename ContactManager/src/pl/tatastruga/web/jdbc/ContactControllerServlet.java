package pl.tatastruga.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	// request.setCharacterEncoding("UTF-8");
	// response.setCharacterEncoding("UTF-8");

	
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
			String theCommand = request.getParameter("command");
			
			if(theCommand == null)
				theCommand = "LIST";
			
			switch(theCommand)
			{
			case "LIST":
				listContact(request, response);
				break;
								
			default: 			
				listContact(request, response);
			}
			
		} 
		catch (Exception e)
		{
			throw new ServletException(e);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        try {

            String theCommand = request.getParameter("command");
                    
            switch (theCommand) 
            {
                            
            case "ADD":
            	boolean isValid = validateAddForm(request, response);
                if(isValid)
                	addContact(request, response);
                break;
                                
            default:
                listContact(request, response);
            }
                
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
	}

		
	private boolean validateAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String firstName = request.getParameter("firstName");
		String theRegex = "^[\\p{L} .'-]{1,35}$";
		boolean firstNnameIsValid = regexChecker(theRegex, firstName);
		if (!firstNnameIsValid)
		{
			if(firstName.trim().length() == 0 || firstName == null)
				firstNnameIsValid = true;
			else if(firstName.trim().length() > 35)
			{
				String firstNameNotValid = "Sorry! Entered name is too long.";
				request.setAttribute("FIRST_NAME_INVALID", firstNameNotValid);
			}
			else
			{
				String firstNameNotValid = "Invalid characters! Try again.";
				request.setAttribute("FIRST_NAME_INVALID", firstNameNotValid);
			}			
		
		}
				
		String lastName = request.getParameter("lastName");
		theRegex = "^[\\p{L} .'-]{1,35}$";
		boolean lastNnameIsValid = regexChecker(theRegex, lastName);
		if (!lastNnameIsValid)
		{
			if(lastName.trim().length() == 0 || lastName == null)
				lastNnameIsValid = true;
			else if(lastName.trim().length() > 35)
			{
				String lastNameNotValid = "Sorry! Entered name is too long.";
				request.setAttribute("LAST_NAME_INVALID", lastNameNotValid);
			}
			else
			{
				String lastNameNotValid = "Invalid characters! Try again.";
				request.setAttribute("LAST_NAME_INVALID", lastNameNotValid);
			}			
		}
		
		String email = request.getParameter("email");
		theRegex = "^[A-Za-z0-9\\._-]+@[A-Za-z0-9\\._-]+\\.[A-Za-z]{2,4}$";
		boolean emailIsValid = regexChecker(theRegex, email);
		if (emailIsValid == true && email.trim().length() > 70)
			emailIsValid = false;
		if (!emailIsValid)
		{
			if(email.trim().length() == 0 || lastName == null)
			{
				String emailNotValid = "Email field can't be empty";
				request.setAttribute("EMAIL_INVALID", emailNotValid);
			}
			else if(email.trim().length() > 70)
			{
				String emailNotValid = "Sorry! Entered email is too long.";
				request.setAttribute("EMAIL_INVALID", emailNotValid);
			}
			else
			{
				String emailNotValid = "Wrong format! Try again.";
				request.setAttribute("EMAIL_INVALID", emailNotValid);
			}			
		}
		
		String phoneNumber = request.getParameter("phoneNumber");
		theRegex = "^[0-9 -]{9,20}$";
		boolean phoneNumberIsValid = regexChecker(theRegex, phoneNumber);
		if (!phoneNumberIsValid)
		{
			if(phoneNumber.trim().length() == 0 || phoneNumber == null)
				phoneNumberIsValid = true;
			else if(phoneNumber.trim().length() < 9)
			{
				String phoneNumberNotValid = "Number to short.";
				request.setAttribute("PHONE_NUMBER_INVALID", phoneNumberNotValid);
			}
			else if(phoneNumber.trim().length() > 20)
			{
				String phoneNumberNotValid = "Entered number is too long.";
				request.setAttribute("PHONE_NUMBER_INVALID", phoneNumberNotValid);
			}
			else
			{
				String phoneNumberNotValid = "Wrong format! No special characters or letters allowed.";
				request.setAttribute("PHONE_NUMBER_INVALID", phoneNumberNotValid);
			}
		}
		
		if(firstNnameIsValid == false || phoneNumberIsValid == false || lastNnameIsValid == false ||  emailIsValid == false)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-contact.jsp");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
		
		
	}



	private boolean regexChecker(String theRegex, String firstName)
	{
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(firstName);
		
		if (regexMatcher.find())
			return true;
		
		return false;
	}

	private void addContact(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String circle = request.getParameter("circle");
		
		Contact theContact = new Contact(firstName, lastName, email,  phoneNumber, circle);
		
		contactDBUtil.addContact(theContact);
		
// preventing multiple submissions
		response.sendRedirect(request.getContextPath() + "/ContactControllerServlet?command=LIST");
		
	}

	private void listContact(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<Contact> contactList = contactDBUtil.getContacts();

		request.setAttribute("CONTACT_LIST", contactList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts-list.jsp");

		dispatcher.forward(request, response);
	}

}
