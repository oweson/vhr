package sea.top.entry;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Assert;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/10/27 23:39
 */
public class demo1 {
    /**
     * 我们以RSA为例，介绍使用RSA加密和解密 在构建RSA对象时，
     * 可以传入公钥或私钥，当使用无参构造方法时，Hutool将自动生成随机的公钥私钥密钥对
     */
    public static void main(String[] args) {
        RSA rsa = new RSA();

// 1 获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
// 2 获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();

//公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

//Junit单元测试
        Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

//私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

//Junit单元测试
        Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
    }
}
