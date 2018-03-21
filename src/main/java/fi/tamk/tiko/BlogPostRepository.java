package fi.tamk.tiko;

import org.springframework.data.repository.CrudRepository;


public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
    public Iterable<BlogPost> findAll();
    public void delete(BlogPost entity);
    public void delete(int id);
    public BlogPost findOne(int id);


}
