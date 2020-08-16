package org.javaboy.vhr.utils.sea521;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    public static final Integer transferStringToInt(String string){

        return Integer.parseInt(string);
    }

    public static void main(String[] args) {
        transferStringToInt("100");
        transferStringToInt("npe");
    }
}
