package cn.yj.notes.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreaPoolConf{
    /** 核心线程数*/
    private static final int  CORE_POOL_SIZE= 2;
    /** 最大线程数*/
    private static final int  MAX_NUM_POOL_SIZE= 10;
    /** 空闲时间*/
    private static final long  KEEPALL_LIVETIME= 60;


    @Bean
    public ThreadPoolExecutor threadPoolExecutor(
            @Qualifier("Handler") RejectedExecutionHandler rejectedExecutionHandler)
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_NUM_POOL_SIZE,
                KEEPALL_LIVETIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                rejectedExecutionHandler
        );

        return threadPoolExecutor;
    }

    /**
     * 拒绝策略
     * @return
     */
    @Bean("Handler")
    public RejectedExecutionHandler rejectedExecutionHandler()
    {
        return new ThreadPoolExecutor.AbortPolicy();
    }
}