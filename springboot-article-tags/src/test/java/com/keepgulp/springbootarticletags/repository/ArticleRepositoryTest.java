package com.keepgulp.springbootarticletags.repository;

import com.keepgulp.springbootarticletags.entity.Article;
import com.keepgulp.springbootarticletags.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void save() {
        List<Tag> tags = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            Tag tag = new Tag();
            tag.setName("标签" + i);
            tags.add(tag);
        }

        tags = tagRepository.saveAll(tags);

        Article article1 = new Article();
        article1.setTitle("文章1");
        article1.setContent("内容1");
        article1.setTags(tags);
        articleRepository.save(article1);

        List<Tag> list = tagRepository.findAll();

        List<Tag> tags2 = new ArrayList<>();


        for(int i=0; i< list.size() / 2 ; i++) {
            tags2.add(list.get(i));
        }

        Article article2 = new Article();
        article2.setTitle("文章2");
        article2.setContent("内容2");
        article2.setTags(tags2);
        articleRepository.save(article2);
    }

    @Test
    public void findAllByTitle() {

        List<Article> list = articleRepository.findAll();

        for( Article article : list) {
            List<Tag> tags = article.getTags();
            for(Tag tag : tags) {
                System.out.println(article.getTitle() + ":" + tag.getName());
            }
        }
    }
}