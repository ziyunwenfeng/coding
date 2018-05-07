package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ArticleMapper;
import entity.Article;
import entity.Comments;
@Service
public class ArticleService implements IArticleService {
	@Autowired 
	ArticleMapper articlemapper;
	public Article selectArticleByArticle(Long articleId) {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleByArticle(articleId);
	}
	public Article selectArticleWithCommentsByArticle(Long articleId) {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleWithCommentsByArticle(articleId);
	}
	public Comments selectArticleByComments(Long commentsId) {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleByComments(commentsId);
	}
	public int insertArticle(Article article) {
		// TODO Auto-generated method stub
		return articlemapper.insert(article);
	}
	public void deleteArticle(Long articleId) {
		// TODO Auto-generated method stub
		articlemapper.deleteByPrimaryKey(articleId);
	}
	
	

}
