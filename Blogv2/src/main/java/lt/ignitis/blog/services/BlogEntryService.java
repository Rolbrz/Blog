package lt.ignitis.blog.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ignitis.blog.entities.BlogEntry;
import lt.ignitis.blog.repositories.BlogEntryRepository;

@Service
public class BlogEntryService {

	@Autowired
	BlogEntryRepository blogEntryRepository;
	
	public BlogEntry saveBlogEntry(BlogEntry blogEntry) {		
		return blogEntryRepository.save(blogEntry);		
	}
	
	public void deleteBlogEntryById(Integer id) {		
		blogEntryRepository.deleteById(id);	
	}
	
	public BlogEntry findBlogEntryById(Integer Id) {		
		return blogEntryRepository.findById(Id).orElse(null);		
	}	
}
