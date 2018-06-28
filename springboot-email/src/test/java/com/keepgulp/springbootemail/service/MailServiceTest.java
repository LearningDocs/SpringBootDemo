package com.keepgulp.springbootemail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    private String sendToEmail = "guodq12@163.com";

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail(sendToEmail,"test simple mail"," hello this is simple mail");
    }

    @Test
    public void sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(sendToEmail,"test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="E:\\工作记录\\【迭代】【3】20180625-20180706\\BI任务\\【依赖关系图】加和校验.svg";
        mailService.sendAttachmentsMail(sendToEmail, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\guodongqing\\Pictures\\微信图片_20180528135324.jpg";

        mailService.sendInlineResourceMail(sendToEmail, "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(sendToEmail,"主题：这是模板邮件",emailContent);
    }
}