



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

package cn.webank.dockin.rm.http;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class GetDataJob implements Callable<String> {
    public static final int REQUEST_TIMEOUT_SECONDS = 15;
    String url;
    Map<String, String> paramMap;
    private Logger logger = LoggerFactory.getLogger(GetDataJob.class);

    public GetDataJob(String url, Map<String, String> paramMap) {
        this.url = url;
        this.paramMap = paramMap;
    }

    @Override
    public String call() throws Exception {
        int timeout = REQUEST_TIMEOUT_SECONDS;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet httpGet = new HttpGet();
        URIBuilder uri = new URIBuilder(url);
        try {
            httpGet.setConfig(config);
            if (paramMap != null) {
                if (paramMap != null) {
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        uri.addParameter(entry.getKey(), entry.getValue());
                    }
                }

                httpGet.setURI(uri.build());
                httpGet.setHeader("Content-type", "application/json");
            }

            logger.debug("Executing request: {}, address: {} ", httpGet.getRequestLine(), uri.build());

            ResponseHandler<String> responseHandler = new DataResponseHandler();
            String responseBody = httpclient.execute(httpGet, responseHandler);
            logger.debug("Response: " + responseBody);
            return responseBody;
        } finally {
            httpGet.releaseConnection();
            httpclient.close();
        }
    }

    public String exectue() throws Exception {
        FutureTask task = new FutureTask(this);
        task.run();
        return (String) task.get();
    }

    @Override
    public String toString() {
        return "GetDataJob{" +
                "url='" + url + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
