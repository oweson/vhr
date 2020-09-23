package sea.top.entry;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/10/27 23:43
 */
public class demo2AutoSecret {
    public static void main(String[] args) {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        String encode = Base64.encode(privateKey.toString().getBytes());
        // 生成private key;
        System.out.println(encode);
        System.out.println("hello".getBytes().toString());
        String encode1 = Base64.encode(publicKey.toString().getBytes());
        System.out.println(encode1);
    }
}
