package org.javaboy.vhr.utils.sea521;

import cn.hutool.core.util.IdcardUtil;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/27 0027 19:37
 */
public class IdCardTest {
    public static void main(String[] args) {
        String age = "412724199503284411";
        boolean validCard = IdcardUtil.isValidCard(age);
        System.out.println(validCard);
        String birth = IdcardUtil.getBirth(age);
        System.out.println("主人的生日是：" + birth);
        System.out.println("主人今年" + IdcardUtil.getAgeByIdCard(age) + "岁啦！");
        System.out.println(IdcardUtil.getProvinceByIdCard(age));
    }
}
