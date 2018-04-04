package fi.tamk.tiko;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fi.tamk.tiko.Comment;


// Täst tulee BLOGPOST
@Entity
public class BlogPost {


    private int id;
    @ElementCollection(targetClass=Integer.class)
    private Set<Comment> comments;
    private int points;
    private String title;
    private String text;
    private String author;

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }


    @OneToMany(mappedBy="blogPost", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment) {
        this.comments.add(comment);
        if(comment.getBlogPost() != this) {
            comment.setBlogPost(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BlogPost() {
        this.points = 0;
        comments = new HashSet<Comment>();

    }

}
