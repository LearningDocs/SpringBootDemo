package com.keepgulp.springbootcrawler.proxypool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;

import java.net.InetSocketAddress;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
@Setter
@Getter
@ToString
public class Proxy implements Delayed {

    private long timeInterval ;//任务间隔时间,单位ms
    private String id;
    private String ip;
    private int port;
    private String type;//http、https
    private boolean availableFlag;
    private boolean anonymousFlag;
    private long lastSuccessfulTime;//最近一次请求成功时间
    private long successfulTotalTime;//请求成功总耗时
    private int failureTimes;//请求失败次数
    private int successfulTimes;//请求成功次数
    private double successfulAverageTime;//成功请求平均耗时

    public Proxy() {}

    public Proxy(String ip, int port, long timeInterval) {
        this.ip = ip;
        this.port = port;
        this.type = "http";
        this.timeInterval = timeInterval;
        this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    public Proxy(String ip, int port, String type, long timeInterval) {
        this.ip = ip;
        this.port = port;
        this.type = type.toLowerCase();
        this.timeInterval = timeInterval;
        this.timeInterval = TimeUnit.NANOSECONDS.convert(timeInterval, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeInterval - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Proxy element = (Proxy)o;
        if (successfulAverageTime == 0.0d ||element.successfulAverageTime == 0.0d){
            return 0;
        }
        return successfulAverageTime > element.successfulAverageTime ? 1:(successfulAverageTime < element.successfulAverageTime ? -1 : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Proxy proxy = (Proxy) o;

        if (port != proxy.port)
            return false;
        return ip.equals(proxy.ip);

    }

    @Override
    public int hashCode() {
        int result = ip.hashCode();
        result = 31 * result + port;
        return result;
    }

    public String getProxyStr() {
        return type + "://" + ip + ":" + port;
    }

    /**
     * 将Proxy转换成一个HttpHost对象
     * @return
     */
    public HttpHost toHttpHost() {

        return new HttpHost(ip,port,type);
    }

    public java.net.Proxy toJavaNetProxy() {

        return new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(ip , port));
    }
}
