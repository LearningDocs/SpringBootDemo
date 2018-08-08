package com.keepgulp.springbootdrools.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 19:20
 **/
@Setter
@Getter
public class AddressCheckResult {
    private boolean postCodeResult = false; // true:通过校验；false：未通过校验
}
