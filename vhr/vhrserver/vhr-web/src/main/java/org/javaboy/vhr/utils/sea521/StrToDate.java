package org.javaboy.vhr.utils.sea521;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/27 0027 19:06
 */
public class StrToDate {
    public static void main(String[] args) {
        String dateStr = "2012-12-12 12:12:12";
        DateTime dateTime = DateUtil.parse(dateStr);
        System.out.println(dateTime);

    }
}
