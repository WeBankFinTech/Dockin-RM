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
import cn.webank.dockin.rm.utils.StringFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ReloadRMConfiguration extends ReloadConfiguration<RMConfig> {
    public ReloadRMConfiguration(File file, long cacheInterval, TimeUnit unit, Class cl) {
        super(file, cacheInterval, unit, cl);
    }

    @Override
    public RMConfig loadConfiguration(File file, Class cl) {

        Properties properties = new Properties();
        try {
            RMConfig rmConfig = new RMConfig();
            properties.load(new FileInputStream(file));
            rmConfig.setCpuExceededPercentage(StringFormatter.parseNumberFromPercentage(properties.getProperty("rm.assignment.cpu.exceeded-percentage")));

            return rmConfig;
        } catch (Exception e) {
            throw new SysException("load file error", e);
        }
    }
}
