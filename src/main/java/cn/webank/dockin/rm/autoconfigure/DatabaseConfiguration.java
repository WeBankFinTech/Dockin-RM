



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

package cn.webank.dockin.rm.autoconfigure;

import cn.webank.dockin.rm.exception.SysException;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.vendor.MySqlValidConnectionChecker;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Configuration
@MapperScan("cn.webank.dockin.rm.database.dao")
public class DatabaseConfiguration {
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.url.dockin_rm_data}")
    private String jdbcUrl;

    @Value("${jdbc.user}")
    private String jdbcUser;

    @Value("${jdbc.initial-size:3}")
    private int jdbcInitialSize = 3;

    @Value("${jdbc.min-idle:3}")
    private int jdbcMinIdle = 3;

    @Value("${jdbc.max-active:200}")
    private int jdbcMaxActive = 200;

    
    @Value("${jdbc.max-wait:2}")
    private int jdbcMaxWait = 2;

    
    @Value("${jdbc.time-between-eviction-runs-millis:60000}")
    private int jdbcTimeBetweenEvictionRunsMillis = 60000;

    
    @Value("${jdbc.min-evictable-idle-time-millis:30000}")
    private int jdbcMinEvictableIdleTimeMillis = 30000;

    @Value("${jdbc.validation-query}")
    private String jdbcValidationQuery = "SELECT 'x'";

    @Value("${jdbc.validation-query-timeout:2}")
    private int validationQueryTimeout = 2;

    
    @Value("${jdbc.test-while-idle:false}")
    private boolean jdbcTestWhileIdle = false;

    
    @Value("${jdbc.test-on-borrow:false}")
    private boolean jdbcTestOnBorrow = false;

    
    @Value("${jdbc.test-on-return:false}")
    private boolean jdbcTestOnReturn = false;

    @Value("${jdbc.prepared-statements:false}")
    private boolean jdbcPoolPreparedStatements = false;

    
    @Value("${jdbc.max-pool-prepared-statement-per-connection-size:-1}")
    private int jdbcMaxPoolPreparedStatementPerConnectionSize = -1;

    private static Pattern p = Pattern.compile("(/db_[^/?]*_)([A-Z])(/|\\?|$)");

    
    @Value("${jdbc.keep-alive:true}")
    private boolean jdbcKeepAlive = true;

    
    @Value("${jdbc.usePingMethod:false}")
    private boolean jdbcUsePingMethod = false;

    
    @Value("${jdbc.filters:stat}")
    private String jdbcFilters = "stat";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
    private static String updateDatabaseName(String url) {
        Matcher m = p.matcher(url);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String replacement = m.group(1) + m.group(2).toLowerCase() + m.group(3);
            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private DataSource dataSource(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty.");
        }
        logger.info("original datasource url=" + url);
        url = updateDatabaseName(url);
        logger.info("patched  datasource url=" + url);
        try {
            DruidDataSource ds = new DruidDataSource();
            ds.setUrl(url);
            ds.setUsername(jdbcUser);
            ResourceLoader resourceLoader = new DefaultResourceLoader();

            ds.setPassword(jdbcPassword);
            ds.setInitialSize(jdbcInitialSize);
            ds.setMinIdle(jdbcMinIdle);
            ds.setMaxActive(jdbcMaxActive);
            ds.setMaxWait(jdbcMaxWait);
            ds.setTimeBetweenEvictionRunsMillis(jdbcTimeBetweenEvictionRunsMillis);
            ds.setMinEvictableIdleTimeMillis(jdbcMinEvictableIdleTimeMillis);
            ds.setValidationQuery(jdbcValidationQuery);
            ds.setValidationQueryTimeout(validationQueryTimeout);
            ds.setTestWhileIdle(jdbcTestWhileIdle);
            ds.setTestOnBorrow(jdbcTestOnBorrow);
            ds.setTestOnReturn(jdbcTestOnReturn);
            ds.setPoolPreparedStatements(jdbcPoolPreparedStatements);
            ds.setMaxPoolPreparedStatementPerConnectionSize(
                    jdbcMaxPoolPreparedStatementPerConnectionSize);
            ds.setKeepAlive(jdbcKeepAlive);
            ds.setFilters(jdbcFilters);
            ds.setConnectionInitSqls(Arrays.asList("set names utf8mb4"));

            if (!jdbcUsePingMethod) {


                MySqlValidConnectionChecker checker = new MySqlValidConnectionChecker();
                checker.setUsePingMethod(false);
                ds.setValidConnectionChecker(checker);
            }

            return ds;
        } catch (SQLException e) {
            throw new SysException("datasource construct failed", e);
        }
    }

    @Bean(name = "dataSource")
    @Primary
    DataSource dataSource() {
        logger.info("druid init!");
        return dataSource(jdbcUrl);
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        factory.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new SysException("sqlSessionFactory construct fail", e);
        }
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
