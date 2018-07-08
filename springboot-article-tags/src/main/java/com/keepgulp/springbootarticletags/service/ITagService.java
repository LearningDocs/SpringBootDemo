package com.keepgulp.springbootarticletags.service;

import com.keepgulp.springbootarticletags.entity.Tag;

import java.util.List;

public interface ITagService {

    Tag save(Tag tag);

    List<Tag> saveAll(List<Tag> tags);
}
