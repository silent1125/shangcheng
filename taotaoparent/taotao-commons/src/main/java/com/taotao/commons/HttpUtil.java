package com.taotao.commons;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
        @Override
        public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
            e.printStackTrace();
            return i <= 3; // 网络请求失败时，自动重试，连续3次失败不再重试
        }
    };

    private static CloseableHttpClient httpClient = null;

    static {
        httpClient = HttpClientBuilder.create()
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")
//       .setDefaultHeaders()
                .setRetryHandler(retryHandler)
                .build();
    }

    public static String doGet(String url) {
        return doGet(url, "UTF-8");
    }

    public static String doGet(String url, String charset) {
        HttpGet getMethod = new HttpGet(url);
        try {
            HttpResponse resp = httpClient.execute(getMethod);
            return EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, "UTF-8");
    }

    public static String doGet(String url, Map<String, String> params, String charset) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setCharset(Charset.forName(charset));
            if(params != null && params.size() > 0) {
                for(String key : params.keySet()) {
                    uriBuilder.addParameter(key, params.get(key));
                }
            }
            HttpGet getMethod = new HttpGet(url);
            HttpResponse resp = httpClient.execute(getMethod);
            return EntityUtils.toString(resp.getEntity(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost(String url) {
        return doPost(url, null, "UTF-8");
    }

    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, "UTF-8");
    }

    public static String doPost(String url, Map<String, String> params, String charset) {

        HttpPost postMethod = new HttpPost(url);
        try {
            if(params != null && params.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for(String key : params.keySet()) {
                    nvps.add(new BasicNameValuePair(key, params.get(key)));
                }
                postMethod.setEntity(new UrlEncodedFormEntity(nvps));
            }
            HttpResponse resp = httpClient.execute(postMethod);
            return EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
