package lt.ignitis.blog.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import at.favre.lib.crypto.bcrypt.BCrypt;
import lt.ignitis.blog.entities.User;
import lt.ignitis.blog.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;	
	
	@PostMapping("/login")
	@ResponseBody
	public String userLogin (@RequestParam String email, @RequestParam String password, HttpSession session) {
		
		User user = userService.findUserByEmail(email);
		// Check if user's email exists in DB
		if (user == null) { 			
			return email + "|| User with this email doesn't exist";			
		}else {
		// Check if another user is not already logged in				
			User tmpUser = userService.findUserBySeesion(session.getId());
			if (tmpUser != null && tmpUser != user ) return "Another user is already loged in";			
		// Check password and if it is correct save user's sessions id 
			if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {				
				user.setSessionId(session.getId());
				userService.saveUser(user);
				return user.toString()+"|| Succesfully loged in";				
			}			
		return user.toString()+"|| Incorrect password";				
		}	
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public String newUser (@RequestParam String email, @RequestParam String password) {		
		User user = userService.findUserByEmail(email);
		// Check if user's email already exists in DB
		if (user == null) { 
		// Save user email and encrypted password to DB	
			User newUser = new User(email, BCrypt.withDefaults().hashToString(12, password.toCharArray()));
			userService.saveUser(newUser);
			return email + " || User added";			
		}
		// User with given email already exist	
		return user.toString()+" || User already exists";			
	}
	
	
	
	@GetMapping("/logout")
	@ResponseBody
	public String userLogout(HttpSession session) {
		User user = userService.findUserBySeesion(session.getId());
		// Check if user is logged in
		if (user==null) return "User is not loged in";			
		// Delete user's sesion id from DB and reset session
		user.setSessionId("");
		userService.saveUser(user);		
	    session.invalidate();
		return user.getEmail() + " || Succesfully logged out";
	}	
}
