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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;


public abstract class ReloadConfiguration<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private T content;
    private ConfiguratonFileWatcher watcher;

    public ReloadConfiguration(File file, long cacheInterval, TimeUnit unit, Class cl) {
        content = loadConfiguration(file, cl);
        ConfiguratonFileWatcher fileWatcher = new ReloadConfiguration.ConfiguratonFileWatcher(file, cacheInterval, unit, cl);
        fileWatcher.setDaemon(true);
        fileWatcher.start();
        watcher = fileWatcher;
    }

    public void stopWatchConfiguration() {
        if (watcher != null) {
            watcher.setStop(true);
        }
    }

    public ConfiguratonFileWatcher getWatcher() {
        return watcher;
    }

    private class ConfiguratonFileWatcher extends Thread {
        private TimeUnit unit;
        private long cacheInterval;
        private volatile File configFile;
        private long lastCheckTime;
        private boolean stop = false;
        private long lastModifiedTime;
        private Class cl;

        public ConfiguratonFileWatcher(File file, long cacheInterval, TimeUnit unit, Class cl) {
            this.configFile = file;
            this.cacheInterval = cacheInterval;
            this.unit = unit;
            this.cl = cl;
            this.lastCheckTime =
                    System.currentTimeMillis() - this.unit.toMillis(this.cacheInterval) - 100;
            this.lastModifiedTime = -1;
        }

        public void run() {
            while (!stop) {

                if ((System.currentTimeMillis() - lastCheckTime >= this.unit
                        .toMillis(this.cacheInterval)) && configFile.exists()) {
                    long currentModifiedTime = this.configFile.lastModified();
                    if (this.lastModifiedTime != currentModifiedTime) {
                        try {
                            setContent(loadConfiguration(this.configFile, this.cl));
                            logger.info("reload content succeed, file={}", configFile.getAbsolutePath());
                        } catch (Exception e) {
                            logger.warn("reload content failed, file={}", configFile.getAbsolutePath(), e);
                        }
                        this.lastModifiedTime = currentModifiedTime;
                    }
                }

                this.lastCheckTime = System.currentTimeMillis();
                try {
                    Thread.sleep(unit.toMillis(cacheInterval));
                } catch (InterruptedException e) {
                    logger.error("thread sleep interrupted", e);
                }
            }
        }

        public void setLastModifiedTime(long modifiedTime) {
            this.lastModifiedTime = modifiedTime;
        }

        public long getLastModifiedTime() {
            return lastModifiedTime;
        }

        public TimeUnit getUnit() {
            return unit;
        }

        public long getCacheInterval() {
            return cacheInterval;
        }

        public File getConfigFile() {
            return configFile;
        }

        public long getLastCheckTime() {
            return lastCheckTime;
        }

        public boolean isStop() {
            return stop;
        }

        public Class getCl() {
            return cl;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }

    public abstract T loadConfiguration(File file, Class cl);

    synchronized public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public File getFile() {
        return watcher.configFile;
    }

}
