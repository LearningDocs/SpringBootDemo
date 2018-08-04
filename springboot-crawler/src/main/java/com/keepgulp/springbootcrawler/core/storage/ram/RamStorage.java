package com.keepgulp.springbootcrawler.core.storage.ram;

import com.keepgulp.springbootcrawler.core.entity.CrawlResult;
import com.keepgulp.springbootcrawler.core.storage.IStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RamStorage implements IStorage {

    private Map<String, CrawlResult> map = new ConcurrentHashMap<>();


    @Override
    public boolean putIfNotExist(String url, CrawlResult result) {
        if(map.containsKey(url)) {
            return false;
        }

        map.put(url, result);
        return true;
    }

    @Override
    public boolean contains(String url) {
        return map.containsKey(url);
    }
}
