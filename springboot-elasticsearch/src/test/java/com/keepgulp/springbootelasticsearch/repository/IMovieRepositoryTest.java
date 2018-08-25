package com.keepgulp.springbootelasticsearch.repository;

import com.keepgulp.springbootelasticsearch.entity.Movie;
import com.keepgulp.springbootelasticsearch.entity.Page;
import com.keepgulp.springbootelasticsearch.entity.QueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IMovieRepositoryTest {

    @Autowired
    private IMovieRepository movieRepository;

    @Test
    public void query() {
        Page<Movie> page = movieRepository.query("爆裂无声",1, 10);
        log.info(page.toString());
        Assert.assertNotNull(page);
    }

    @Test
    public void query1() {
        QueryDTO queryDTO = QueryDTO.builder().minScore(7.5f).orderBy("updateDate").build();
        Page<Movie> page = movieRepository.query(queryDTO,1, 10);
        log.info(page.toString());
        Assert.assertNotNull(page);
    }

}