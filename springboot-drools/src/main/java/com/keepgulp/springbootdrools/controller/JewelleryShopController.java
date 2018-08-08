package com.keepgulp.springbootdrools.controller;

import com.keepgulp.springbootdrools.model.Product;
import com.keepgulp.springbootdrools.service.JewelleryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 17:26
 **/
@Controller
public class JewelleryShopController {

    @Autowired
    private JewelleryShopService jewelleryShopService;

    @GetMapping("/getDiscount")
    @ResponseBody
    public Product getQuestions(@RequestParam(required = true) String type) {
        Product product = new Product();
        product.setType(type);
        product = jewelleryShopService.getProductDiscount(product);
        return product;
    }
}
