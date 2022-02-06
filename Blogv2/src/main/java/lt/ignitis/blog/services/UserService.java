package lt.ignitis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.ignitis.blog.entities.User;
import lt.ignitis.blog.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User findUserByEmail(String email) {		
		return userRepository.findByEmail(email);		
	}
	
	public User findUserBySeesion(String sessionId) {		
		return userRepository.findBySessionId(sessionId);		
	}	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}		
}
