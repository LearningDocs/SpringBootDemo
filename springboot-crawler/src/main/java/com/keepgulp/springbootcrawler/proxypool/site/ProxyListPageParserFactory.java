package com.keepgulp.springbootcrawler.proxypool.site;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProxyListPageParserFactory {

    private static Map<String, ProxyListPageParser> map = new HashMap<>();

    public static ProxyListPageParser getProxyListPageParser(Class clazz){

        String parserName = clazz.getSimpleName();
        if (map.containsKey(parserName)){
            return map.get(parserName);
        } else {
            try {
                ProxyListPageParser parser = (ProxyListPageParser) clazz.newInstance();
                parserName = clazz.getSimpleName();
                map.put(parserName, parser);
                return parser;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
