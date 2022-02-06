package lt.ignitis.blog.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "users")
public class User {

	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;	
	
	@Column
	private String sessionId; 	
	

	@OneToMany(mappedBy = "user")	
	private Set<BlogEntry> userBlogEntries;	
	

	public User(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public User(String email, String password) {		
		this.email = email;
		this.password = password;
	}	
	
	public User(String email) {
		super();
		this.email = email;
	}

	public User() {
		super();
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BlogEntry> getUserBlogEntries() {
		return userBlogEntries;
	}

	public void setUserBlogEntries(Set<BlogEntry> userBlogEntries) {
		this.userBlogEntries = userBlogEntries;
	}

	@Override
	public String toString() {
		return "User email: " + email;
	}
	

	
}
