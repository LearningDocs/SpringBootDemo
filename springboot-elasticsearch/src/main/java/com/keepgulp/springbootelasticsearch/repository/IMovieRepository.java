package com.keepgulp.springbootelasticsearch.repository;

import com.keepgulp.springbootelasticsearch.entity.Movie;
import com.keepgulp.springbootelasticsearch.entity.Page;
import com.keepgulp.springbootelasticsearch.entity.QueryDTO;

public interface IMovieRepository {
    boolean save(Movie movie);

    Page<Movie> query(String queryString, int pageNo, int size);

    Page<Movie> query(QueryDTO queryDTO, int pageNo, int size);

    Movie get(String id);
}
