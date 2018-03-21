package fi.tamk.tiko;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


//Oli exercise
@Entity
public class Comment {

    private int id;
    private int amount;
    private String username;
    private String comment;
    private BlogPost blogPost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "blogpost_id")
    @JsonBackReference
    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
        if(blogPost.getComments().contains(this)) {
            blogPost.getComments().add(this);
        }
    }

    public int getAmount() {
        return amount;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment() {
        blogPost = new BlogPost();
    }



    @Override
    public String toString() {
        return "{ \"id\":" + id + ", \"username\": \"" + this.username + "\"" +
                ", \"points\" :" + amount + ", \"blog_id\":" + blogPost.getId() + "}";
    }


}
