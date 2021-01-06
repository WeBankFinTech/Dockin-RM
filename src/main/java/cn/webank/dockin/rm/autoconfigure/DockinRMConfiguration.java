/*
 * Copyright (C) @2020 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.webank.dockin.rm.autoconfigure;

import cn.webank.dockin.rm.autoconfigure.bean.RMConfig;
import cn.webank.dockin.rm.exception.SysException;
import cn.webank.dockin.rm.web.controller.WbThreadPoolTaskExecutor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@Configuration
@EnableTransactionManagement
@ComponentScan(lazyInit = false)
@EnableScheduling
@EnableAsync
@EnableCaching
public class DockinRMConfiguration extends WebMvcConfigurerAdapter {

    @Bean(name = "frontTaskExecutor")
    public ThreadPoolTaskExecutor frontThreadPoolTaskExecutor(
            @Value("${dockin-rm.front.threadpool.core-pool-size:128}") int frontCorePoolSize,
            @Value("${dockin-rm.front.threadpool.keep-alive-seconds:600}") int frontKeepAliveSeconds,
            @Value("${dockin-rm.front.threadpool.max-pool-size:128}") int frontMaxPoolSize,
            @Value("${dockin-rm.front.threadpool.queue-capacity:300}") int frontQueueCapacity,
            @Value("${dockin-rm.front.threadpool.allow-core-thread-time-out:false}") boolean frontAllowCoreThreadTimeOut,
            @Value("${dockin-rm.front.threadpool.await-termination-seconds:60}") int frontAwaitTerminationSeconds,
            @Value("${dockin-rm.front.threadpool.wait-for-task-to-complete-on-shutdown:true}") boolean waitForTasksToCompleteOnShutdown) {
        WbThreadPoolTaskExecutor executor = new WbThreadPoolTaskExecutor();
        executor.setCorePoolSize(frontCorePoolSize);
        executor.setKeepAliveSeconds(frontKeepAliveSeconds);
        executor.setMaxPoolSize(frontMaxPoolSize);
        executor.setQueueCapacity(frontQueueCapacity);
        executor.setAllowCoreThreadTimeOut(frontAllowCoreThreadTimeOut);
        executor.setAwaitTerminationSeconds(frontAwaitTerminationSeconds);
        executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    @Bean(name = "defaultTaskExecutor")
    public ScheduledExecutorService reloadThreadPoolTaskExecutor() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10, new ThreadFactory() {
            AtomicInteger i = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "DefaultTaskThread-" + i.incrementAndGet());
                t.setDaemon(true);
                return t;
            }
        });

        return executorService;
    }

    @Bean(name = "messageSource")
    public MessageSource messageResource() {
        ReloadableResourceBundleMessageSource messageResource =
                new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheSeconds(3600);
        return messageResource;
    }

    @Bean(name = "reloadConfiguration")
    public ReloadRMConfiguration ReloadRMConfiguration() {
        String routeFile = "/application.properties";
        try {

            ClassPathResource res = new ClassPathResource(routeFile);
            InputStream is = res.getInputStream();

            File file = File.createTempFile("temp-config", "properties");
            FileUtils.copyInputStreamToFile(is , file);

//            PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
//            File file = pmrpr.getResource(routeFile).getFile();
            ReloadRMConfiguration reloadConfiguration = new ReloadRMConfiguration(file, 60, TimeUnit.SECONDS,
                    RMConfig.class);
            return reloadConfiguration;
        } catch (Exception e) {
            throw new SysException("init ReloadRMConfiguration fail", e);

        }
    }
}
