package com.unufolio.fondo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/13
 */
public class ThreadPoolUtils {
    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat
            ("guava-thread-pool-%d").build();

    private static ExecutorService executorService;

    public static ExecutorService getExecutorService() {
        var corePoolSize = Runtime.getRuntime().availableProcessors();
        var maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        var keepAliveTime = 60000L;
        var capacity = Integer.MAX_VALUE;
        return getExecutorService(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                capacity);
    }

    public static ExecutorService getExecutorService(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     int capacity) {
        if (executorService == null || executorService.isShutdown()) {
            executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(capacity), NAMED_THREAD_FACTORY,
                    new ThreadPoolExecutor.AbortPolicy());
        }
        return executorService;
    }
}
