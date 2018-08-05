package com.keepgulp.springbootcrawler.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
public class ConfigProperites {
    /**
     * 爬取任务的间隔时间
     */
    @Getter
    private static long sleepTime;

    @Value("${crawler.job.interval.sleep.time}")
    public void setSleepTime(long sleepTime) {
        ConfigProperites.sleepTime = sleepTime;
    }

    /**
     * 从队列中获取任务，返回空时，等待时间之后再进行重试
     */
    @Getter
    private static long emptyQueueWaitTime;

    @Value("${crawler.job.empty.queue.waittime}")
    public void setEmptyQueueWaitTime(long emptyQueueWaitTime) {
        ConfigProperites.emptyQueueWaitTime = emptyQueueWaitTime;
    }

    /**
     * 对象池大小
     */
    @Getter
    private static int fetchQueueSize;

    @Value("${crawler.job.pool.fetch.queue.size}")
    public void setFetchQueueSize(int fetchQueueSize) {
        ConfigProperites.fetchQueueSize = fetchQueueSize;
    }

    @Getter
    private static Integer maxConnection;

    @Value("${http.client.max.connection}")
    public void setMaxConnection(Integer maxConnection) {
        ConfigProperites.maxConnection = maxConnection;
    }

    @Getter
    private static Integer connectionTimeout;

    @Value("${http.client.connection.timeout}")
    public void setConnectionTimeout(Integer connectionTimeout) {
        ConfigProperites.connectionTimeout = connectionTimeout;
    }

    @Getter
    private static Integer socketTimeout;

    @Value("${http.client.socket.timeout}")
    public void setSocketTimeout(Integer socketTimeout) {
        ConfigProperites.socketTimeout = socketTimeout;
    }
}
