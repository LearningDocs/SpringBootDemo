package com.keepgulp.springbootcrawler.proxypool.site.parser;

import com.keepgulp.springbootcrawler.proxypool.entity.Proxy;
import com.keepgulp.springbootcrawler.proxypool.site.ProxyListPageParser;
import com.keepgulp.springbootcrawler.proxypool.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * http://m.66ip.cn/1.html
 */
@Slf4j
public class M66ipProxyListPageParser implements ProxyListPageParser {

    @Override
    public List<Proxy> parse(Document document) {
        Elements elements = document.select("table tr:gt(1)");
        List<Proxy> proxyList = new ArrayList<>(elements.size());
        for (Element element : elements){
            String ip = element.select("td:eq(0)").first().text();
            String port  = element.select("td:eq(1)").first().text();
            String isAnonymous = element.select("td:eq(3)").first().text();
            log.debug("parse result = http://"+ip+":"+port+"  "+isAnonymous);
            if(!anonymousFlag || isAnonymous.contains("匿")){
                proxyList.add(new Proxy(ip, Integer.valueOf(port), "http", Constant.TIME_INTERVAL));
            }
        }
        return proxyList;
    }
}
