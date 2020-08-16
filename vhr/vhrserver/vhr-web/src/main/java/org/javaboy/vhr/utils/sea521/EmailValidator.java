package org.javaboy.vhr.utils.sea521;

import cn.hutool.core.lang.Validator;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/1/27 0027 19:42
 */
public class EmailValidator {
    public static void main(String[] args) {
        boolean email = Validator.isEmail("570347720@qq.com");
        System.out.println(email);
        System.out.println(Validator.isMobile("15797624891"));
        System.out.println(Validator.isMobile("1579762489123"));
        System.out.println(Validator.isCitizenId("412724199503284411"));
        System.out.println("邮编校验如下：");
        System.out.println(Validator.isZipCode("461400"));
        //至少多长的
        System.out.println(Validator.isGeneral("100", 2));
        //至少多长的
        System.out.println(Validator.isGeneral("999999", 2, 15));

        /** 1 为空判断
         isNull
         isEmpty
         字母，数字和下划线
         isGeneral
         至少多长的
         isGeneral(String value, int min)
         给定范围的
         isGeneral(String value, int min, int max)
         数字
         isNumber
         给定范围的数字
         isBetween(Number value, Number min, Number max)
         纯字母
         isLetter
         大小写
         isUpperCase
         isLowerCase
         ip4
         isIpv4
         金额
         isMoney
         邮件
         isEmail
         手机号码
         isMobile
         18位身份证
         isCitizenId
         邮编
         isZipCode
         出生年月日
         isBirthday
         URL
         isUrl
         汉字
         isChinese
         汉字，字母，数字和下划线
         isGeneralWithChinese
         mac地址
         isMac
         中国车牌
         isPlateNumber
         uuid
         isUUID*/
    }
}
