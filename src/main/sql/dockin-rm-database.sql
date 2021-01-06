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

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 dockin_rm_data_dev 的数据库结构
CREATE DATABASE IF NOT EXISTS `dockin_rm_data_dev` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `dockin_rm_data_dev`;

-- 导出  表 dockin_rm_data_dev.host_info 结构
CREATE TABLE IF NOT EXISTS `host_info` (
  `host_ip` varchar(30) COLLATE utf8_bin NOT NULL,
  `tor` varchar(200) COLLATE utf8_bin NOT NULL,
  `wa_tor` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `idc` varchar(10) COLLATE utf8_bin NOT NULL,
  `dcn` varchar(10) COLLATE utf8_bin NOT NULL,
  `env_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cluster_id` varchar(30) COLLATE utf8_bin NOT NULL,
  `cluster_version` varchar(50) COLLATE utf8_bin NOT NULL,
  `all_cpu` double(10,3) NOT NULL,
  `all_mem` double(10,3) NOT NULL,
  `all_disk` int(11) NOT NULL,
  `available_cpu` double(10,3) NOT NULL,
  `available_mem` double(10,3) NOT NULL,
  `available_disk` int(11) NOT NULL,
  `state` varchar(20) COLLATE utf8_bin NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`host_ip`),
  KEY `IDX_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。

-- 导出  表 dockin_rm_data_dev.pod_info 结构
CREATE TABLE IF NOT EXISTS `pod_info` (
  `pod_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `pod_set_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sub_system` varchar(20) COLLATE utf8_bin NOT NULL,
  `sub_system_id` varchar(10) COLLATE utf8_bin NOT NULL,
  `dcn` varchar(10) COLLATE utf8_bin NOT NULL,
  `pod_ip` varchar(30) COLLATE utf8_bin NOT NULL,
  `gateway_id` varchar(30) COLLATE utf8_bin NOT NULL,
  `subnet_mask` varchar(30) COLLATE utf8_bin NOT NULL,
  `host_ip` varchar(30) COLLATE utf8_bin NOT NULL,
  `cpu` double(10,3) NOT NULL,
  `cpu_request` double(10,3) NOT NULL,
  `mem` double(10,3) NOT NULL,
  `mem_request` double(10,3) NOT NULL,
  `disk` int(11) NOT NULL,
  `itsm_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `offline_itsm_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `update_itsm_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `people` varchar(30) COLLATE utf8_bin NOT NULL,
  `type` varchar(30) COLLATE utf8_bin NOT NULL,
  `port` int(11) NOT NULL,
  `jmx_port` int(11) NOT NULL,
  `tag` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(30) COLLATE utf8_bin NOT NULL,
  `cluster_id` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `expiry_type` tinyint(1) NOT NULL DEFAULT '0',
  `state` varchar(20) COLLATE utf8_bin NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pod_name`),
  KEY `IDX_pod_ip` (`pod_ip`),
  KEY `IDX_itsm_id` (`itsm_id`),
  KEY `IDX_state` (`state`),
  KEY `IDX_sub_system` (`sub_system`),
  KEY `IDX_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。

-- 导出  表 dockin_rm_data_dev.pod_network 结构
CREATE TABLE IF NOT EXISTS `pod_network` (
  `pod_name` varchar(1000) COLLATE utf8mb4_bin NOT NULL,
  `ip` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `subnet_mask` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `gateway` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ip`),
  KEY `IDX_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 数据导出被取消选择。

-- 导出  表 dockin_rm_data_dev.subsystem 结构
CREATE TABLE IF NOT EXISTS `subsystem` (
  `subsystem_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `important_level` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `subsystem_id` varchar(10) COLLATE utf8_bin NOT NULL,
  `system_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `logic_area` varchar(10000) COLLATE utf8_bin DEFAULT NULL,
  `dev_language` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `busiResDept` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `pro_oper_group` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`subsystem_id`),
  KEY `IDX_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
