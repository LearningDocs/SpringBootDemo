package com.keepgulp.springbootdrools.repository;

import com.keepgulp.springbootdrools.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 19:05
 **/
@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findByRuleKey(String ruleKey);
}
