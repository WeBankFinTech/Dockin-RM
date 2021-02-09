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
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
public class PostDataJob implements Callable<String> {
    public static final int REQUEST_TIMEOUT_SECONDS = 15;
    String uri;
    String requestBody;
    HashMap<String, String> paramMap;
    private Logger logger = LoggerFactory.getLogger(PostDataJob.class);
    public PostDataJob(String uri, String requestBody) {
        this.uri = uri;
        this.requestBody = requestBody;
    }
    @Override
    public String call() throws Exception {
        int timeout = REQUEST_TIMEOUT_SECONDS;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpPost httpPost = new HttpPost(uri);
        try {
            if (requestBody != null) {
                httpPost.setEntity(new StringEntity(requestBody, APPLICATION_JSON));
                logger.debug("send http request param: " + requestBody);
            } else if (paramMap != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                if (paramMap != null) {
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                httpPost.setHeader("Content-type", "application/json");
                logger.debug("send http request param: " + nvps.toString());
            }
            logger.debug("Executing request: " + httpPost.getRequestLine());
            ResponseHandler<String> responseHandler = new DataResponseHandler();
            String responseBody = httpclient.execute(httpPost, responseHandler);
            logger.debug("Response: " + responseBody);
            return responseBody;
        } finally {
            httpPost.releaseConnection();
            httpclient.close();
        }
    }
    public String execute() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(this);
        task.run();
        return (String) task.get();
    }
    @Override
    public String toString() {
        return "PostDataJob{" +
                "uri='" + uri + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
