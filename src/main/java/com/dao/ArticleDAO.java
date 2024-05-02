package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Article;

@Repository("articleDAO")
@Mapper
public interface ArticleDAO {

	public int insertArticle(Article article);

	public int updateArticle(Article article);

	public int deleteArticle(String articleid);

	public int deleteArticleByIds(String[] ids);

	public List<Article> getAllArticle();

	public List<Article> getArticleByCond(Article article);

	public List<Article> getArticleByLike(Article article);

	public Article getArticleById(String articleid);

}


