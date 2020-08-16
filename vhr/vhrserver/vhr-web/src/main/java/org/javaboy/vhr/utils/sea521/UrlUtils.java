package org.javaboy.vhr.utils.sea521;


import cn.hutool.core.util.URLUtil;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/27 0027 19:32
 */
public class UrlUtils {
    public static void main(String[] args) {
        String s = "http://how2j.cn/k/hutool/hutool-url/1952.html";
        String encode = URLUtil.encode(s);
        System.out.println(encode);
        String decode = URLUtil.decode(encode);
        System.out.println(decode);
    }
}
