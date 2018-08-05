package com.keepgulp.springbootcrawler.proxypool.task;

import com.keepgulp.springbootcrawler.common.entity.CrawlResult;
import com.keepgulp.springbootcrawler.core.job.DefaultAbstractCrawlJob;
import com.keepgulp.springbootcrawler.proxypool.entity.Proxy;
import com.keepgulp.springbootcrawler.proxypool.manager.ProxyPool;
import com.keepgulp.springbootcrawler.proxypool.site.ProxyListPageParser;
import com.keepgulp.springbootcrawler.proxypool.site.ProxyListPageParserFactory;

import java.util.List;

public class QueueCrawlerProxyJob extends DefaultAbstractCrawlJob {

    public void beforeRun() {
        // 设置返回的网页编码
        super.setResponseCode("gbk");
    }

    @Override
    protected void visit(CrawlResult crawlResult) {
        String url = crawlResult.getUrl();
        ProxyListPageParser proxyListPageParser = ProxyListPageParserFactory.getProxyListPageParser(ProxyPool.proxyMap.get(url));
        if(null != proxyListPageParser) {
            List<Proxy> proxyList = proxyListPageParser.parse(crawlResult.getHtmlDoc());
            System.out.println("---------------------------------------------");
            System.out.println("url:" + url);
            for(Proxy proxy : proxyList) {
                System.out.println(proxy);
            }
            System.out.println("---------------------------------------------");
        }
    }
}
