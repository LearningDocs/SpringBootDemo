package com.keepgulp.springbootcrawler.proxypool.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProxyManagerTest {

    @Test
    public void start() throws Exception {
        ProxyManager proxyManager = ProxyManager.get();
        proxyManager.start();
    }
}