package service;

import entity.Article;
import entity.Comments;

public interface IArticleService {
	public Article selectArticleByArticle(Long articleId);
	public Article selectArticleWithCommentsByArticle(Long articleId);
	public Comments selectArticleByComments(Long commentsId);
	public int insertArticle(Article article);
	public void deleteArticle(Long articleId);
	public void updateArticle(Article article);
}
