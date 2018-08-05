package com.keepgulp.springbootcrawler.core.job;

import com.keepgulp.springbootcrawler.common.entity.CrawlMeta;
import com.keepgulp.springbootcrawler.common.entity.CrawlResult;
import com.keepgulp.springbootcrawler.core.fetcher.Fetcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QueueCrawlerJobTest {
    public static class QueueCrawlerJob extends DefaultAbstractCrawlJob {

        public void beforeRun() {
            // 设置返回的网页编码
            super.setResponseCode("gbk");
        }

        @Override
        protected void visit(CrawlResult crawlResult) {
            System.out.println(Thread.currentThread().getName() + "___" + crawlMeta.getCurrentDepth() + "___" + crawlResult.getUrl());
        }
    }

    @Test
    public void testCrawel() throws Exception {
        Fetcher fetcher = new Fetcher(2, QueueCrawlerJob.class);

//        String url = "https://chengyu.911cha.com/zishu_4.html";
        String url = "http://www.ip3366.net";
        CrawlMeta crawlMeta = new CrawlMeta();
        crawlMeta.setUrl(url);
        crawlMeta.addPositiveRegex("http://www.ip3366.net/\\?stype=1&page=[2-5]$");
//        crawlMeta.addPositiveRegex("https://chengyu.911cha.com/[a-z|A-Z]+==.html$");

        fetcher.addFeed(crawlMeta);


        fetcher.start();
    }
}
