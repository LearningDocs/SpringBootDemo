package com.keepgulp.springbootdrools.repository;

import com.keepgulp.springbootdrools.entity.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository ruleRepository;

    @Test
    public void insertRule() {
        Rule rule = new Rule();
        String ruleContent = "package rules\n" +
                "\n" +
                "import com.keepgulp.springbootdrools.model.Address;\n" +
                "import com.keepgulp.springbootdrools.model.AddressCheckResult;\n" +
                "\n" +
                "rule \"Postcode should be filled with exactly 5 numbers\"\n" +
                "    when\n" +
                "        address : Address(postcode != null, postcode matches \"([0-9]?)\")\n" +
                "        checkResult : AddressCheckResult();\n" +
                "    then\n" +
                "        checkResult.setPostCodeResult(true);\n" +
                "\t\tSystem.out.println(\"规则中打印日志：校验通过!\");\n" +
                "end";
        rule.setContent(ruleContent);
        rule.setCreateTime("2018-08-08");
        rule.setLastModifyTime("2018-08-08");
        rule.setRuleKey("address");
        rule.setVersion("v1");
        ruleRepository.save(rule);
    }

}