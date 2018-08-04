package com.keepgulp.springbootcrawler.core.job;

import com.keepgulp.springbootcrawler.core.entity.CrawlMeta;
import com.keepgulp.springbootcrawler.core.entity.CrawlResult;
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

        String url = "http://chengyu.911cha.com/zishu_4.html";
        CrawlMeta crawlMeta = new CrawlMeta();
        crawlMeta.setUrl(url);
        crawlMeta.addPositiveRegex("http://chengyu.911cha.com/zishu_4_p[0-9]+\\.html$");

        fetcher.addFeed(crawlMeta);


        fetcher.start();
    }
}
