package com.keepgulp.springbootdrools.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 18:25
 **/

@Setter
@Getter
public class Order {

    private Date bookingDate;//下单日期

    private int amout;//订单原价金额

    private User user;//下单人

    private int score;
}
