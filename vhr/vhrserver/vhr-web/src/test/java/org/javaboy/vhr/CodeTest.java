package org.javaboy.vhr;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodeTest {
    public static void main(String[] args) {
        // System.out.println(new BCryptPasswordEncoder().encode("123")); 生成密码；
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));
        String a= "$2a$10$O3QDhU7GgigwKcmSxeKDH.KvPqtVZ.tS0qT2isusQqRMaeDaWiE0S";
        String b= "$2a$10$SsqLB.Urc/.WrOgQlWgQL.zarPc9U1wxvSzSnydruEDDCYtYaVI22";
        System.out.println(bCryptPasswordEncoder.matches("123", a));
        System.out.println(bCryptPasswordEncoder.matches("123", b));

    }
}
