package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ArticleMapper;
import dao.CommentsMapper;
import entity.Article;
import entity.Comments;
@Service
public class ArticleService implements IArticleService {
	@Autowired 
	ArticleMapper articlemapper;
	@Autowired 
	CommentsMapper commentsMapper;
	public Article selectArticleByArticle(Long articleId) {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleWithCommentsByArticle(articleId);
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
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		List<Comments> comments = article.getComments();
		if(comments.size()==0){
			articlemapper.updateByPrimaryKey(article);
		}
		else{
			for(Comments comment : comments){
				commentsMapper.updateByPrimaryKey(comment);
			}
		}
	}
	
	

}
