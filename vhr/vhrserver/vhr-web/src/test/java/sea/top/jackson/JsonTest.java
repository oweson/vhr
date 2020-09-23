package sea.top.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2019/7/28 11:23
 */
public class JsonTest {
    /**
     * 然后就可以处理JSON数据了。首先需要一个ObjectMapper对象，序列化和反序列化都需要它。
     */

    @Test
    public void demo1() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Friend friend = new Friend("yitian", 25);

        // 1 写为字符串
        String text = mapper.writeValueAsString(friend);
        // 2 写为文件
        mapper.writeValue(new File("friend.jackson"), friend);
        // 3 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(friend);
        System.out.println(text);
        System.out.println("***********************************************************");
        // 4 从字符串中读取,返回实体的对象
        Friend newFriend = mapper.readValue(text, Friend.class);
        // 5 从字节流中读取
        newFriend = mapper.readValue(bytes, Friend.class);
        // 6 从文件中读取
        newFriend = mapper.readValue(new File("friend.jackson"), Friend.class);
        System.out.println(newFriend);
        System.out.println("*************************************************************");
    }

    @Test
    /*2 集合的映射
     除了使用Java类进行映射之外，我们还可以直接使用Map和List等Java集合组织JSON数据，
     在需要的时候可以使用readTree方法直接读取JSON中的某个属性值。需要注意的是从JSON转换为Map对象的时候，
     由于Java的类型擦除，所以类型需要我们手动用new TypeReference<T>给出。

     */
    public void demo2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "yitian");
        map.put("interests", new String[]{"pc games", "music"});

        String text = mapper.writeValueAsString(map);
        System.out.println(text);
        // 1 json类型的集合数据！！！
        Map<String, Object> map2 = mapper.readValue(text, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map2);
        // 2 json数据转化为对象！！！
        JsonNode root = mapper.readTree(text);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();
        // 3 读取json数据的某个值！！！

        System.out.println("name:" + name + " age:" + age);
    }

    @Test
    public void demo3() {

    }
}
