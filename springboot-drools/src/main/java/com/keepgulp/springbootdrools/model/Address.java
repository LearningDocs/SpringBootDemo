package com.keepgulp.springbootdrools.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 19:02
 **/
@Setter
@Getter
public class Address {
    private String postcode;

    private String street;

    private String state;
}
