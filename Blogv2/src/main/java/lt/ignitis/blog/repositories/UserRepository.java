package lt.ignitis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ignitis.blog.entities.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);	
			
	public User findBySessionId(String sessionId);
	
}





