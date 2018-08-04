package com.keepgulp.springbootcrawler.core.job;

import com.keepgulp.springbootcrawler.core.entity.CrawlResult;
import com.keepgulp.springbootcrawler.core.pool.SimplePool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractJob implements IJob {

    public void beforeRun() {
    }

    public void afterRun() {
    }


    /**
     * 解析完网页后的回调方法
     *
     * @param crawlResult
     */
    protected abstract void visit(CrawlResult crawlResult);


    @Override
    public void run() {
        this.beforeRun();


        try {
            this.doFetchPage();
        } catch (Exception e) {
            log.error("fetch page error! e: {}", e);
        }


        this.afterRun();

        // 将job扔回队列
        SimplePool.getInstance().release(this);
    }


    /**
     * 具体的抓去网页的方法， 需要子类来补全实现逻辑
     *
     * @throws Exception
     */
    abstract void doFetchPage() throws Exception;
}
