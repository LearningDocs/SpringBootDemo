package com.keepgulp.springbootdb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootDbApplicationTests {

    protected String base;
    protected RestTemplate client;

    @Autowired
    Environment environment;

    public String getPort(){
        return environment.getProperty("local.server.port");
    }

    @Before
    public void setUp() throws Exception {
        String port = getPort();
        this.base = "http://localhost:" + port + "/account";
        this.client = new RestTemplate();

    }

    @Test
    public void testUpdate() {
        new Thread(() -> this.client.put(base + "/1/llt-1", null)).start();
        new Thread(() -> this.client.put(base + "/1/llt-2", null)).start();
        new Thread(() -> this.client.put(base + "/1/llt-3", null)).start();
        new Thread(() -> this.client.put(base + "/1/llt-4", null)).start();
        new Thread(() -> this.client.put(base + "/1/llt-5", null)).start();

        try {
            //waiting for execution result of service
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
