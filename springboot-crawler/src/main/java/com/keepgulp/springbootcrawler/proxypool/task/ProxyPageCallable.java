package com.keepgulp.springbootcrawler.proxypool.task;

import com.keepgulp.springbootcrawler.proxypool.entity.Proxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class ProxyPageCallable implements Callable<List<Proxy>> {

    protected String url;

    @Override
    public List<Proxy> call() throws Exception {
            return null;
    }
}
