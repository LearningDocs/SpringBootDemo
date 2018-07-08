package com.keepgulp.springbootarticletags.service;

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
public class IArticleServiceTest {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @Test
    public void save() {
        List<Tag> tags = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            Tag tag = new Tag();
            tag.setName("标签" + i);
            tags.add(tag);
        }

        tags = tagService.saveAll(tags);

        Article article1 = new Article();
        article1.setTitle("文章1");
        article1.setContent("内容1");
        article1.setTags(tags);
        articleService.save(article1);

        List<Tag> tags2 = new ArrayList<>();


        for(int i=0; i< tags.size() / 2 ; i++) {
            tags2.add(tags.get(i));
        }

        Article article2 = new Article();
        article2.setTitle("文章2");
        article2.setContent("内容2");
        article2.setTags(tags2);
        articleService.save(article2);
    }

    @Test
    public void findByTagsContainsAnyOf() {
        List<String> tags = new ArrayList<>();
//        tags.add()
    }

    @Test
    public void findByTagsContainsAll() {
    }
}