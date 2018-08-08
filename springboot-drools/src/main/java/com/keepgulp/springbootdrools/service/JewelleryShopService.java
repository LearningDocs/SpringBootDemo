package com.keepgulp.springbootdrools.service;

import com.keepgulp.springbootdrools.model.Product;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 17:28
 **/
@Service
public class JewelleryShopService {

    @Autowired
    private KieContainer kieContainer;

    public Product getProductDiscount(Product product) {
        //get the stateful session
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
        return product;
    }

}
