package com.example.springboottools.dao;

import com.example.springboottools.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 继承 MongoRepository<实体类，主键类型>,以实现CRUD
 **/

public interface ArticleRepository extends MongoRepository<Article,String> {

    //根据id查询文章【创建自定义接口时需要遵守MongoRepository的命名规范，这里接口需要起名findBy + 主键名，否则会报找不到属性的错误】
    List<Article> findByid(String id);
}

