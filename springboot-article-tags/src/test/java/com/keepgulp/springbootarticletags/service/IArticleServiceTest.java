package com.keepgulp.springbootarticletags.service;

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

    @Test
    public void save() {
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