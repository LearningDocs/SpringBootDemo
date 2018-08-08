package com.keepgulp.springbootdrools.controller;

import com.keepgulp.springbootdrools.model.Address;
import com.keepgulp.springbootdrools.model.Order;
import com.keepgulp.springbootdrools.model.Product;
import com.keepgulp.springbootdrools.service.DemoService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 17:26
 **/
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/getDiscount")
    @ResponseBody
    public Product getQuestions(@RequestParam(required = true) String type) {
        Product product = new Product();
        product.setType(type);
        product = demoService.getProductDiscount(product);
        return product;
    }

    @GetMapping("/getOrders")
    @ResponseBody
    public List<Order> getQuestions() {
        return demoService.getOrderScores();
    }

    @ResponseBody
    @RequestMapping("/address")
    public void test(int num){
        demoService.getAddressCheckResult(generateRandom(num));
    }

    /**
     * 从数据加载最新规则
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/reload")
    public String reload() throws IOException {
        demoService.reload();
        return "ok";
    }


    /**
     * 生成随机数
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number=new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number=number.append(chars.charAt(rand));
        }
        System.out.println("num = [" + number.toString() + "]");
        return number.toString();
    }
}
