package com.keepgulp.springbootarticletags.repository;

import com.keepgulp.springbootarticletags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByNameIn(List<String> tags);

    boolean existsByName(String name);

    Tag findDistinctByName(String name);
}
