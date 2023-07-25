package com.example.springboottools.service.impl;

import com.example.springboottools.dao.ArticleRepository;
import com.example.springboottools.entity.mongo.Article;
import com.example.springboottools.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @Override
    public int create(Article article) {
        Article save = articleRepository.save(article);
        return 1;
    }

    /**
     * 删除文章
     *
     * @param ids
     */
    @Override
    public int delete(List<String> ids) {
        List<Article> deleteList = new ArrayList<>();

        for(String id:ids){
            Article article = new Article();
            article.setId(id);
            deleteList.add(article);
        }
        articleRepository.deleteAll(deleteList);

        return ids.size();
    }

    /**
     * 查询文章
     * @param id
     * @return
     */
    @Override
    public List<Article> list(String id) {
        return articleRepository.findByid(id);
    }


}
