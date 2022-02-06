package lt.ignitis.blog.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import lt.ignitis.blog.entities.BlogEntry;
import lt.ignitis.blog.entities.User;
import lt.ignitis.blog.services.BlogEntryService;
import lt.ignitis.blog.services.UserService;

@Controller
public class BlogEntryController {
	
	@Autowired
	BlogEntryService blogEntryService;
	
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/add_entry")
	@ResponseBody
	public String addBlogEntry(@RequestParam String title, @RequestParam String text, HttpSession session) {
		// Authenticate user by session id
		User user = userService.findUserBySeesion(session.getId());
		if (user == null) return "Login to perform this action";	
		// Save blog entry to DB
		BlogEntry blogEntry = new BlogEntry(title,text);
		blogEntry.setUser(user);
		blogEntryService.saveBlogEntry(blogEntry);		
		return "Blog entry saved to DB";
	}
	
	@GetMapping("/user_entries")
	@ResponseBody
	public String getUserEntries(HttpSession session) {
		// Authenticate user by session id
		User user = userService.findUserBySeesion(session.getId());
		if (user == null) return "Login to view your blog entries";	
		// Fetch user's blog entries from DB
		return user.getUserBlogEntries().toString();
	}
	
	@PostMapping("/edit_entry")
	@ResponseBody
	public String editBlogStringEntry (@RequestParam Integer id, @RequestParam String title, @RequestParam String text, HttpSession session) {
		// Authenticate user by session id
		User user = userService.findUserBySeesion(session.getId());
		if (user == null) return "Login to perform this action";
		// Fetch blog entry to be edited from DB by id
		BlogEntry blogEntry = blogEntryService.findBlogEntryById(id);
		if (blogEntry == null) return "Blog entry no longer exist";
		// Check if user is authorized,if so edit and save changes to DB
		if (blogEntry.getUser() == user) {
			blogEntry.setTitle(title);
			blogEntry.setText(text);
			blogEntryService.saveBlogEntry(blogEntry);
			return "Upadated blog entry saved to DB";
		}		
		return "Can't update this entry, because it's created by another user";
	}
	
	@PostMapping("/delete_entry")
	@ResponseBody
	public String deleteBlogEntry (@RequestParam Integer id, HttpSession session) {
		// Authenticate user by session id
		User user = userService.findUserBySeesion(session.getId());
		if (user == null) return "Login to perform this action";
		// Fetch blog entry to be edited from DB by id
		BlogEntry blogEntry = blogEntryService.findBlogEntryById(id);		
		if (blogEntry == null) return "Blog entry no longer exist";
		// Check if user is authorized to delete, if so delete from DB
		if (blogEntry.getUser() == user) {			
			blogEntryService.deleteBlogEntryById(id);			
			return "Blog entry deleted from DB";
		}		
		return "Can't delete this entry, because it's created by another user";
	}
	
}
