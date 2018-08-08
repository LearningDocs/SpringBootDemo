package com.keepgulp.springbootdrools.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 19:03
 **/

@Entity
@Setter
@Getter
public class Rule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String ruleKey;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, unique = true)
    private String version;
    @Column(nullable = true, unique = true)
    private String lastModifyTime;
    @Column(nullable = false)
    private String createTime;
}
