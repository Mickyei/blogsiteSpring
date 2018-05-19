
package fi.tamk.tiko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


// This class acts as a controller.
// Usually when using @Controller, you will use also @RequestMapping
@RestController
public class MyController {


    @Autowired
    BlogPostRepository database;

    @Autowired
    CommentRepository commentRepository;

    public MyController() {

    }

    @PostConstruct
    public void init() {

            String title = "NEW BLOGPOST";
            String text = "Welcome to the blogsite, here is a random blogpost";
            String author = "Weeaboo420";
            String commentUser = "Naruto666";
            String commentText = "Can you delete this please?";

            BlogPost newBlog = new BlogPost();
            newBlog.setText(text);
            newBlog.setTitle(title);
            newBlog.setAuthor(author);

            Comment comm = new Comment();
            comm.setComment(commentText);
            comm.setUsername(commentUser);
            comm.setBlogPost(newBlog);

            Set<Comment> comments = new HashSet<Comment>();
            comments.add(comm);
            newBlog.setComments(comments);

            database.save(newBlog);

    }


    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/blogposts",  method=RequestMethod.POST)
    public void saveLocation(@RequestBody BlogPost c) {

        database.save(c);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/blogposts",  method=RequestMethod.GET)
    public Iterable<BlogPost> fetchLocation() {
        return database.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/blogposts/{id}",  method=RequestMethod.GET)
    public BlogPost fetchLocation(@PathVariable int id) {
        for(BlogPost c : database.findAll()) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/comments",  method=RequestMethod.POST)
    public void saveExercise(@RequestBody Comment c) {

        commentRepository.save(c);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public void deleteBlogpost(@PathVariable int id) {

        for(BlogPost c : database.findAll()) {
            if(c.getId() == id) {
                database.delete(c);

            }
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int id) {

        for(Comment c : commentRepository.findAll()) {
            if(c.getId() == id) {
                commentRepository.delete(c);

            }
        }
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.PUT)
    public void updateBlogpost(@PathVariable int id, @RequestBody BlogPost blog) {


        for(BlogPost c : database.findAll()) {
            if(c.getId() == id) {
                c.setPoints(blog.getPoints());
                c.setText(blog.getText());
                c.setTitle(blog.getTitle());
                c.setAuthor(blog.getAuthor());
                database.save(c);
            }
        }
    }


    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/comments",  method=RequestMethod.GET)
    public Iterable<Comment> fetchExercise() {
        return commentRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @RequestMapping(value = "/comments/{id}",  method=RequestMethod.GET)
    public Comment fetchExercise(@PathVariable int id) {
        for(Comment c : commentRepository.findAll()) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}

