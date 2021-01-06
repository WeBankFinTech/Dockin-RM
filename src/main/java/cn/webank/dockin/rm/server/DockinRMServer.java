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

package cn.webank.dockin.rm.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;


@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "cn.webank", lazyInit = true)
public class DockinRMServer {
    public static void main(String[] args) throws Exception {
        try {
            new DockinRMServer().service(DockinRMServer.class, args);
        } catch (Exception exp) {
            exp.printStackTrace();
            System.exit(1);
        }
    }

    public void service(Class<?> serverClass, String[] args) throws Exception {
        String command = "start";
        ServerCommandEnum aCommand = ServerCommandEnum.getServerCommandEnum(command);
        if (aCommand.equals(ServerCommandEnum.START)) {
            startServer(serverClass, args);

        }
    }

    private void resetStartedSuccessTip() {
        File openedTipFile = new File("./start_success");
        if (openedTipFile.exists()) {
            openedTipFile.delete();
        }
    }

    private void startServer(Class<?> serverClass, String[] args) throws Exception {
        resetStartedSuccessTip();
        SpringApplication springApplication = new SpringApplication(serverClass);
        springApplication.setWebEnvironment(true);

        springApplication.run(args);
    }


















}
