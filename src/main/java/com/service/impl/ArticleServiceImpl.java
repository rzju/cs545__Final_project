package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ArticleDAO;
import com.entity.Article;
import com.service.ArticleService;

@Service("articleService") //
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	@Override
	public int insertArticle(Article article) {
		return this.articleDAO.insertArticle(article);
	}

	@Override
	public int updateArticle(Article article) {
		return this.articleDAO.updateArticle(article);
	}

	@Override
	public int deleteArticle(String articleid) {
		return this.articleDAO.deleteArticle(articleid);
	}

	@Override
	public int deleteArticleByIds(String[] ids) {
		return this.articleDAO.deleteArticleByIds(ids);
	}

	@Override
	public List<Article> getAllArticle() {
		return this.articleDAO.getAllArticle();
	}

	@Override
	public List<Article> getArticleByCond(Article article) {
		return this.articleDAO.getArticleByCond(article);
	}

	@Override
	public List<Article> getArticleByLike(Article article) {
		return this.articleDAO.getArticleByLike(article);
	}

	@Override
	public Article getArticleById(String articleid) {
		return this.articleDAO.getArticleById(articleid);
	}

}

