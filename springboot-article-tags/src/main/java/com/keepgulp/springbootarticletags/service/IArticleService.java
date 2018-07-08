package com.keepgulp.springbootarticletags.service;

import com.keepgulp.springbootarticletags.entity.Article;

import java.util.List;

public interface IArticleService {

    Article save(Article article);

    List<Article> findByTagsContainsAnyOf(List<String> tags);

    List<Article> findByTagsContainsAll(List<String> tags);

}
