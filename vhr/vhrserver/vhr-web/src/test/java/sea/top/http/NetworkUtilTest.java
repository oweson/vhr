package sea.top.http;


import org.javaboy.vhr.utils.sea521.NetworkUtil;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/9 13:34
 */
public class NetworkUtilTest {
    public static void main(String[] args) {
        String localIP = NetworkUtil.getLocalIP();
        System.out.println(localIP);
    }
}
