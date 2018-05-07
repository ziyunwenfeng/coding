package dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import entity.Article;
import entity.ArticleExample;
import entity.Comments;


public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
    
    Article selectArticleByArticle(Long articleid);
    
    Article selectArticleWithCommentsByArticle(Long articleid);
    
    Comments selectArticleByComments(Long commentsId);
}