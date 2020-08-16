package org.javaboy.vhr.utils.sea521;

import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    /**
     * 程序中访问http数据接口
     */
    @SuppressWarnings("unused")
    public static String getURLContent(String urlStr) {
        /** 1 网络的url地址 */
        URL url = null;
        /** 2 http连接 */
        HttpURLConnection httpConn = null;
        /** 3 输入流 */
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(),
                    "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        String result = sb.toString();
        return result;
    }


    public static void main(String[] args) {
        String url = "http://www.mxnzp.com/api/address/search?type=1&value=深圳";
        String str = getURLContent(url);
        JSONArray array = JSONArray.parseArray(str);
        System.out.println(array);
    }

}
