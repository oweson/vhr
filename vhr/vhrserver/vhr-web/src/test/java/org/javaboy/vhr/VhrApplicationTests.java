package org.javaboy.vhr;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VhrApplication.class)
public class VhrApplicationTests {

    @Autowired
    StringEncryptor encryptor;
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }

    @Test
    public void contextLoads() {
        //加密方法
        System.out.println(stringEncryptor.encrypt("123456"));
        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
//        System.out.println(stringEncryptor.decrypt("uaNBj4ZmzCD83uedRYUXqQ=="));
//        System.out.println(stringEncryptor.decrypt("oKBQENfbbQiMyPvECAgPGA=="));
    }

}
