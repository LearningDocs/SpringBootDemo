package com.keepgulp.springbootcrawler.core.job;


import com.keepgulp.springbootcrawler.common.entity.CrawlResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SimpleCrawlJob extends DefaultAbstractCrawlJob {
    /**
     * 存储爬取的结果
     */
    private CrawlResult crawlResult;


    /**
     * 批量查询的结果
     */
    private List<CrawlResult> crawlResults = new ArrayList<>();



    public SimpleCrawlJob(int depth) {
        super(depth);
    }


    @Override
    protected void visit(CrawlResult crawlResult) {
        crawlResults.add(crawlResult);
    }


    public CrawlResult getCrawlResult() {
        if(crawlResults.size() == 0) {
            return null;
        }

        return crawlResults.get(0);
    }
}
