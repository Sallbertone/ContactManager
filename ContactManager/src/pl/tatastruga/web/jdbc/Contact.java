package pl.tatastruga.web.jdbc;

public class Contact
{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String circle;
	
	
	
	
	public Contact(int id, String firstName, String lastName, String email, String phoneNumber, String circle)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.circle = circle;
	}

	public Contact( String firstName, String lastName, String email, String phoneNumber, String circle)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.circle = circle;
	}

	public Contact()
	{
		
	}

	public int getId()
	{
		return id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public String getCircle()
	{
		return circle;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public void setCircle(String circle)
	{
		this.circle = circle;
	}

	@Override
	public String toString()
	{
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", circle=" + circle + "]";
	}
	
	
	
}
