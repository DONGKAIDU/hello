package com.easydu.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    private int s = 1;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    @RequestMapping("hello")
    public String getHello() {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                s++;
                if (s == 10) {
                    executorService.shutdown();
                }
                try {
                    Thread.sleep(30000);

                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }
        });

        return "duke hello";
    }

}
