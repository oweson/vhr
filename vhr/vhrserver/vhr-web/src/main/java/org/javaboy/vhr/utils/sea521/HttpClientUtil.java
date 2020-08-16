package org.javaboy.vhr.utils.sea521;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 封装了HttpClient发送http和https请求的方法(包括get和post请求)，形成一个工具类HttpClientUtil，
 * 大家以后在用到网络请求时，直接用这个工具类即可
 *
 * @author chenyz
 * @date 2019-04-04
 */
public class HttpClientUtil {
    private static Log log = LogFactory.getLog(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 1 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 2 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 3 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 4 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 5 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }


    /**
     * 1 发送GET请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     * @author Charlie.chen；
     */
    public static String doGet(String url, Map<String, Object> params) {

        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;

        // 创建默认的HttpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // 定义一个get请求方法
            HttpGet httpget = new HttpGet(apiUrl);

            // 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);

            // 使用响应对象, 获得状态码, 处理内容
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // 使用响应对象获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                // 将响应实体转为字符串
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                log.error("访问失败" + statusCode);
            }

        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                // 关闭连接, 和释放资源
                httpclient.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
        return null;
    }

    /**
     * 2 发送POST请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     * @author Charlie.chen
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String doPost(String url, Map<String, String> params) {

        // 创建默认的HttpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // 定义一个get请求方法
            HttpPost httppost = new HttpPost(url);

            // List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            // parameters.add(new BasicNameValuePair("username", userName));
            // parameters.add(new BasicNameValuePair("password", password));

            // 定义post请求的参数
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                httppost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            }


            // httppost.setHeader("Content-type","application/json,charset=utf-8");
            // httppost.setHeader("Accept", "application/json");


            // 执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);

            // 使用响应对象, 获得状态码, 处理内容
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // 使用响应对象获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                // 将响应实体转为字符串
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                log.error("访问失败" + statusCode);
            }

        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                // 关闭连接, 和释放资源
                httpclient.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
        return null;
    }


    /**
     * 3 发送 SSL POST请（HTTPS），K-V形式
     *
     * @param url
     * @param params
     * @author Charlie.chen
     */
    public static String doPostSSL(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConn()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("访问失败" + statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 4 发送 SSL POST请（HTTPS），K-V形式, 可指定提交编码格式
     *
     * @param url
     * @param params
     * @param encoding
     * @author Charlie.chen
     */
    public static String doPostSSL(String url, Map<String, Object> params, String encoding) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConn()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            if (encoding != null && !"".equals(encoding)) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(encoding)));
            } else {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("访问失败" + statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, encoding);
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 5 发送 SSL POST请（HTTPS），K-V形式, 可指定提交编码格式
     *
     * @param url
     * @param params
     * @param encoding
     * @param timeout  单位:毫秒
     * @author Charlie.chen
     */
    public static String doPostSSL(String url, Map<String, Object> params, String encoding, int timeout) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConn())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                        .setSocketTimeout(timeout).build())
                .build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            if (encoding != null && !"".equals(encoding)) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(encoding)));
            } else {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("访问失败" + statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, encoding);
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 6 发送 SSL POST请（HTTPS），K-V形式, 可指定提交编码格式
     *
     * @param url
     * @param params
     * @param encoding
     * @param timeout  单位:毫秒
     * @author Charlie.chen
     */
    public static String doPostSSL(String url, Map<String, String> headers, Map<String, Object> params, String encoding, int timeout) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConn())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                        .setSocketTimeout(timeout).build())
                .build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            // 设置头信息
            if (MapUtils.isNotEmpty(headers)) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            if (encoding != null && !"".equals(encoding)) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(encoding)));
            } else {
                httpPost.setEntity(new UrlEncodedFormEntity(pairList));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("访问失败" + statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, encoding);
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 7 创建SSL安全连接
     *
     * @param url
     * @param params
     * @return
     * @author Charlie.chen
     */
    private static SSLConnectionSocketFactory createSSLConn() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @SuppressWarnings("unused")
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @SuppressWarnings("unused")
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @SuppressWarnings("unused")
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            log.error(e);
        }
        return sslsf;
    }


    /**
     * 8 发送post请求,并上传文件
     */
    public String postWithFile(String url, String filePath) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);
            FileBody bin = null;

            File file = new File(filePath);
            if (file.exists()) {
                bin = new FileBody(file);
            } else {
                System.out.println("Can't find " + filePath);
            }


            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

            //请求实体
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();
            httppost.setEntity(reqEntity);

            //执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);

            // 使用响应对象, 获得状态码, 处理内容
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // 使用响应对象获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                //将响应实体转为字符串
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                log.error("访问失败" + statusCode);
            }

        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                // 关闭连接, 和释放资源
                httpclient.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
        return null;
    }
}
