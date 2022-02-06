package lt.ignitis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ignitis.blog.entities.BlogEntry;

public interface BlogEntryRepository extends JpaRepository<BlogEntry, Integer> {

}
