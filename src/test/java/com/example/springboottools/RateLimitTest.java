package com.example.springboottools;

import com.example.springboottools.util.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class RateLimitTest {

    @Test
    public void test() throws InterruptedException {
        int clientSize = 10;
        CountDownLatch downLatch = new CountDownLatch(clientSize);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(clientSize);
        IntStream.range(0, clientSize).forEach(i ->
                fixedThreadPool.submit(() -> {
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getForObject("http://localhost:8081/rate/limit/get1", ResponseResult.class);
                    downLatch.countDown();
                })
        );
        downLatch.await();
        fixedThreadPool.shutdown();
    }
}
