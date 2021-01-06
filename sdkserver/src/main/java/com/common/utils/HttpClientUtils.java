package com.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpClientUtils {

    static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String convertResponseToString(CloseableHttpResponse response, String encode) throws IOException {
        String body = "";
        try {
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                body = EntityUtils.toString(entity, encode);
            }
        } finally {
            response.close();
        }
        return body;
    }

    public static Map<String, Object> httpGet(String url, Map<String, String> headers, String encode)
        throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        CloseableHttpResponse response = null;
        response = httpGetHttpResponse(url, headers);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = JSON.parseObject(convertResponseToString(response, encode));
        }
        return result;
    }

    public static CloseableHttpResponse httpGetHttpResponse(String url, Map<String, String> headers) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet getMethod = new HttpGet(url);
        // 设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                getMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = null;
        response = httpclient.execute(getMethod);
        return response;
    }

    public static Map<String, Object> httpPost(String url, String params, Map<String, String> headers, String encode)
        throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        CloseableHttpResponse response = null;
        response = httpPostHttpResponse(url, params, headers, encode);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = JSON.parseObject(convertResponseToString(response, encode));
        }
        return result;
    }

    public static CloseableHttpResponse httpPostFormHttpResponse(String url, Map<String, String> params,
                                                                 Map<String, String> headers, String encode) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost postMethod = new HttpPost(url);
        // 设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postMethod.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 设置请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        // 设置form提交方法
        postMethod.setEntity(new UrlEncodedFormEntity(paramList, encode));
        CloseableHttpResponse response = null;
        response = httpclient.execute(postMethod);
        return response;
    }

    public static CloseableHttpResponse httpPostHttpResponse(String url, String params, Map<String, String> headers,
                                                             String encode) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost postMethod = new HttpPost(url);
        // 设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 设置请求参数
        StringEntity stringEntity = new StringEntity(params, encode);
        postMethod.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        response = httpclient.execute(postMethod);
        return response;
    }

}
