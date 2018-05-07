package entity;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long articleId;

    private String title;

    private String text;

//    private Long author;
    private Author author;
    
//    private List<Comments> comments;
//    
//    public List<Comments> getComments() {
//		return comments;
//	}
//	public void setComments(List<Comments> comments) {
//		this.comments = comments;
//	}
	public Article(){}
    public Article(Long articleId,String title,String text,Author author){
    	this.articleId = articleId;
    	this.title = title;
    	this.text = text;
    	this.author = author;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}