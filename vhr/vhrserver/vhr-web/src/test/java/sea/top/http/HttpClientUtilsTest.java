package sea.top.http;

import com.google.common.collect.Maps;
import org.javaboy.vhr.utils.sea521.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/8/8 8:18
 */
public class HttpClientUtilsTest {
    public static void main(String[] args) {
        Map map = new HashMap<String, Object>();
        map.put("query", "慕课网");
        String s = HttpClientUtil.doGet("https://www.sogou.com/", map);
        System.out.println(s);
        // 1 公共api接口测试！https://www.mxnzp.com/api/address/search?type=1&value=%E6%B7%B1%E5%9C%B3
        Map map02 = new HashMap<String, Object>();
        map02.put("type", 1);
        map02.put("value", "深圳");
        s = "https://www.mxnzp.com/api/address/search";
        String areaMessage = HttpClientUtil.doGet(s, map02);
        System.out.println(areaMessage);
        // 2 返回string类型的json数据！
        // post不行！！！ 请求数据的，post干啥啊？？？ todo
        String doPost = HttpClientUtil.doPost(s, map02);
        System.out.println(doPost);


    }
}
