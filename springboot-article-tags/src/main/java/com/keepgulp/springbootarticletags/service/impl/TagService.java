package com.keepgulp.springbootarticletags.service.impl;

import com.keepgulp.springbootarticletags.entity.Tag;
import com.keepgulp.springbootarticletags.repository.TagRepository;
import com.keepgulp.springbootarticletags.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {
        if(!tagRepository.existsByName(tag.getName())) {
            return tagRepository.save(tag);
        } else {
            return tagRepository.findDistinctByName(tag.getName());
        }
    }

    @Override
    public List<Tag> saveAll(List<Tag> tags) {
        List<Tag> exitsList = new ArrayList<>();
        List<Tag> newList = new ArrayList<>();
        for(Tag tag : tags) {
            if(tagRepository.existsByName(tag.getName())) {
                exitsList.add(tagRepository.findDistinctByName(tag.getName()));
            } else {
                newList.add(tag);
            }
        }
        exitsList.addAll(tagRepository.saveAll(newList));
        return exitsList;
    }
}
