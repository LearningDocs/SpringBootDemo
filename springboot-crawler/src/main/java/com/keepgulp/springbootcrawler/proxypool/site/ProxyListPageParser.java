package com.keepgulp.springbootcrawler.proxypool.site;

import com.keepgulp.springbootcrawler.proxypool.entity.Proxy;
import org.jsoup.nodes.Document;

import java.util.List;

public interface ProxyListPageParser {



    /**
     * 是否只要匿名代理
     */
    boolean anonymousFlag = true;

    /**
     * 转化器，将页面元素中代理相关的信息提取出来
     * @param document
     * @return
     */
    List<Proxy> parse(Document document);
}
