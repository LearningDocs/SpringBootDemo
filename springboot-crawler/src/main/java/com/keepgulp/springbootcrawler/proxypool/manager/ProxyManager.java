package com.keepgulp.springbootcrawler.proxypool.manager;

import com.keepgulp.springbootcrawler.common.entity.CrawlMeta;
import com.keepgulp.springbootcrawler.core.fetcher.Fetcher;
import com.keepgulp.springbootcrawler.proxypool.site.parser.Ip3366ProxyListPageParser;
import com.keepgulp.springbootcrawler.proxypool.site.parser.M66ipProxyListPageParser;
import com.keepgulp.springbootcrawler.proxypool.task.QueueCrawlerProxyJob;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProxyManager {

    private ProxyManager() {
    }

    public static ProxyManager get() {
        return ProxyManager.Holder.MANAGER;
    }

    private static class Holder {
        private static final ProxyManager MANAGER = new ProxyManager();
    }

    /**
     * 抓取代理，成功的代理存放到ProxyPool中
     */
    public void start() throws Exception {

        Map<String, Class> proxyMap = new HashMap<>();
        proxyMap.put("http://www.ip3366.net", Ip3366ProxyListPageParser.class);
        proxyMap.put("http://www.ip3366.net/?stype=1&page=2", Ip3366ProxyListPageParser.class);
        proxyMap.put("http://www.ip3366.net/?stype=1&page=3", Ip3366ProxyListPageParser.class);
        proxyMap.put("http://www.ip3366.net/?stype=1&page=4", Ip3366ProxyListPageParser.class);
        proxyMap.put("http://m.66ip.cn/1.html", M66ipProxyListPageParser.class);
        proxyMap.put("http://m.66ip.cn/2.html", M66ipProxyListPageParser.class);
        proxyMap.put("http://m.66ip.cn/3.html", M66ipProxyListPageParser.class);

        ProxyPool.proxyMap = proxyMap;

        Fetcher fetcher = new Fetcher(0, QueueCrawlerProxyJob.class);

        for(String url : ProxyPool.proxyMap.keySet()) {
            CrawlMeta crawlMeta = new CrawlMeta();
            crawlMeta.setUrl(url);
            fetcher.addFeed(crawlMeta);
        }
        fetcher.start();

//        Flowable.fromIterable(ProxyPool.proxyMap.keySet())
//                .parallel()
//                .map(new Function<String, List<Proxy>>() {
//                    @Override
//                    public List<Proxy> apply(String s) {
//                        try {
//                            return new ProxyPageCallable(s).call();
//                        } catch (Exception e) {
//                            log.error(e.getMessage());
//                            e.printStackTrace();
//                        }
//                        return new ArrayList<Proxy>();
//                    }
//                });
    }
}
