package klu.modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.UserRepository;

@Service
public class UserManager {

	@Autowired
	UserRepository UR;
	
	@Autowired
	EmailManager EM;

	public String insertData(User U)
	{
		if (UR.findById(U.getEmailid()).isPresent()) {
            return "Email already exists";
        }
		UR.save(U);
		EM.sendEmail(U.getEmailid(), "Account Created", "Your account has been created successfully. Welcome to JobPortal!");
		return "Account created successfully";
	}
	
	public String getPassword(String emailid)
	{
		User U = UR.findById(emailid).orElse(null);
		if(U == null)
		{
			return "Email ID not found";
		}
		return U.getPassword();
	}
	
	public String signIn(String emailid, String password)
	{
		User U = UR.findById(emailid).orElse(null);
		if(U == null)
		{
			return "Email ID not found";
		}
		if(U.getPassword().equals(password))
		{
			return "Login successful. " + U.getRole();
		}
		return "Invalid password";
	}
	
	public String getFullName(String emailid)
	{
		User U = UR.findById(emailid).orElse(null);
		if(U == null)
		{
			return "User not found";
		}
		return U.getName();
	}
}
