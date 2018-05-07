package entity;

import java.io.Serializable;

public class Comments implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commentsId;

    private Author author;

    private Article article;

    private String comments;

    public Comments(){}
    
    public Comments(Long commentsId,Author author,Article article,String comments){
    	this.commentsId = commentsId;
    	this.author = author;
    	this.article = article;
    	this.comments = comments;
    }
    public Long getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Long commentsId) {
        this.commentsId = commentsId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}