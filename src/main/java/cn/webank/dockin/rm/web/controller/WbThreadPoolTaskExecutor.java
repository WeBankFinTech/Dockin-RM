



/*
 * Copyright (C) @2021 Webank Group Holding Limited
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

package cn.webank.dockin.rm.web.controller;

import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class WbThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private int queueCapacity = Integer.MAX_VALUE;
    private TaskDecorator taskDecorator;
    private boolean allowCoreThreadTimeOut = false;
    private ThreadPoolExecutor threadPoolExecutor;
    private BlockingQueue<Runnable> taskQueue;

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public boolean isAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    @Override
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public TaskDecorator getTaskDecorator() {
        return taskDecorator;
    }

    @Override
    public void setTaskDecorator(TaskDecorator taskDecorator) {
        this.taskDecorator = taskDecorator;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    @Override
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

        @Override
    protected ExecutorService initializeExecutor(
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {

        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);

        ThreadPoolExecutor executor;
        taskDecorator = new TaskDecorator() {
            @Override
            public Runnable decorate(Runnable runnable) {
                return runnable;
            }
        };
        executor = new ThreadPoolExecutor(
                this.getCorePoolSize(), this.getMaxPoolSize(), this.getKeepAliveSeconds(), TimeUnit.SECONDS,
                queue, threadFactory, rejectedExecutionHandler) {
            @Override
            public void execute(Runnable command) {
                super.execute(taskDecorator.decorate(command));
            }
        };

        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;

        return executor;
    }


        protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            this.taskQueue = new LinkedBlockingQueue<Runnable>(queueCapacity);
            return this.taskQueue;
        } else {
            this.taskQueue = super.createQueue(queueCapacity);
            return this.taskQueue;
        }
    }

    public BlockingQueue<Runnable> getTaskQueue() {
        return taskQueue;
    }
}

