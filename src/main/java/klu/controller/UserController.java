package klu.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import klu.modal.EmailManager;
import klu.modal.User;
import klu.modal.UserManager;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {

	@Autowired
	UserManager UM;
	
	@Autowired
	EmailManager EM;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User U)
	{
		return ResponseEntity.ok(Map.of("message", UM.insertData(U)));
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<String> forgotPassword(@RequestBody User U)
	{
		return ResponseEntity.ok(UM.getPassword(U.getEmailid()));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody User U)
	{
		String result = UM.signIn(U.getEmailid(), U.getPassword());
		if (result.startsWith("Login successful")) {
		    String role = result.substring("Login successful.".length()).trim();
		    return ResponseEntity.ok(Map.of("token", "your_token_here", "role", role));
		}
		return ResponseEntity.badRequest().body(Map.of("error", result));
	}
	
	@PostMapping("/getfullname")
	public ResponseEntity<String> getFullName(@RequestBody Map<String, String> data)
	{
		return ResponseEntity.ok(UM.getFullName(data.get("csrid")));
	}
}
