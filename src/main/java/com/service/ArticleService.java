package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Article;
@Service("articleService")
public interface ArticleService {
	public int insertArticle(Article article);

	public int updateArticle(Article article);

	public int deleteArticle(String articleid);

	public int deleteArticleByIds(String[] ids);

	public List<Article> getAllArticle();

	public List<Article> getArticleByCond(Article article);

	public List<Article> getArticleByLike(Article article);

	public Article getArticleById(String articleid);

}

