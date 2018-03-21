
package fi.tamk.tiko;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

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

    @RequestMapping(value = "/blogposts",  method=RequestMethod.POST)
    public void saveLocation(@RequestBody BlogPost c) {

        database.save(c);
    }

    @RequestMapping(value = "/blogposts",  method=RequestMethod.GET)
    public Iterable<BlogPost> fetchLocation() {
        return database.findAll();
    }

    @RequestMapping(value = "/blogposts/{id}",  method=RequestMethod.GET)
    public BlogPost fetchLocation(@PathVariable int id) {
        for(BlogPost c : database.findAll()) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }


    @RequestMapping(value = "/comments",  method=RequestMethod.POST)
    public void saveExercise(@RequestBody Comment c) {

        commentRepository.save(c);
    }

    @RequestMapping(value = "/comments",  method=RequestMethod.GET)
    public Iterable<Comment> fetchExercise() {
        return commentRepository.findAll();
    }

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

