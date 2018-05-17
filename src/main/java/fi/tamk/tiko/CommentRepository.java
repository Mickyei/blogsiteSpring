package fi.tamk.tiko;

import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Integer> {
    public Iterable<Comment> findAll();
    public void delete(Comment entity);
    public void delete(int id);


}
