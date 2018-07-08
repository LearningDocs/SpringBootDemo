package com.keepgulp.springbootarticletags.repository;

import com.keepgulp.springbootarticletags.entity.Article;
import com.keepgulp.springbootarticletags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findAllByTitle(String title);

    List<Article> findByTagsIn(List<Tag> tagList);

    List<Article> findByTagsContains(List<Tag> tagList);
}
