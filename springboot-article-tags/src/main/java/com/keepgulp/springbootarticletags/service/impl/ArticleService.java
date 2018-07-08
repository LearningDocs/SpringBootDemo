package com.keepgulp.springbootarticletags.service.impl;

import com.keepgulp.springbootarticletags.entity.Article;
import com.keepgulp.springbootarticletags.entity.Tag;
import com.keepgulp.springbootarticletags.repository.ArticleRepository;
import com.keepgulp.springbootarticletags.repository.TagRepository;
import com.keepgulp.springbootarticletags.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;


    @Override
    public Article save(Article article) {
        List<Tag> tags = article.getTags();
        if(!CollectionUtils.isEmpty(tags)) {
            tags = tagRepository.saveAll(tags);
        }
        article.setTags(tags);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findByTagsContainsAnyOf(List<String> tags) {

        List<Tag> tagList = tagRepository.findByNameIn(tags);

        return articleRepository.findByTagsIn(tagList);
    }

    @Override
    public List<Article> findByTagsContainsAll(List<String> tags) {
        List<Tag> tagList = tagRepository.findByNameIn(tags);
        return articleRepository.findByTagsContains(tagList);
    }
}
