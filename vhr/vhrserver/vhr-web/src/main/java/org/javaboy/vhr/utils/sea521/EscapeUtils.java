package org.javaboy.vhr.utils.sea521;

import cn.hutool.core.util.EscapeUtil;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/27 0027 19:14
 */
public class EscapeUtils {
    public static void main(String[] args) {
        String s1 = "<script>location.href='http://how2j.cn';</script>";
        /** 1 转义*/
        String s = EscapeUtil.escapeHtml4(s1);
        System.out.println(s);
    }
}
